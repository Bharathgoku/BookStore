package com.bookstore.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.bookstore.RestTemplateTestApplication;
import com.bookstore.entity.BookCatalog;
import com.bookstore.repo.BookCatalogRepo;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RestTemplateTestApplication.class})
public class MediaCoverageServiceTest {


  @Autowired
  private MediaCoverageService mediaCoverageService;

  @MockBean
  private BookCatalogRepo bookCatalogRepo;

  @Test
  public void getMediaCoverage() throws Exception {
    String isbn = "978-3-16-148410-0";
    BookCatalog bookCatalog = BookCatalog.builder()
                                .id(1)
                                .isbnNumber(isbn)
                                .price(791.28)
                                .title("qui est esse")
                                .author("Sir Quackalot")
                                .build();
    when(bookCatalogRepo.findByIsbnNumber(isbn)).thenReturn(bookCatalog);
    List<String> mediaCoverageList = mediaCoverageService.getMediaCoverage(isbn);
    assertNotNull(mediaCoverageList);
    assertNotEquals(0, mediaCoverageList.size());
  }
}