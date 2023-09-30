package com.maoyan.loveblindbox.services.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.maoyan.loveblindbox.entity.LoveStick;
import com.maoyan.loveblindbox.entity.LoveUser;
import com.maoyan.loveblindbox.entity.dto.LoveStickDTO;
import com.maoyan.loveblindbox.entity.vo.PublishLoveStickVO;
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
    public int publishLoveStick(LoveStick newLoveStick) {
        // 获取当前登录的用户ID
        Long currentUserId = StpUtil.getLoginIdAsLong();
        newLoveStick.setPublisherId(currentUserId);
        int i = stickMapper.insertStick(newLoveStick);
        if (i <= 0) {
            throw new CustomException("发布小纸条失败", HttpStatus.ERROR);
        }
        return Math.toIntExact(newLoveStick.getStickId());
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

    /**
     * 查找当前用户所发布的小纸条
     *
     * @param pageNum       页码
     * @param pageSize      每页大小
     * @param currentUserId 当前用户ID
     * @return 小纸条列表
     */
    @Override
    public List<LoveStickDTO> batchFindPublishedLoveStickDetailForCurrentUser(int pageNum, int pageSize, Long currentUserId) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoveStickDTO> loveStickDTOS = stickMapper.BatchSelectLoveStickDetailByPublisherId(currentUserId);
        if (ObjectUtil.isNull(loveStickDTOS)) {
            throw new CustomException("未查询到数据", HttpStatus.NOT_FOUND);
        }
        return loveStickDTOS;
    }

    @Override
    public int receiveLoveStick(int gender) {
        // 获得当前登录用户的ID
        Long currentUserId = StpUtil.getLoginIdAsLong();
        // 随机抽取一个指定性别的小纸条
        LoveStick loveStick = stickMapper.randomSelectLoveStick(gender, currentUserId);
        if (ObjectUtil.isNull(loveStick)) {
            throw new CustomException("未查询到小纸条, 快去发布自己的小纸条吧！", HttpStatus.NOT_FOUND);
        }
        // 接收到的小纸条的ID
        Long receiveLoveStickId = loveStick.getStickId();
        loveStick.setReceiverId(currentUserId);
        int i = stickMapper.updateLoveStick(loveStick);
        if (i <= 0) {
            throw new CustomException("接收失败", HttpStatus.ERROR);
        }
        return Math.toIntExact(receiveLoveStickId);
    }

    /**
     * 更新自己的小纸条
     *
     * @param newloveStick
     * @return
     */
    @Override
    @Deprecated
    public int updateMyselffLoveStick(LoveStick newloveStick) {
        // 获取当前登录用户的ID
        Long currentUserId = StpUtil.getLoginIdAsLong();
        // 根据小纸条ID查询小纸条
        LoveStick loveStick = stickMapper.selectLoveStickById(newloveStick.getStickId());
        // 判断当前登录用户是否为小纸条的发布者
        if (!currentUserId.equals(loveStick.getPublisherId())) {
            throw new CustomException("您不是小纸条的发布者", HttpStatus.FORBIDDEN);
        }
        // 更新小纸条
        int i = stickMapper.updateLoveStick(newloveStick);
        if (i <= 0) {
            throw new CustomException("更新失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 删除自己的小纸条
     *
     * @param stickId
     * @return
     */
    @Override
    public int deleteMyselfLoveStick(Long stickId) {
        // 获取当前登录用户的ID
        Long currentUserId = StpUtil.getLoginIdAsLong();
        // 根据小纸条ID查询小纸条
        LoveStick loveStick = stickMapper.selectLoveStickById(stickId);
        // 判断当前登录用户是否为小纸条的发布者
        if (!currentUserId.equals(loveStick.getPublisherId())) {
            throw new CustomException("您不是小纸条的发布者", HttpStatus.FORBIDDEN);
        }
        // 删除小纸条
        int i = stickMapper.deleteLoveStick(stickId);
        if (i <= 0) {
            throw new CustomException("删除失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 统计当前用户所发布的小纸条的数量
     *
     * @return
     */
    @Override
    public int countLoveStickForCurrentUserPublished() {
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        int i = stickMapper.countLoveStickByPublisherId(loginIdAsLong);
        if (i < 0) {
            throw new CustomException("统计失败", HttpStatus.ERROR);
        }
        return i;
    }

    /**
     * 统计当前用户所接收到的小纸条的数量
     *
     * @return
     */
    @Override
    public int countLoveStickForCurrentUserReceived() {
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        int i = stickMapper.countLoveStickByReceiverId(loginIdAsLong);
        if (i < 0) {
            throw new CustomException("统计失败", HttpStatus.ERROR);
        }
        return i;
    }
}
