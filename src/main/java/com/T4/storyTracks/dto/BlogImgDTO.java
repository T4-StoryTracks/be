package com.T4.storyTracks.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogImgDTO {
    private Long imgId;
    private Long postId;
    private String geo_lat;
    private String geo_long;
    private String imgPath;
    private String imgDtm;
    private String rgstDtm;
    private String thumbYn;
}
