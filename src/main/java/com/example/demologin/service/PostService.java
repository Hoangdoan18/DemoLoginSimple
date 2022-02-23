package com.example.demologin.service;

import com.example.demologin.entity.Post;
import com.example.demologin.model.dto.PostInfoDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    List<PostInfoDto> getListPostbyPage(int page);
    PostInfoDto getPostbyId(long id);
}
