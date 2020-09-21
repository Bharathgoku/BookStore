package com.bookstore.service;

import com.bookstore.dto.BookInventoryRequestDto;
import com.bookstore.dto.OrderResponseDto;
import com.bookstore.dto.OrderResponseDto.BookDetails;
import com.bookstore.entity.BookCatalog;
import com.bookstore.entity.BookInventory;
import com.bookstore.entity.Order;
import com.bookstore.enums.InventoryStatus;
import com.bookstore.enums.OrderStatus;
import com.bookstore.exceptions.NotAvailableException;
import com.bookstore.exceptions.SoldOutException;
import com.bookstore.helper.OrderingHelper;
import com.bookstore.repo.BookCatalogRepo;
import com.bookstore.repo.BookInventoryRepo;
import com.bookstore.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrderingService {

  @Autowired
  private BookCatalogRepo bookCatalogRepo;

  @Autowired
  private BookInventoryRepo bookInventoryRepo;

  @Autowired
  private OrderingHelper orderingHelper;

  @Autowired
  private OrderRepo orderRepo;

  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public OrderResponseDto buyBook(String isbnNumber) throws SoldOutException, NotAvailableException{
    BookCatalog bookCatalog = bookCatalogRepo.findByIsbnNumber(isbnNumber);
    BookInventory bookInventory = bookInventoryRepo.findByBookCatalog(bookCatalog);

    Order order = processOrder(bookCatalog, bookInventory);
    BookDetails bookDetails = new BookDetails(bookCatalog.getIsbnNumber(), bookCatalog.getTitle(), bookCatalog.getAuthor());

    assert order != null;
    return OrderResponseDto.builder()
                      .orderId(order.getId())
                      .price(bookCatalog.getPrice())
                      .bookDetails(
                          bookDetails
                      ).build();
  }

  private Order processOrder(BookCatalog bookCatalog, BookInventory bookInventory)
      throws SoldOutException, NotAvailableException{
    switch(bookInventory.getInventoryStatus()){
      case OUT_OF_STOCK:
        throw new SoldOutException("Book with Title Name : " + bookCatalog.getTitle() + ", is Out-of-stock");
      case IN_STOCK:
        // create an order
        Order order = Order.builder()
                        .orderStatus(OrderStatus.SUCCESSFUL)
                        .bookCatalog(bookCatalog)
                        .build();
        if(bookInventory.getCount() == 1){
          bookInventory.setInventoryStatus(InventoryStatus.OUT_OF_STOCK);
          orderingHelper.requestForInventory(bookCatalog.getId());
          // request for the inventory
        }
        bookInventory.setCount(bookInventory.getCount()-1);
        bookInventoryRepo.save(bookInventory);
        orderRepo.save(order);
        return order;
      case NOT_AVAILABLE:
        throw new NotAvailableException("Book with Title Name : " + bookCatalog.getTitle() + ", is not available yet");
      default:
        break;
    }
    return null;
  }



}
