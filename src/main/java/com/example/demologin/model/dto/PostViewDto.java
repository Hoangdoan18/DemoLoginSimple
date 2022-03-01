package com.example.demologin.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostViewDto {
    private long id;

    private String slug;

    private String title;

    private String createdAt;

    private String publishedAt;

    private String description;

    private String content;

    private int status;
}
