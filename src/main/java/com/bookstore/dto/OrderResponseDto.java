package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {

  @JsonProperty("order_id")
  private Integer orderId;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("book_details")
  private BookDetails bookDetails;

  @AllArgsConstructor
  public static class BookDetails {

    @JsonProperty("isbn")
    private String isbnNumber;

    private String title;

    private String author;
  }

}
