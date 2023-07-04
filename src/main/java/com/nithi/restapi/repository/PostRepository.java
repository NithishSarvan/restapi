package com.nithi.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nithi.restapi.post.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
