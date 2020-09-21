package com.bookstore.repo;

import com.bookstore.entity.BookCatalog;
import com.bookstore.entity.BookInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInventoryRepo extends CrudRepository<BookInventory, Long> {

  BookInventory findByBookCatalog(BookCatalog bookCatalog);

//  @Query("select bI from BookCatalog bC left join BookInventory bI on bI.bookCatalog = bC where bC.isbnNumber = ?1")
//  BookInventory findByIsbnNumber(String isbn);

}
