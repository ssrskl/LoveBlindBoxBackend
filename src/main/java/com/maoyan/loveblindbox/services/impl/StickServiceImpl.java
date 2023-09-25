package com.maoyan.loveblindbox.services.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.maoyan.loveblindbox.entity.LoveStick;
import com.maoyan.loveblindbox.entity.LoveUser;
import com.maoyan.loveblindbox.entity.dto.LoveStickDTO;
import com.maoyan.loveblindbox.exception.CustomException;
import com.maoyan.loveblindbox.mapper.StickMapper;
import com.maoyan.loveblindbox.mapper.UserMapper;
import com.maoyan.loveblindbox.services.StickService;
import com.maoyan.loveblindbox.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StickServiceImpl implements StickService {

    @Autowired
    private StickMapper stickMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int publishLoveStick(LoveStick loveStick) {
        // 获取当前用户的ID
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        // 获取当前用户
        LoveUser currentLoveUser = userMapper.selectLoveUserById(loginIdAsLong);
        loveStick.setPublisherId(loginIdAsLong);
        int i = stickMapper.insertStick(loveStick);
        if (i <= 0) {
            throw new CustomException("发布小纸条失败", HttpStatus.ERROR);
        }
        return i;
    }

    @Override
    public LoveStick findLoveStickById(Long loveStickId) {
        LoveStick loveStick = stickMapper.selectLoveStickById(loveStickId);
        if (ObjectUtil.isNull(loveStick)) {
            throw new CustomException("小纸条不存在", HttpStatus.NOT_FOUND);
        }
        return loveStick;
    }

    @Override
    public LoveStickDTO findLoveStickDetailById(Long loveStickId) {
        LoveStickDTO loveStickDTO = stickMapper.selectLoveStickDetailById(loveStickId);
        if (ObjectUtil.isNull(loveStickDTO)) {
            throw new CustomException("小纸条不存在", HttpStatus.NOT_FOUND);
        }
        return loveStickDTO;
    }

    @Override
    public List<LoveStickDTO> batchFindLoveStickDetailForCurrentUser(int pageNum, int pageSize, Long currentUserId) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoveStickDTO> loveStickDTOS = stickMapper.BatchSelectLoveStickDetailByReceiverId(currentUserId);
        if (ObjectUtil.isNull(loveStickDTOS)) {
            throw new CustomException("未查询到数据", HttpStatus.NOT_FOUND);
        }
        return loveStickDTOS;
    }

    @Override
    public int receiveLoveStick(LoveStick loveStick) {
        int i = stickMapper.updateLoveStick(loveStick);
        if (i <= 0) {
            throw new CustomException("接收失败", HttpStatus.ERROR);
        }
        return i;
    }
}
