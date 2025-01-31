package com.T4.storyTracks;

import com.T4.storyTracks.service.GeminiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeminiServiceTest {
    @Autowired
    private GeminiService service;

    @Test
    void getCompletion() {
        String text = service.getCompletion("서울 맛집을 추천해줘");
        System.out.println(text);
    }
}