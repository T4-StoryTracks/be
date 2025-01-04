package com.T4.storyTracks.entity;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;
    @Column(name = "og_text")
    private String ogText;
    @Column(name = "ai_gen_text") //생성 텍스트 = 이미지 분석 데이터 + Img.geo + ogText
    private String aiGenText;
    @Column(name = "password")
    private String password;
    @Column(name = "rgst_dtm")
    private LocalDateTime rgstDtm;
    @Column(name = "chng_dtm")
    private LocalDateTime chngDtm;
    @Column(name = "title")
    private String title;

    public void setEmpId(String postId) {
        this.postId = postId;
    }

    public String getpostId() {
        return postId;
    }

}