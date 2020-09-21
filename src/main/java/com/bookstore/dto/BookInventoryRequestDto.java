package com.bookstore.dto;

import com.bookstore.enums.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookInventoryRequestDto {

  @JsonProperty("catalog_id")
  private Integer catalogId;

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("inventory_status")
  private InventoryStatus inventoryStatus;

}
