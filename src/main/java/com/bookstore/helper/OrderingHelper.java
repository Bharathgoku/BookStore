package com.bookstore.helper;

import com.bookstore.dto.BookInventoryRequestDto;
import com.bookstore.enums.InventoryStatus;
import com.bookstore.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderingHelper {

  @Autowired
  private InventoryService inventoryService;


  @Async
  public void requestForInventory(Integer catalogId){
    // add books to the inventory
    // adding 2 item for each request
    BookInventoryRequestDto bookInventoryRequestDto = new BookInventoryRequestDto();
    bookInventoryRequestDto.setInventoryStatus(InventoryStatus.IN_STOCK);
    bookInventoryRequestDto.setCatalogId(catalogId);
    bookInventoryRequestDto.setCount(2);
    try{
      inventoryService.updateInventoryToCatalog(bookInventoryRequestDto);
    }catch(Exception ex){
      log.error("Error while requesting for the inventory, catalogId : {}, error message : {}", catalogId, ex.getMessage());
    }
  }



}
