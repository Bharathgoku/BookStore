package com.bookstore.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class MediaCoverageDto {

  List<MediaCoverage> mediaCoverageList;

  @Data
  public static class MediaCoverage{
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("userId")
    private Integer userId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;
  }


}
