package com.example.demologin.controller;

import com.example.demologin.entity.Post;
import com.example.demologin.exception.NotFoundException;
import com.example.demologin.model.dto.PostInfoDto;
import com.example.demologin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    public PostService postService;

    @GetMapping("/post")
    public ResponseEntity<?> getAllPost(@RequestParam int page) {
        if(page<=0) {
            page = 1;
        }
        List<PostInfoDto> list = postService.getListPostbyPage(page);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/post-detail")
    public ResponseEntity<?> getPost(@RequestParam long id) {
        PostInfoDto post = postService.getPostbyId(id);
        if(post == null) {
            throw new NotFoundException("Post not found!");
        }
        return ResponseEntity.ok(post);
    }

}
