package com.bookstore.dto;

import com.bookstore.entity.BookCatalog;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookCatalogResponseDto {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("isbn")
  private String isbn;

  @JsonProperty("title")
  private String title;

  @JsonProperty("author")
  private String author;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("created_at")
  private Date created_at;

  @JsonProperty("updated_id")
  private Date updated_id;

  public static BookCatalogResponseDto createObject(BookCatalog bookCatalog){

    return BookCatalogResponseDto.builder()
                                          .id(bookCatalog.getId())
                                          .author(bookCatalog.getAuthor())
                                          .isbn(bookCatalog.getIsbnNumber())
                                          .price(bookCatalog.getPrice())
                                          .title(bookCatalog.getTitle())
                                          .created_at(bookCatalog.getCreatedAt())
                                          .updated_id(bookCatalog.getUpdatedAt())
                                          .build();
  }

}
