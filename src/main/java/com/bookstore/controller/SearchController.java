package com.bookstore.controller;

import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.enums.SearchFilter;
import com.bookstore.dto.BaseResponse;
import com.bookstore.service.SearchService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/book-store/search")
public class SearchController {

  @Autowired
  private SearchService searchService;

  // search a book
  @GetMapping(value = "/search-book")
  public ResponseEntity<BaseResponse<List<BookCatalogResponseDto>>> searchBook(@RequestParam String text, @RequestParam SearchFilter searchFilter){
    return new ResponseEntity<>(new BaseResponse<>(searchService.searchBooks(text, searchFilter)), HttpStatus.OK);
  }

}
