package com.maoyan.loveblindbox.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateLoveUserVO {
    private String username;
    private String password;
    private String email;
    private String avater;
}
