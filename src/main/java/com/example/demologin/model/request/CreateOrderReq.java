package com.example.demologin.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderReq {
    @NotEmpty(message = "Product mustn't empty")
    @JsonProperty("product_id")
    private String productId;

    @Min(value = 35)
    @Max(value = 42)
    private int size;

    @NotEmpty(message = "Full name mustn't empty")
    @ApiModelProperty(
            example="Sam Smith",
            notes="Full name empty",
            required=true
    )
    @JsonProperty("receiver_name")
    private String receiverName;

    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b", message = "Invalid phone number.")
    @ApiModelProperty(
            example="0916016972",
            notes="Điện thoại trống",
            required=true
    )
    @JsonProperty("receiver_phone")
    private String receiverPhone;

    @NotEmpty(message = "Address mustn't empty.")
    @ApiModelProperty(
            example="Ha Noi",
            notes="Address empty.",
            required=true
    )
    @JsonProperty("receiver_address")
    private String receiverAddress;

    @JsonProperty("coupon_code")
    private String couponCode;

    @JsonProperty("total_price")
    private long totalPrice;

    @JsonProperty("product_price")
    private long productPrice;

    private String note;
}
