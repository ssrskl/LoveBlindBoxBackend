package com.maoyan.loveblindbox.entity.dto;

import com.maoyan.loveblindbox.entity.LoveUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoveStickDTO {
    private Long stickId;
    private Long publisherId;
    private Long receiverId;
    private int gender;
    private int age;
    private String qq;
    private String wechat;
    private String hobby;
    private String personality;
    private String introduction;
    private LoveUser publisher;
    private LoveUser receiver;
}
