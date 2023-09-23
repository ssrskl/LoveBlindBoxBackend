package com.maoyan.loveblindbox.mapper;

import com.maoyan.loveblindbox.entity.LoveStick;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StickMapper {
    int insertStick(@Param(value = "loveStick") LoveStick loveStick);

    LoveStick selectLoveStickById(@Param(value = "stickId") Lang stickId);
}
