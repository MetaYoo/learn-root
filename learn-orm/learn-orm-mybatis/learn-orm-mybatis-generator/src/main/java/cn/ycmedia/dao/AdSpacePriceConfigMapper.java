package cn.ycmedia.dao;

import cn.ycmedia.model.AdSpacePriceConfig;

public interface AdSpacePriceConfigMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(AdSpacePriceConfig record);

    int insertSelective(AdSpacePriceConfig record);

    AdSpacePriceConfig selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(AdSpacePriceConfig record);

    int updateByPrimaryKey(AdSpacePriceConfig record);
}