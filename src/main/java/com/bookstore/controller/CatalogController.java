package com.bookstore.controller;

import com.bookstore.dto.BaseResponse;
import com.bookstore.dto.BookCatalogRequestDto;
import com.bookstore.dto.BookCatalogResponseDto;
import com.bookstore.service.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/book-store/catalog")
public class CatalogController {

//  @GetMapping(value = "/get_top")
//  public ServiceResponse<BaseResponse<PointsOfInterestResponseDto>> getPoiByCityName(@RequestParam(value = "city_name") String cityName) throws RuntimeException{
//    try{
//      log.info("[POI - GET ByCity Request] - RequestParam:{city_name : " + cityName + "}");
//      return new ServiceResponse<>(new BaseResponse<>(pointsOfInterestService.getPoiByCityName(cityName)));
//    }catch(InvalidCityException e){
//      return new ServiceResponse<>(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
//    }catch(InternalServerError e){
//      return new ServiceResponse<>(new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//  }

  @Autowired
  private CatalogService catalogService;

  // add book to the store
  @PostMapping(value = "/add-book")
  public ResponseEntity<BaseResponse<BookCatalogResponseDto>> addBook(@RequestBody BookCatalogRequestDto bookCatalogRequestDto){
    try{
      BookCatalogResponseDto bookCatalogResponseDto = catalogService.addBookToStore(bookCatalogRequestDto);
      return new ResponseEntity<>(new BaseResponse<>(bookCatalogResponseDto),
          HttpStatus.OK);
    }catch(ConstraintViolationException cVEx){
      return new ResponseEntity<>(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), cVEx.getMessage(),null), HttpStatus.BAD_REQUEST);
    }catch(Exception ex){
      return new ResponseEntity<>(new BaseResponse<>(
          HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
