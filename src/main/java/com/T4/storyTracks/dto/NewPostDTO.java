package com.T4.storyTracks.dto;

import com.T4.storyTracks.entity.BlogPostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.T4.storyTracks.dto.BlogImgDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
