package com.bookstore.service;

import static org.junit.Assert.*;

import com.bookstore.JpaTestApplication;
import com.bookstore.dto.BookCatalogRequestDto;
import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.dto.BookInventoryRequestDto;
import com.bookstore.dto.BookInventoryResponseDto;
import com.bookstore.enums.InventoryStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {JpaTestApplication.class})
public class InventoryServiceTest {

  @Autowired
  private CatalogService catalogService;

  @Autowired
  private InventoryService inventoryService;

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
  }

  @Test
  public void updateInventoryToCatalog() throws Exception {
    BookInventoryRequestDto bookInventoryRequestDto = new BookInventoryRequestDto();
    bookInventoryRequestDto.setCatalogId(bookCataLogResponseDto.getId());
    bookInventoryRequestDto.setCount(2);
    bookInventoryRequestDto.setInventoryStatus(InventoryStatus.IN_STOCK);
    BookInventoryResponseDto bookInventoryResponseDto = inventoryService.updateInventoryToCatalog(bookInventoryRequestDto);

    assertNotNull(bookInventoryResponseDto);
    assertEquals(InventoryStatus.IN_STOCK, bookInventoryResponseDto.getInventoryStatus());
  }
}