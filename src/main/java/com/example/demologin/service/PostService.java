package com.example.demologin.service;

import com.example.demologin.model.dto.PostInfoDto;
import com.example.demologin.model.dto.PostViewDto;

import java.util.List;

public interface PostService {
    List<PostInfoDto> getListPostbyPage(int page);

    PostViewDto getPostbyId(long id);

    List<PostInfoDto> getLatestPostsExceptId(long id);

    List<PostInfoDto> getLastestPost();


}
