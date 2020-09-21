package com.bookstore.repo;

import com.bookstore.entity.BookCatalog;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCatalogRepo extends CrudRepository<BookCatalog, Integer> {

  @Query("select b from BookCatalog b where lower(b.title) like %?1%")
  List<BookCatalog> findByPartialTitle(String text);

  @Query("select b from BookCatalog b where lower(b.author) like %?1%")
  List<BookCatalog> findByPartialAuthor(String text);

  @Query("select b from BookCatalog b where b.isbnNumber = ?1")
  BookCatalog findByIsbnNumber(String isbn);

}
