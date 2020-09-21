package com.bookstore.controller;

import com.bookstore.dto.BaseResponse;
import com.bookstore.dto.OrderResponseDto;
import com.bookstore.exceptions.NotAvailableException;
import com.bookstore.exceptions.SoldOutException;
import com.bookstore.service.OrderingService;
import javax.xml.ws.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/book-store/ordering")
public class OrderingController {

  @Autowired
  private OrderingService orderingService;

  // buy a book
  @GetMapping(value = "/buy-book")
  public ResponseEntity<BaseResponse<OrderResponseDto>> buyBook(@RequestParam String isbnNumber){
    try{
      return new ResponseEntity<>(new BaseResponse<>(orderingService.buyBook(isbnNumber)), HttpStatus.OK);
    }catch(SoldOutException | NotAvailableException sOEx){
      return new ResponseEntity<>(new BaseResponse<>(HttpStatus.FORBIDDEN.value(), sOEx.getMessage(), null), HttpStatus.FORBIDDEN);
    } catch(Exception ex){
      return new ResponseEntity<>(new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
