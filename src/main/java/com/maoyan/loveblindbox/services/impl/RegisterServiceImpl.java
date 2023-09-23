package com.maoyan.loveblindbox.services.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.ObjectUtil;
import com.maoyan.loveblindbox.entity.LoveUser;
import com.maoyan.loveblindbox.exception.CustomException;
import com.maoyan.loveblindbox.mapper.UserMapper;
import com.maoyan.loveblindbox.services.RegisterService;
import com.maoyan.loveblindbox.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int LoveUserRegister(LoveUser loveUser) {
        // 对密码进行加密
        String md5Password = SaSecureUtil.md5(loveUser.getPassword());
        loveUser.setPassword(md5Password);
        int i = userMapper.insertLoveUser(loveUser);
        if (i <= 0) {
            throw new CustomException("注册失败", HttpStatus.ERROR);
        }
        return i;
    }
}
