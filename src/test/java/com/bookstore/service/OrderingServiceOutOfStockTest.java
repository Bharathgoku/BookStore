package com.bookstore.service;

import com.bookstore.JpaTestApplication;
import com.bookstore.dto.BookCatalogRequestDto;
import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.dto.BookInventoryRequestDto;
import com.bookstore.dto.OrderResponseDto;
import com.bookstore.enums.InventoryStatus;
import com.bookstore.exceptions.SoldOutException;
import com.bookstore.helper.OrderingHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {JpaTestApplication.class})
public class OrderingServiceOutOfStockTest {

  @Autowired
  private CatalogService catalogService;

  @Autowired
  private InventoryService inventoryService;

  @MockBean
  private OrderingHelper orderingHelper;

  @Autowired
  private OrderingService orderingService;

  BookCatalogRequestDto bookCatalogRequestDto;

  BookCatalogResponseDto bookCataLogResponseDto;

  @Before
  public void setUp() throws Exception {
    bookCatalogRequestDto = new BookCatalogRequestDto();
    bookCatalogRequestDto.setAuthor("Sir Quackalot");
    bookCatalogRequestDto.setIsbn("978-3-16-148410-0");
    bookCatalogRequestDto.setPrice(791.28);
    bookCatalogRequestDto.setTitle("The Adventures of Duck and Goose");
    bookCataLogResponseDto = catalogService.addBookToStore(bookCatalogRequestDto);

    BookInventoryRequestDto bookInventoryRequestDto = new BookInventoryRequestDto();
    bookInventoryRequestDto.setCount(1);
    bookInventoryRequestDto.setCatalogId(bookCataLogResponseDto.getId());
    bookInventoryRequestDto.setInventoryStatus(InventoryStatus.IN_STOCK);
    inventoryService.updateInventoryToCatalog(bookInventoryRequestDto);

  }

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void buyBook() {
    OrderResponseDto orderResponseDto = orderingService.buyBook(bookCatalogRequestDto.getIsbn());
    assertNotNull(orderResponseDto);

    exceptionRule.expect(SoldOutException.class);
    exceptionRule.expectMessage("Book with Title Name : " + bookCatalogRequestDto.getTitle() + ", is Out-of-stock");
    orderingService.buyBook(bookCatalogRequestDto.getIsbn());
  }
}