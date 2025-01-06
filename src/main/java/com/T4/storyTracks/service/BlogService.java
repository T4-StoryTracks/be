package com.T4.storyTracks.service;

import com.T4.storyTracks.dto.*;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogImgRepository;
import com.T4.storyTracks.repository.BlogRepository;
import com.T4.storyTracks.service.GeminiService;
import com.T4.storyTracks.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogImgRepository blogImgRepository;

    private final GeminiService service;
    private final S3Service s3Service;

    public List<BlogListPostDTO> findAll() {
        List<BlogPostEntity> blogPostEntityList= blogRepository.findAll();
        List<BlogListPostDTO> blogPostDTOList =new ArrayList<>();
        for (BlogPostEntity postEntity: blogPostEntityList) {
            blogPostDTOList.add(BlogListPostDTO.toPostListDTO(postEntity, blogImgRepository.findByBlogPostPostIdAndThumbYn(postEntity.getPostId(), "Y")
                    .orElseThrow(() -> new IllegalArgumentException("blogImgEntity no thumbImg"))));
        }
        return blogPostDTOList;
    }
    //글 정보 통째로 저장
    public Long savePost(BlogPostDTO newPostDTO) {
        return blogRepository.save(BlogPostEntity.toPostEntity(newPostDTO)).getPostId();
    }


    //aiGenTextList를 생성하는 함수

    public List<BlogImgDTO> getBlogImgList(Long postId) {
        List<BlogImgEntity> imgEntityList = blogImgRepository.findByBlogPostPostId(postId);
        if (imgEntityList.isEmpty()) {
            throw new IllegalArgumentException("no imgEntityList");
        }
        List<BlogImgDTO> imgDTOList = new ArrayList<>();
        imgEntityList.stream().forEach(x -> imgDTOList.add(BlogImgDTO.toImgDTO(x)));
        return imgDTOList;
    }

    public BlogPostDTO getBlogPostOne(Long postId) {
        //getBlogImgList(postId)
        Optional<BlogPostEntity> postEntity = blogRepository.findById(postId);
        if (!postEntity.isPresent()) {
            throw new IllegalArgumentException("no blogPost page");
        }

        // 이미지 리스트 가져오기
        List<BlogImgEntity> imgEntityList = blogImgRepository.findByBlogPostPostId(postId);
        if (imgEntityList.isEmpty()) {
            throw new IllegalArgumentException("no imgEntityList");
        }
        List<BlogImgDTO> imgDTOList = new ArrayList<>();
        imgEntityList.stream().forEach(x -> imgDTOList.add(BlogImgDTO.toImgDTO(x)));
        BlogPostDTO postDTO = BlogPostDTO.toPostDTO(postEntity.get(),imgDTOList);

        return postDTO;
    }

    public Map<String, String> createPost(ImgMetaDTO imgMetaDTO, String ogText) { //
        //System.out.println(imgMetaDTO.getGeoLat()+", "+imgMetaDTO.getGeoLong());
        int[] length = {200, 350, 450};
        Map<String, String> genAIList = new HashMap<>(3);

        for (int i=0; i<length.length; i++){
            String tmp = "genRes"+Integer.toString(i+1);
            genAIList.put(tmp, genText(ogText, imgMetaDTO.getGeoLat(), imgMetaDTO.getGeoLong(), length[i]));
        }
        return genAIList;
    }

    public String genText(String ogText, String geoLat, String geoLong, int length) {
        String prompt = "";
        prompt = """
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
                - 
                - """ + length + "words"
                +
                """
                
                Be creative but remain consistent with the input data.
                """;
        String text = service.getCompletion(prompt);
        return text; //prompt.substring(0, length); // test용
    }

    public String saveImgS3(BlogImgDTO imgDTO, Long postId) {
        try {
            String fileUrl = s3Service.uploadFile(imgDTO.getImgFile(), "uploadtest1");
            return fileUrl;

        } catch (IOException e) {
            new IllegalArgumentException(postId + "  S3 upload failed - " + imgDTO.getImgFile().getName());
        }
        return "";
    }

    public Long saveImgList(BlogImgDTO imgDTO, Long postId, String fileUrl) {
        return blogImgRepository.save(BlogImgEntity.toImgEntity(imgDTO, postId, fileUrl)).getImgId();
    }
}
