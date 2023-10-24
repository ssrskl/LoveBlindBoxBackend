package com.maoyan.loveblindbox.services.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
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
        long loginIdAsLong = StpUtil.getLoginIdAsLong(); // 获得当前登录用户的id
        String md5Password = SaSecureUtil.md5(newLoveUser.getPassword()); // 对密码进行加密
        newLoveUser.setPassword(md5Password);
        newLoveUser.setUserId(loginIdAsLong);
        int i = userMapper.updateLoveUser(newLoveUser);
        if (i <= 0) {
            throw new CustomException("更新用户信息失败", 500);
        }
        return i;
    }
}
