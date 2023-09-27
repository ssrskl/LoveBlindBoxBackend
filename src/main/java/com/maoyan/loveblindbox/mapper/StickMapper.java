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

    int updateLoveStick(@Param(value = "loveStick") LoveStick newloveStick);

    LoveStick randomSelectLoveStick(@Param(value = "gender") int gender);
}
