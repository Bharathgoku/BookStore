package com.bookstore.dto;

import com.bookstore.entity.BookInventory;
import com.bookstore.enums.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookInventoryResponseDto {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("catalog_id")
  private Integer catalogId;

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("inventory_status")
  private InventoryStatus inventoryStatus;

  public static BookInventoryResponseDto createObject(BookInventory bookInventory){
    return BookInventoryResponseDto.builder()
        .id(bookInventory.getId())
        .catalogId(bookInventory.getBookCatalog().getId())
        .count(bookInventory.getCount())
        .inventoryStatus(bookInventory.getInventoryStatus())
        .build();
  }
}
