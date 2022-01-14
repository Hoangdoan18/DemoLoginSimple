package com.example.demologin.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductReq {
    @NotEmpty(message = "Product's name mustn't empty")
    @Size(min = 1, max = 300, message = "Product's name length must from 1 to 300 characters")
    @ApiModelProperty(
            example="Adidas KH422",
            notes="Product's name mustn't empty",
            required=true
    )
    private String name;

    @NotEmpty(message = "Product's description mustn't empty")
    @ApiModelProperty(
            example="Lorem",
            notes="Product's description empty",
            required=true
    )
    private String description;

    @NotNull(message = "Product's brand mustn't empty")
    @ApiModelProperty(
            example="1",
            notes="Nhãn hiệu trống",
            required=true
    )
    @JsonProperty("brand_id")
    private Integer brandId;

    @NotNull(message = "Product's category mustn't empty")
    @ApiModelProperty(
            example="[1]",
            notes="Product's category empty",
            required=true
    )
    @JsonProperty("category_ids")
    private ArrayList<Integer> categoryIds;

    @JsonProperty("is_available")
    private boolean isAvailable;

    @Min(1000)
    @Max(1000000000)
    @ApiModelProperty(
            example="4000000",
            notes="Invalid price",
            required=true
    )
    private Integer price;

    @NotNull(message = "Image list mustn't empty")
    @ApiModelProperty(
            example="[img1.jpg, img2.jpg]",
            notes="Image list empty",
            required=true
    )
    @JsonProperty("product_images")
    private ArrayList<String> productImages;
}
