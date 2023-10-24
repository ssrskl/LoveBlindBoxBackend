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

import java.util.regex.Pattern;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int LoveUserRegister(LoveUser loveUser) {
        // 对密码进行加密
        String md5Password = SaSecureUtil.md5(loveUser.getPassword());
        loveUser.setPassword(md5Password);
        // 判断邮箱受否为QQ邮箱
        if (loveUser.getEmail().matches("[1-9][0-9]{8,10}\\@[q][q]\\.[c][o][m]")) {
            String regex = "^(\\d+)@.*$";
            String qq = loveUser.getEmail().replaceAll(regex, "$1");
            // 截取@前面的数字作为QQ号
            loveUser.setAvater("https://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640");
        } else {
            // 使用默认的头像，创建随机数1~7
            int random = (int) (Math.random() * 7 + 1);
            loveUser.setAvater("https://maomaoio.oss-cn-beijing.aliyuncs.com/avatar%20/avatar%20%28" + random + "%29.png");
        }
        int i = userMapper.insertLoveUser(loveUser);
        userMapper.updateLoveUser(loveUser);
        if (i <= 0) {
            throw new CustomException("注册失败", HttpStatus.ERROR);
        }
        return i;
    }
}
