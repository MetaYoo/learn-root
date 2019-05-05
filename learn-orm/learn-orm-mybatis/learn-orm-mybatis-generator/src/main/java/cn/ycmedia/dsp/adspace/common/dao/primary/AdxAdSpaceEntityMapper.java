package cn.ycmedia.dsp.adspace.common.dao.primary;

import cn.ycmedia.dsp.adspace.common.entity.AdxAdSpaceEntity;

public interface AdxAdSpaceEntityMapper {
    int deleteByPrimaryKey(Integer itemid);

    int insert(AdxAdSpaceEntity record);

    int insertSelective(AdxAdSpaceEntity record);

    AdxAdSpaceEntity selectByPrimaryKey(Integer itemid);

    int updateByPrimaryKeySelective(AdxAdSpaceEntity record);

    int updateByPrimaryKey(AdxAdSpaceEntity record);
}