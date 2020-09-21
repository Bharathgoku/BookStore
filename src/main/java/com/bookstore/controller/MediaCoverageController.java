package com.bookstore.controller;

import com.bookstore.dto.BaseResponse;
import com.bookstore.service.MediaCoverageService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@Slf4j
@RestController
@RequestMapping(value = "/book-store/")
public class MediaCoverageController {

  @Autowired
  private MediaCoverageService mediaCoverageService;

  // search media coverage about a book
  @GetMapping(value = "/get-media-coverage")
  public ResponseEntity<BaseResponse<List<String>>> getMediaCoverage(@RequestParam String isbnNumber){
    try{
      return new ResponseEntity<>(new BaseResponse<>(mediaCoverageService.getMediaCoverage(isbnNumber)),
          HttpStatus.OK);
    }catch(RestClientException rCEx){
      return new ResponseEntity<>(new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), rCEx.getMessage(), null), HttpStatus.BAD_REQUEST);
    }catch(Exception ex){
      return new ResponseEntity<>(new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
