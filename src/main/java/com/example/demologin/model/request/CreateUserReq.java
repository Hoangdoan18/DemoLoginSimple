package com.example.demologin.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserReq {

//    @JsonProperty("name") is to change property name to full_name
    @NotNull(message = "Name is required")
    private String fullname;

    @NotNull(message = "Password is required")
    @Size(min = 4, max = 20, message = "Password must be between 4 - 20 characters")
    private String password;

    @NotNull(message = "Name is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @Pattern(regexp = "[0-9]+" , message = "Please provide a valid phone number")
    private String phone;

}
