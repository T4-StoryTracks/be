package com.T4.storyTracks.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BlogImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;
}
