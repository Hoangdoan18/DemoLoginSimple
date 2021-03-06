package com.example.demologin.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadForm {
    @Valid
    @URL(regexp="(\\/\\/.*\\.(?:png|jpg))", message="Image must be an end with .jpg or .png")
    private MultipartFile image;

    private MultipartFile fileData;
}
