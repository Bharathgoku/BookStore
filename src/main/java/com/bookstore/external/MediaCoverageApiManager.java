package com.bookstore.external;

import com.bookstore.external.MediaCoverageDto.MediaCoverage;
import com.bookstore.util.HttpRequestUtil;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class MediaCoverageApiManager {

  @Value("${media.coverage.api.url}")
  private String MEDIA_COVERAGE_URL;

  @Autowired
  private HttpRequestUtil httpRequestUtil;

  public List<MediaCoverage> getMediaCoverage() throws RestClientException {
    String url = MEDIA_COVERAGE_URL;
    HttpHeaders httpHeaders = getHeaders();
    URI uri =
        UriComponentsBuilder.fromUriString(url)
            .build()
            .toUri();
    MediaCoverage[] mediaCoverageArray = httpRequestUtil.getApiResponse(uri, httpHeaders, MediaCoverage[].class);
    return Arrays.asList(mediaCoverageArray);
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }

}
