package com.maoyan.loveblindbox.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublishLoveStickVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int gender;
    private int age;
    private String qq;
    private String wechat;
    private String hobby;
    private String personality;
    private String introduction;
}
