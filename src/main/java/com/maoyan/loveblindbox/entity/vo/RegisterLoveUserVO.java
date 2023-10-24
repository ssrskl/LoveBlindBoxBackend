package com.maoyan.loveblindbox.entity.vo;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterLoveUserVO {
    private String username;
    private String password;
    @Email
    private String email;
}
