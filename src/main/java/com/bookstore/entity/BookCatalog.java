package com.bookstore.entity;

import com.bookstore.dto.BookCatalogRequestDto;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@Entity
@Table(name = "book_catalog")
public class BookCatalog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "isbn")
  private String isbnNumber;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "price")
  private Double price;

  @OneToOne(mappedBy = "bookCatalog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private BookInventory bookInventory;

  @OneToMany(mappedBy = "bookCatalog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Order> orderList;

  @Column(name = "created_at")
  @CreationTimestamp
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Date updatedAt;

  public static BookCatalog createObject(BookCatalogRequestDto bookCatalogRequestDto){

    return BookCatalog.builder()
                    .isbnNumber(bookCatalogRequestDto.getIsbn())
                    .author(bookCatalogRequestDto.getAuthor())
                    .price(bookCatalogRequestDto.getPrice())
                    .title(bookCatalogRequestDto.getTitle())
                    .build();
  }

}
