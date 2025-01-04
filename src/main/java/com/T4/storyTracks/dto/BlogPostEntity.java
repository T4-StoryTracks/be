package com.T4.storyTracks.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Post")
public class BlogPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    @Column
    private String ogText;

    @Lob
    @Column
    private String aiGenText;

    @Column(length = 64, nullable = false)
    private String password;
}
