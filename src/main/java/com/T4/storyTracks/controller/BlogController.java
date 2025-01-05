package com.T4.storyTracks.controller;

import com.T4.storyTracks.dto.BlogImgDTO;
import com.T4.storyTracks.dto.BlogPostDTO;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogController {
    public final BlogService blogService;

//    @PostMapping("/save")
//    @Description("저장")
//    public String save(@ModelAttribute BlogPostDTO blogPostDTO) throws IOException {
//        blogService.save(blogPostDTO);
//        return "index";
//    }

    //프론트에서 위도 경도, 사용자 글 받은 후 제미나이로 커스텀하는 함수를 서비스에서 불러와서 리스트 세개 프론트로 보내기
    @PostMapping("/create1")
    public ResponseEntity<BlogImgEntity> saveLocation(@RequestBody BlogImgDTO request) {
        BlogImgEntity savedLocation = blogService.saveLagLong(request);
        return ResponseEntity.ok(savedLocation);
    }

}
