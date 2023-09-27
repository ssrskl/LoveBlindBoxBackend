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

    @GetMapping(value = "/stick/random")
    public AjaxResult testRandomReceiveStick() {
        LoveStick loveStick = stickMapper.randomSelectLoveStick(0);
        return AjaxResult.success("查询成功", loveStick);
    }
}
