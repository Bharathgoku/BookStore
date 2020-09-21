package com.bookstore.service;

import com.bookstore.enums.SearchFilter;
import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.entity.BookCatalog;
import com.bookstore.repo.BookCatalogRepo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  @Autowired
  private BookCatalogRepo bookCatalogRepo;

  public List<BookCatalogResponseDto> searchBooks(String text, SearchFilter searchFilter){

    List<BookCatalogResponseDto> bookCatalogResponseDtoList = new ArrayList<>();
    List<BookCatalog> bookCatalogList = null;
    switch (searchFilter){

      case ISBN:
        bookCatalogList = Collections.singletonList(bookCatalogRepo.findByIsbnNumber(text));
        break;
      case TITLE:
        bookCatalogList = bookCatalogRepo.findByPartialTitle(text.toLowerCase());
        break;
      case AUTHOR:
        bookCatalogList = bookCatalogRepo.findByPartialAuthor(text.toLowerCase());
        break;
      default:
        bookCatalogList = new ArrayList<>();
        break;

    }

    bookCatalogResponseDtoList = bookCatalogList.stream().map(BookCatalogResponseDto::createObject).collect(
        Collectors.toList());

    return bookCatalogResponseDtoList;

  }

}
