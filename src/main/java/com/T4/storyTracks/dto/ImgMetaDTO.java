package com.T4.storyTracks.dto;

import jdk.jfr.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Description("Generate AI 클릭 시 넘어오는 이미지 메타데이터")
public class ImgMetaDTO {
    private String geoLat;
    private String geoLong;
    private String imgDtm;

    public ImgMetaDTO(String geoLat, String geoLong, String imgDtm) {
        this.geoLat = geoLat;
        this.geoLong = geoLong;
        this.imgDtm = imgDtm;
    }
}
