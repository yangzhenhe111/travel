package com.cy.travels.service.impl;

import com.cy.travels.dao.TravelTrafficMapper;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.TravelTrafficDTO;
import com.cy.travels.model.entity.TravelTraffic;
import com.cy.travels.service.TravelTrafficService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.prefs.BackingStoreException;

@Slf4j
@Service
@Transactional
public class TravelTrafficServiceImpl implements TravelTrafficService {

    @Autowired
    private TravelTrafficMapper trafficMapper;

    @Override
    public TravelTrafficDTO saveOrUpdata(TravelTrafficDTO condition) {
        if (Objects.isNull(condition.getId())) {
            //保存
            return save(condition);
        } else {
            //修改
            return update(condition);
        }
    }

    private TravelTrafficDTO update(TravelTrafficDTO condition) {
        TravelTrafficDTO result = new TravelTrafficDTO();
        TravelTraffic Traffic = trafficMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(Traffic, result);

        BeanUtils.copyProperties(condition, Traffic);
        int insert = trafficMapper.updateByPrimaryKeySelective(Traffic);
        if (insert > 0) {
            BeanUtils.copyProperties(Traffic, result);
        }
        return result;
    }

    private TravelTrafficDTO save(TravelTrafficDTO condition) {
        TravelTraffic Traffic = new TravelTraffic();
        BeanUtils.copyProperties(condition, Traffic);
        int insert = trafficMapper.insert(Traffic);
        TravelTrafficDTO result = new TravelTrafficDTO();
        BeanUtils.copyProperties(condition, result);
        if (insert > 0) {
            TravelTraffic dbTraffic = trafficMapper.selectOne(Traffic);
            BeanUtils.copyProperties(dbTraffic, result);
        }
        return result;
    }

    @Override
    public TravelTrafficDTO getDetails(TravelTrafficDTO condition) {
        TravelTraffic query = new TravelTraffic();
        query.setIsDeleted(YesOrNoEnum.N.getCode());
        TravelTraffic dbTraffic;
        TravelTrafficDTO result = new TravelTrafficDTO();
        if (condition.getId() != null) {
            query.setId(condition.getId());
        } else if (condition.getTitleId() != null) {
            query.setTitleId(condition.getTitleId());
        } else {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(), "id不能为null");
        }
        dbTraffic = trafficMapper.selectOne(query);
        BeanUtils.copyProperties(dbTraffic, result);
        return result;
    }

    @Override
    public TravelTrafficDTO delete(TravelTrafficDTO condition) {
        TravelTrafficDTO result = new TravelTrafficDTO();
        TravelTraffic Traffic = trafficMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(Traffic, result);

        Traffic.setIsDeleted(YesOrNoEnum.Y.getCode());
        int insert = trafficMapper.updateByPrimaryKeySelective(Traffic);
        if (insert > 0) {
            BeanUtils.copyProperties(Traffic, result);
        }
        return result;
    }
}
