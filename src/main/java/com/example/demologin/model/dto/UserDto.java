package com.example.demologin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
    private List<String> roles;
}
