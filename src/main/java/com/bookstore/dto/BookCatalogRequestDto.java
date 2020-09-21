package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookCatalogRequestDto {

  @JsonProperty("isbn")
  private String isbn;

  @JsonProperty("title")
  private String title;

  @JsonProperty("author")
  private String author;

  @JsonProperty("price")
  private Double price;

}
