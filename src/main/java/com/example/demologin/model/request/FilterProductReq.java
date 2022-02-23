package com.example.demologin.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilterProductReq {
    private List<String> brands;

    private List<String> categories;

    @JsonProperty("min_price")
    private Long minPrice;

    @JsonProperty("max_price")
    private Long maxPrice;

    private List<Integer> size;

    private int page;
}
