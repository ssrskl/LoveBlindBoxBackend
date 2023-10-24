package com.maoyan.loveblindbox.mapper;

import com.maoyan.loveblindbox.entity.LoveStick;
import com.maoyan.loveblindbox.entity.dto.LoveStickDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StickMapper {
    int insertStick(@Param(value = "loveStick") LoveStick loveStick);

    LoveStick selectLoveStickById(@Param(value = "stickId") Long stickId);

    LoveStickDTO selectLoveStickDetailById(@Param(value = "stickId") Long stickId);

    List<LoveStickDTO> BatchSelectLoveStickDetailByReceiverId(@Param(value = "receiverId") Long receiverId);

    List<LoveStickDTO> BatchSelectLoveStickDetailByPublisherId(@Param(value = "publisherId") Long publisherId);

    int updateLoveStick(@Param(value = "loveStick") LoveStick newloveStick);

    LoveStick randomSelectLoveStick(@Param(value = "gender") int gender, @Param(value = "receiverId") Long receiverId);

    int deleteLoveStick(@Param(value = "stickId") Long stickId);

    /**
     * --------------------------数量统计----------------------------------
     */
    int countLoveStickByReceiverId(@Param(value = "receiverId") Long receiverId);

    int countLoveStickByPublisherId(@Param(value = "publisherId") Long publisherId);
    int countEmptyLoveStickByGender(@Param(value = "gender") int gender);
}
