package com.example.demologin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandInfo {
    private int id;

    private String name;

    private String thumbnail;

    private int productCount;
}
