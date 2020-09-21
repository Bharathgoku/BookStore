package com.bookstore.service;

import com.bookstore.dto.BookCatalogRequestDto;
import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.entity.BookCatalog;
import com.bookstore.repo.BookCatalogRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CatalogService {

  @Autowired
  private BookCatalogRepo bookCatalogRepo;

  @Autowired
  private InventoryService inventoryService;

  @Transactional
  public BookCatalogResponseDto addBookToStore(BookCatalogRequestDto bookCatalogRequestDto){
    BookCatalog bookCatalog = BookCatalog.createObject(bookCatalogRequestDto);
    bookCatalog = bookCatalogRepo.save(bookCatalog);
    // add dummy inventory
    inventoryService.addInventoryToCatalog(bookCatalog);
    return BookCatalogResponseDto.createObject(bookCatalog);
  }

}
