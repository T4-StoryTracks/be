package com.T4.storyTracks.service;

import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogImgRepository;
import com.T4.storyTracks.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogImgRepository blogImgRepository;
    private final GeminiService service;

    public List<BlogPostDTO> findAll() {
        List<BlogPostEntity> blogPostEntityList= blogRepository.findAll();
        List<BlogPostDTO> blogPostDTOList =new ArrayList<>();
        for (BlogPostEntity postEntity: blogPostEntityList) {
            blogPostDTOList.add(BlogPostDTO.toPostDTO(postEntity));
        }
        return blogPostDTOList;
    }

    //위도 경도 저장 함수
    public BlogImgEntity saveLagLong(BlogImgDTO request) {
        BlogImgEntity location = new BlogImgEntity();
        location.setGeoLat(request.getGeoLat());
        location.setGeoLong(request.getGeoLong());
        return blogImgRepository.save(location);
    }

    //aiGenTextList를 생성하는 함수
    public String genText(String ogText, Long geoLat, Long geoLong, int length) {
        String promt = "";
        promt = """
                You are an expert blog writer specializing in creating daily journal-style blog posts for public sharing. Based on the provided image metadata and user description, generate three versions of a blog post:
                                
                **Content Requirements:**
                - Include the location and time keywords from the metadata in the content.
                - Write in a friendly and descriptive tone, as if sharing a daily journal entry.
                - Describe the location visited, the activities done, how they were done, and the emotions felt.
                - Follow a logical time sequence (e.g., what happened first, next, and so on).
                - Write in the structure of an introduction, main body, and conclusion.
                                
                **Output Structure:**
                1. **Introduction:** Briefly introduce the setting and context.
                2. **Main Body:** Narrate the experience in detail, including actions, observations, and emotions.
                3. **Conclusion:** Summarize the experience and its personal significance.
                """
                +
                "**Must Include Input Data:**\n"
                + "- location(inferred through the given latitude and longitude): latitude = " + geoLat + ", longitude = " + geoLong
                + "\n- base context: " + ogText
                + """
                **Output Format:**
                - Markdown language
                - """ + length + "words"
                +
                """
                
                Be creative but remain consistent with the input data.
                """;
        String text = service.getCompletion(promt);
        return text;
    }
}
