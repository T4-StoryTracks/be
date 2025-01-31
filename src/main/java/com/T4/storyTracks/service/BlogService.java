package com.T4.storyTracks.service;

import com.T4.storyTracks.dto.*;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogImgRepository;
import com.T4.storyTracks.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
        for(BlogPostEntity postEntity : blogPostEntityList) {
            System.out.println(postEntity.getPostId() + "  " + postEntity.getTitle());
        }

        Optional<BlogImgEntity> imgEntity;
        List<BlogListPostDTO> blogPostDTOList =new ArrayList<>();
        for (BlogPostEntity postEntity: blogPostEntityList) {
            //imgEntity = blogImgRepository.findByBlogPostPostIdAndThumbYn(postEntity.getPostId(), "Y");
            imgEntity = blogImgRepository.findFirstByBlogPostPostId(postEntity.getPostId());
            try {
                blogPostDTOList.add(BlogListPostDTO.toBlogPostEntity(postEntity, imgEntity.get()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("return blogPostDTOList");
        System.out.println(blogPostDTOList);
        return blogPostDTOList;
    }
    //글 정보 통째로 저장
    public BlogPostEntity savePost(BlogPostDTO newPostDTO) {
        Long tmp = blogRepository.save(BlogPostEntity.toPostEntity(newPostDTO)).getPostId();
        Optional<BlogPostEntity> postEntity = blogRepository.findById(tmp);
        if (postEntity.isPresent()) {
            System.out.println(">>>>>>>>>BlogService: " + postEntity.get().getPostId());
            return postEntity.get();
        }
        else {
            throw new IllegalArgumentException("savePost - service");
        }
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
        /*prompt = """
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
                """;*/
        prompt = "Below is a review written by a traveler. Based on this review, write a blog post in the appropriate format.\nThe latitude of the travel destination is "
                + geoLat
                + ", and the longitude is "
                + geoLong
                + ". Write in Markdown syntax.\n\n"
                + ogText;
        System.out.println("prompt");
        System.out.println(prompt);

        String text = service.getCompletion(prompt);
        System.out.println("text");
        System.out.println(text);

        return text; //prompt.substring(0, length); // test용
    }

//    public String saveImgS3(BlogImgDTO imgDTO, Long postId) {
//        try {
//            String fileUrl = s3Service.uploadFile(imgDTO.getImgFile(), "uploadtest1");
//            return fileUrl;
//
//        } catch (IOException e) {
//            new IllegalArgumentException(postId + "  S3 upload failed - " + imgDTO.getImgFile().getName());
//        }
//        return "";
//    }

    public Long saveImgList(BlogImgDTO imgDTO, BlogPostEntity postEntity) {
        return blogImgRepository.save(BlogImgEntity.toImgEntity(imgDTO, postEntity)).getImgId();
    }
}
