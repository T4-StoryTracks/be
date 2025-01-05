package com.T4.storyTracks.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateRequestDTO {
//    String title;
    String ogText;
    String geoLat;
    String geoLong;
    List<String> aiGenTextList;
}
