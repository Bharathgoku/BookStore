package com.bookstore.service;

import com.bookstore.entity.BookCatalog;
import com.bookstore.external.MediaCoverageApiManager;
import com.bookstore.external.MediaCoverageDto;
import com.bookstore.external.MediaCoverageDto.MediaCoverage;
import com.bookstore.repo.BookCatalogRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;

@Service
public class MediaCoverageService {

  @Autowired
  private BookCatalogRepo bookCatalogRepo;

  @Autowired
  private MediaCoverageApiManager mediaCoverageApiManager;

  public List<String> getMediaCoverage(String isbnNumber) throws RestClientException {
    List<MediaCoverage> mediaCoverageList = mediaCoverageApiManager.getMediaCoverage();

    BookCatalog bookCatalog = bookCatalogRepo.findByIsbnNumber(isbnNumber);

    List<String> mediaCoverageTitleList = new ArrayList<>();
    if(bookCatalog == null)
      return mediaCoverageTitleList;
    if(!CollectionUtils.isEmpty(mediaCoverageList)){

      for(MediaCoverage mediaCoverage: mediaCoverageList){
        if(mediaCoverage.getTitle().contains(bookCatalog.getTitle())){
          mediaCoverageTitleList.add(mediaCoverage.getTitle());
        }else if(mediaCoverage.getBody().contains(bookCatalog.getTitle())){
          mediaCoverageTitleList.add(mediaCoverage.getTitle());
        }
      }

    }
    return mediaCoverageTitleList;
  }

}
