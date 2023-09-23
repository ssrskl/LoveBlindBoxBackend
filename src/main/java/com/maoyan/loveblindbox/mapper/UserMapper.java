package com.maoyan.loveblindbox.mapper;

import com.maoyan.loveblindbox.entity.LoveUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertLoveUser(@Param(value = "loveUser") LoveUser loveUser);

    LoveUser selectLoveUserById(@Param(value = "userId") Long userId);

    LoveUser selectLoveUserByEmailAndPassword(@Param(value = "email") String email, @Param(value = "password") String password);
}
