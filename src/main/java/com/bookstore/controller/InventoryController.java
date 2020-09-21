package com.bookstore.controller;

import com.bookstore.dto.BaseResponse;
import com.bookstore.dto.BookInventoryRequestDto;
import com.bookstore.dto.BookInventoryResponseDto;
import com.bookstore.exceptions.CatalogNotFoundException;
import com.bookstore.exceptions.InvalidDataException;
import com.bookstore.exceptions.InventoryNotFoundException;
import com.bookstore.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/book-store/inventory")
public class InventoryController {

  @Autowired
  private InventoryService inventoryService;

  @PutMapping(value = "/update-inventory")
  public ResponseEntity<BaseResponse<BookInventoryResponseDto>> updateInventory(
      BookInventoryRequestDto bookInventoryRequestDto){
    try{
      return new ResponseEntity<>(new BaseResponse<>(inventoryService.updateInventoryToCatalog(bookInventoryRequestDto)), HttpStatus.OK);
    }catch(InvalidDataException | CatalogNotFoundException | InventoryNotFoundException iDEx){
      return new ResponseEntity<>(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), iDEx.getMessage(), null), HttpStatus.BAD_REQUEST);
    } catch(Exception ex){
      return new ResponseEntity<>(new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}
