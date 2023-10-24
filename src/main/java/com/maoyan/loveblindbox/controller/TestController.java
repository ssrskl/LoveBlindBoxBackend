package com.maoyan.loveblindbox.controller;

import com.maoyan.loveblindbox.entity.LoveStick;
import com.maoyan.loveblindbox.mapper.StickMapper;
import com.maoyan.loveblindbox.services.StickService;
import com.maoyan.loveblindbox.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private StickService stickService;

    @Autowired
    private StickMapper stickMapper;

    @GetMapping(value = "/stick/{stickId}")
    public AjaxResult testFindStickById(@PathVariable Long stickId) {
        LoveStick loveStickById = stickService.findLoveStickById(stickId);
        return AjaxResult.success("查找成功", loveStickById);
    }

    /**
     * 测试正则表达式
     */
    @GetMapping(value = "/regex")
    public AjaxResult testRegex() {
        String regex = "^(\\d+)@.*$";
        String email = "820244680@qq.com";
        String qq = email.replaceAll(regex, "$1");
        if (email.matches("[1-9][0-9]{8,10}\\@[q][q]\\.[c][o][m]")) {
            System.out.println("是QQ邮箱");
            System.out.println("截取@前面的数字作为QQ号");
            System.out.println("https://q1.qlogo.cn/g?b=qq&nk=" + qq + "&s=640");
        }
        return AjaxResult.success("测试成功");
    }
}
