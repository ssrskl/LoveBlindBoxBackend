package com.maoyan.loveblindbox.services;

import com.maoyan.loveblindbox.entity.LoveStick;
import com.maoyan.loveblindbox.entity.dto.LoveStickDTO;
import com.maoyan.loveblindbox.entity.vo.PublishLoveStickVO;

import java.util.List;

public interface StickService {
    int publishLoveStick(LoveStick loveStick); //发布小纸条

    LoveStick findLoveStickById(Long loveStickId); //根据Id查找小纸条

    LoveStickDTO findLoveStickDetailById(Long loveStickId); //根据ID查找小纸条的详细信息

    List<LoveStickDTO> batchFindLoveStickDetailForCurrentUser(int pageNum, int pageSize, Long currentUserId); //查找当前用户所收到的纸条

    List<LoveStickDTO> batchFindPublishedLoveStickDetailForCurrentUser(int pageNum, int pageSize, Long currentUserId); //查找当前用户所发布的纸条

    int receiveLoveStick(int gender);

    int updateMyselffLoveStick(LoveStick newloveStick);

    int deleteMyselfLoveStick(Long stickId);

    int countLoveStickForCurrentUserPublished();

    int countLoveStickForCurrentUserReceived();

    int countLoveStickByGender(int gender); // 查询指定性别的小纸条数量
}
