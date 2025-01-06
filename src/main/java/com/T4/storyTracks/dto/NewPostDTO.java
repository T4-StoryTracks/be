package com.T4.storyTracks.dto;

import com.T4.storyTracks.dto.BlogImgDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPostDTO {

    private String title;
    private String ogText;
    private String aiGenText;

    private List<BlogImgDTO> imgSaveList = new ArrayList<>();

    public NewPostDTO(String title, String ogText, String aiGenText) {
        this.title = title;
        this.ogText = ogText;
        this.aiGenText = aiGenText;
    }

}
