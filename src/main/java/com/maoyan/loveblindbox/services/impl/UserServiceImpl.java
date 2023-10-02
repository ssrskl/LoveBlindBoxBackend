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

    /**
     * 更新当前用户
     *
     * @param newLoveUser
     * @return
     */
    @Override
    public int updateCurrentUser(LoveUser newLoveUser) {
        int i = userMapper.updateLoveUser(newLoveUser);
        if (i <= 0) {
            throw new CustomException("更新用户信息失败", 500);
        }
        return i;
    }
}
