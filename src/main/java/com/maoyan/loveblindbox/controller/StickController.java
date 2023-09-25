package com.maoyan.loveblindbox.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.maoyan.loveblindbox.entity.LoveStick;
import com.maoyan.loveblindbox.entity.dto.LoveStickDTO;
import com.maoyan.loveblindbox.entity.vo.PublishLoveStickVO;
import com.maoyan.loveblindbox.services.StickService;
import com.maoyan.loveblindbox.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stick")
public class StickController {

    @Autowired
    private StickService stickService;

    @GetMapping(value = "/{loveStickId}")
    public AjaxResult queryLoveStickById(@PathVariable Long loveStickId) {
//        LoveStick loveStickById = stickService.findLoveStickById(loveStickId);
        LoveStickDTO loveStickDetailById = stickService.findLoveStickDetailById(loveStickId);
        return AjaxResult.success("查询成功", loveStickDetailById);
    }

    @GetMapping(value = "/query")
    public AjaxResult batchQueryLoveStickDetailForReceiver(@RequestParam(defaultValue = "1") int pageNum,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        // 获取当前登录用户的ID
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        // 查询当前用户所接收到的小纸条
        List<LoveStickDTO> loveStickDTOS = stickService.batchFindLoveStickDetailForCurrentUser(pageNum, pageSize, loginIdAsLong);
        return AjaxResult.success("查询成功", loveStickDTOS);
    }

    @PostMapping(value = "/publish")
    public AjaxResult publishLoveStick(@RequestBody PublishLoveStickVO publishLoveStickVO) {
        LoveStick newLoveStick = new LoveStick();
        BeanUtil.copyProperties(publishLoveStickVO, newLoveStick);
        int i = stickService.publishLoveStick(newLoveStick);
        return AjaxResult.success("发布成功", i);
    }
}
