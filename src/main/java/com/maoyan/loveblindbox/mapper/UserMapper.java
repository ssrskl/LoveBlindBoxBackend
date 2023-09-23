package com.maoyan.loveblindbox.mapper;

import com.maoyan.loveblindbox.entity.LoveUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertLoveUser(LoveUser loveUser);

    LoveUser selectLoveUserById(Long userId);
}
