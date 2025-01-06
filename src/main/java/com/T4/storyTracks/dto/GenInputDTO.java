package com.T4.storyTracks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Description("Gemini 사용하여 AI기반 텍스트 생성을 위한 데이터오브젝트 ")
public class GenInputDTO {

    //    @JsonProperty("imgInfoList")
//    private List<ImgMetaDTO> imgInfoList;
    @JsonProperty("imgInfo")
    private ImgMetaDTO imgMetaDTO;
    @JsonProperty("ogText")
    private String ogText;

}
