package com.maoyan.loveblindbox.services;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.maoyan.loveblindbox.entity.LoveUser;

public interface LoginService {
    SaTokenInfo LoveUserLogin(LoveUser loveUser);
}
