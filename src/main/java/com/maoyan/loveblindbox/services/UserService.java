package com.maoyan.loveblindbox.services;

import com.maoyan.loveblindbox.entity.LoveUser;

public interface UserService {
    LoveUser findLoveUserById(Long UserId); // 通过ID查找一个用户

    int updateCurrentUser(LoveUser newLoveUser); // 更新当前用户
}
