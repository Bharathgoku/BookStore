package com.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.bookstore.JpaTestApplication;
import com.bookstore.dto.BookCatalogRequestDto;
import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.repo.BookCatalogRepo;
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
public class CatalogServiceTest {

  @Autowired
  private CatalogService catalogService;

  @Test
  public void addBookToStore() {
    BookCatalogRequestDto bookCatalogRequestDto = new BookCatalogRequestDto();
    bookCatalogRequestDto.setAuthor("Sir Quackalot");
    bookCatalogRequestDto.setIsbn("978-3-16-148410-0");
    bookCatalogRequestDto.setPrice(791.28);
    bookCatalogRequestDto.setTitle("The Adventures of Duck and Goose");
    BookCatalogResponseDto bookCataLogResponseDto = catalogService.addBookToStore(bookCatalogRequestDto);
    assertNotNull(bookCataLogResponseDto);
    assertEquals(bookCatalogRequestDto.getIsbn(), bookCataLogResponseDto.getIsbn());

  }
}