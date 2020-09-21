package com.bookstore.util;

import com.bookstore.external.MediaCoverageDto.MediaCoverage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HttpRequestUtil {

  @Autowired private RestTemplate restTemplate;

  public <T> T getApiResponse(URI uri, HttpHeaders headers, Class<MediaCoverage[]> responseClass)
      throws RestClientResponseException {
    log.debug("Get Api url : " + uri.toString());
    RestTemplate restTemplate = this.restTemplate;
    try {
      ResponseEntity<T> responseEntity =
          (ResponseEntity<T>) restTemplate.exchange(
              uri, HttpMethod.GET, new HttpEntity<T>(headers), responseClass);
      return responseEntity.getBody();
    } catch (RestClientResponseException rCREX) {
      log.error("RestClientResponseException while trying to hit URI : " + uri, rCREX);
      throw rCREX;
    }
  }


}

