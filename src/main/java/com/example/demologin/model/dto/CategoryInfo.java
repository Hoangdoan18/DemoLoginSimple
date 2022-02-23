package com.example.demologin.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryInfo {
    private int id;

    private String name;

    private int productCount;
}
