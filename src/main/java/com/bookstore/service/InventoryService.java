package com.bookstore.service;

import com.bookstore.dto.BookInventoryRequestDto;
import com.bookstore.dto.BookInventoryResponseDto;
import com.bookstore.entity.BookCatalog;
import com.bookstore.entity.BookInventory;
import com.bookstore.enums.InventoryStatus;
import com.bookstore.exceptions.CatalogNotFoundException;
import com.bookstore.exceptions.InvalidDataException;
import com.bookstore.exceptions.InventoryNotFoundException;
import com.bookstore.repo.BookCatalogRepo;
import com.bookstore.repo.BookInventoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryService {

  @Autowired
  private BookInventoryRepo bookInventoryRepo;

  @Autowired
  private BookCatalogRepo bookCatalogRepo;

  public void addInventoryToCatalog(BookCatalog bookCatalog){
    BookInventory bookInventory = BookInventory.builder()
        .bookCatalog(bookCatalog)
        .count(0)
        .inventoryStatus(InventoryStatus.NOT_AVAILABLE)
        .build();

    bookInventoryRepo.save(bookInventory);
  }

  public BookInventoryResponseDto updateInventoryToCatalog(BookInventoryRequestDto bookInventoryRequestDto)
      throws InvalidDataException, CatalogNotFoundException, InventoryNotFoundException{
    if(bookInventoryRequestDto.getCatalogId() == null){
      log.error("CatalogId is Empty in the request Body : {}" , bookInventoryRequestDto.toString());
      throw new InvalidDataException("CatalogId is Empty in the request Body - " + bookInventoryRequestDto.toString());
    }
    BookCatalog bookCatalog = bookCatalogRepo.findById(bookInventoryRequestDto.getCatalogId()).orElse(null);
    if(bookCatalog == null){
      log.error("Catalog Not found for Id : {}", bookInventoryRequestDto.getCatalogId());
      throw new CatalogNotFoundException("Catalog Not Found for Id : " + bookInventoryRequestDto.getCatalogId());
    }
    BookInventory bookInventory = bookInventoryRepo.findByBookCatalog(bookCatalog);
    if(bookInventory == null){
      log.error("Inventory Not found for Catalog Id : {}", bookCatalog.getId());
      throw new InventoryNotFoundException("Inventory Not Found for Catalog Id  : " + bookCatalog.getId() );
    }
    bookInventory.setCount(bookInventoryRequestDto.getCount());
    bookInventory.setInventoryStatus(bookInventoryRequestDto.getInventoryStatus());
    bookInventoryRepo.save(bookInventory);
    return BookInventoryResponseDto.createObject(bookInventory);
  }

}
