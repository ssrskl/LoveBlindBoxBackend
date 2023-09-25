package com.maoyan.loveblindbox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoveStick implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long stickId;
    private Long publisherId;
    private Long receiverId;
    private Boolean publisherGender;
    private String age;
    private String qq;
    private String wechat;
    private String hobby;
    private String personality;
    private String introduction;
}

