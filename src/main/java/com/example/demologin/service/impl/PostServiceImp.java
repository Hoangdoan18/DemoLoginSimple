package com.example.demologin.service.impl;

import com.example.demologin.entity.Post;
import com.example.demologin.model.dto.PostInfoDto;
import com.example.demologin.model.dto.PostViewDto;
import com.example.demologin.repository.PostRepository;
import com.example.demologin.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demologin.config.Constant.*;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PostInfoDto> getListPostbyPage(int page) {
        Page<Post> postPage = postRepository.findAllByStatus(PUBLIC_POST,PageRequest.of(page - 1, LIMIT_POST_PER_PAGE, Sort.by("publishedAt").descending()));
        List<PostInfoDto> result = postPage.stream().map(i -> mapper.map(i, PostInfoDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public PostViewDto getPostbyId(long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post == null || post.get().getStatus() == 0)
            return null;
        PostViewDto result = mapper.map(post.get(), PostViewDto.class);
        return result;
    }

    @Override
    public List<PostInfoDto> getLatestPostsExceptId(long id) {
        List<Post> list = postRepository.getLatestPostsNotId(PUBLIC_POST,id,5);
        List<PostInfoDto> result = list.stream().map(n -> mapper.map(n,PostInfoDto.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<PostInfoDto> getLastestPost() {
        List<Post> list = postRepository.getLatestPosts(PUBLIC_POST, LIMIT_POST_PER_PAGE);
        List<PostInfoDto> result = list.stream().map(n -> mapper.map(n, PostInfoDto.class)).collect(Collectors.toList());
        return result;
    }
}
