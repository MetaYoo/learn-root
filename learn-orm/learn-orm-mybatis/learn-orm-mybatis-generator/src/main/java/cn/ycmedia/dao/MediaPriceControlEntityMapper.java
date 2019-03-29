package cn.ycmedia.dao;

import cn.ycmedia.model.MediaPriceControlEntity;

public interface MediaPriceControlEntityMapper {
    int deleteByPrimaryKey(Integer itemid);

    int insert(MediaPriceControlEntity record);

    int insertSelective(MediaPriceControlEntity record);

    MediaPriceControlEntity selectByPrimaryKey(Integer itemid);

    int updateByPrimaryKeySelective(MediaPriceControlEntity record);

    int updateByPrimaryKey(MediaPriceControlEntity record);
}