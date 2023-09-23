package com.maoyan.loveblindbox.services.impl;

import cn.hutool.core.util.ObjectUtil;
import com.maoyan.loveblindbox.entity.LoveUser;
import com.maoyan.loveblindbox.exception.CustomException;
import com.maoyan.loveblindbox.mapper.UserMapper;
import com.maoyan.loveblindbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoveUser findLoveUserById(Long UserId) {
        LoveUser loveUser = userMapper.selectLoveUserById(UserId);
        if (ObjectUtil.isNull(loveUser)) {
            throw new CustomException("用户不存在", 404);
        }
        return loveUser;
    }
}
