package com.maoyan.loveblindbox.services.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.maoyan.loveblindbox.entity.LoveUser;
import com.maoyan.loveblindbox.exception.CustomException;
import com.maoyan.loveblindbox.mapper.UserMapper;
import com.maoyan.loveblindbox.services.LoginService;
import com.maoyan.loveblindbox.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SaTokenInfo LoveUserLogin(LoveUser loveUser) {
        //获得加密后的密码
        String md5Password = SaSecureUtil.md5(loveUser.getPassword());
        LoveUser loveUserLogined = userMapper.selectLoveUserByEmailAndPassword(loveUser.getEmail(), md5Password);
        if (ObjectUtil.isNull(loveUserLogined)) {
            throw new CustomException("邮箱或密码错误", HttpStatus.ERROR);
        }
        StpUtil.login(loveUserLogined.getUserId(), true);
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        return saTokenInfo;
    }
}
