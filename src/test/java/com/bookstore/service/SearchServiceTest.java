package com.bookstore.service;

import static org.junit.Assert.*;

import com.bookstore.JpaTestApplication;
import com.bookstore.dto.BookCatalogRequestDto;
import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.entity.BookCatalog;
import com.bookstore.enums.SearchFilter;
import java.util.List;
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
public class SearchServiceTest {

  @Autowired
  private SearchService searchService;

  @Autowired
  private CatalogService catalogService;

  BookCatalogRequestDto bookCatalogRequestDto;

  BookCatalogResponseDto bookCatalogResponseDto;

  @Before
  public void setUp() throws Exception {
    bookCatalogRequestDto = new BookCatalogRequestDto();
    bookCatalogRequestDto.setAuthor("Sir Quackalot");
    bookCatalogRequestDto.setIsbn("978-3-16-148410-0");
    bookCatalogRequestDto.setPrice(791.28);
    bookCatalogRequestDto.setTitle("The Adventures of Duck and Goose");
    bookCatalogResponseDto = catalogService.addBookToStore(bookCatalogRequestDto);
  }

  @Test
  public void searchBooks() {

    // test search by isbn
    List<BookCatalogResponseDto> bookCatalogListByIsbn = searchService.searchBooks(bookCatalogRequestDto.getIsbn(),
        SearchFilter.ISBN);
    assertNotNull(bookCatalogListByIsbn);
    assertEquals(bookCatalogResponseDto.getIsbn(), bookCatalogListByIsbn.get(0).getIsbn());

    // test partial search by title
    List<BookCatalogResponseDto> bookCatalogListByTitle = searchService.searchBooks("The Adventures of Duc", SearchFilter.TITLE);
    assertNotNull(bookCatalogListByTitle);
    assertEquals(1, bookCatalogListByTitle.size());
    assertEquals(bookCatalogResponseDto.getTitle(), bookCatalogListByTitle.get(0).getTitle());

    // test partial search by author
    List<BookCatalogResponseDto> bookCatalogListByAuthor = searchService.searchBooks("Sir Quac", SearchFilter.AUTHOR);
    assertNotNull(bookCatalogListByAuthor);
    assertEquals(1, bookCatalogListByAuthor.size());
    assertEquals(bookCatalogResponseDto.getAuthor(), bookCatalogListByTitle.get(0).getAuthor());


  }
}