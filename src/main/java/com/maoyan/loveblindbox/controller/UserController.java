package com.maoyan.loveblindbox.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.bean.BeanUtil;
import com.maoyan.loveblindbox.entity.LoveUser;
import com.maoyan.loveblindbox.entity.vo.LoginLoveUserVO;
import com.maoyan.loveblindbox.entity.vo.RegisterLoveUserVO;
import com.maoyan.loveblindbox.services.LoginService;
import com.maoyan.loveblindbox.services.RegisterService;
import com.maoyan.loveblindbox.services.UserService;
import com.maoyan.loveblindbox.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/{loveUserId}")
    public AjaxResult queryLoveUser(@PathVariable Long loveUserId) {
        LoveUser loveUserById = userService.findLoveUserById(loveUserId);
        return AjaxResult.success("查询成功", loveUserById);
    }

    @PostMapping(value = "/register")
    public AjaxResult registerLoveUser(@RequestBody RegisterLoveUserVO registerLoveUserVO) {
        LoveUser newLoveUser = new LoveUser();
        BeanUtil.copyProperties(registerLoveUserVO, newLoveUser);
        int i = registerService.LoveUserRegister(newLoveUser);
        return AjaxResult.success("注册成功", i);
    }

    @PostMapping(value = "/login")
    public AjaxResult loginLoveUser(@RequestBody LoginLoveUserVO loginLoveUserVO) {
        LoveUser loginingLoveUser = new LoveUser();
        BeanUtil.copyProperties(loginLoveUserVO, loginingLoveUser);
        SaTokenInfo saTokenInfo = loginService.LoveUserLogin(loginingLoveUser);
        return AjaxResult.success("登录成功", saTokenInfo);
    }
}
