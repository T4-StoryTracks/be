package com.T4.storyTracks.repository;

import com.T4.storyTracks.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
