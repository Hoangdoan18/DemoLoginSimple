package com.example.demologin.controller;

import com.example.demologin.exception.NotFoundException;
import com.example.demologin.model.dto.PostInfoDto;
import com.example.demologin.model.dto.PostViewDto;
import com.example.demologin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    public PostService postService;

    @GetMapping("/post")
    public ResponseEntity<?> getAllPost(@RequestParam(required = false) Integer page) {
        if(page <= 0 || page == null) {
            page = 1;
        }
        List<PostInfoDto> list = postService.getListPostbyPage(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/post-detail/{slug}/{id}")
    public ResponseEntity<?> getPost(@PathVariable long id) {
        PostViewDto post = postService.getPostbyId(id);
        List<PostInfoDto> latestPost = postService.getLatestPostsExceptId(id);
        if(post == null) {
            throw new NotFoundException("Post not found!");
        }
        return ResponseEntity.ok(post);
    }

}
