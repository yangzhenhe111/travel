package com.cy.travels.service.impl;

import com.cy.travels.dao.TravelLocalOverviewMapper;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.TravelLocalOverviewDTO;
import com.cy.travels.model.entity.TravelLocalOverview;
import com.cy.travels.service.TravelLocalOverviewService;
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
public class TravelLocalOverviewServiceImpl implements TravelLocalOverviewService {

    @Autowired
    private TravelLocalOverviewMapper localOverviewMapper;

    @Override
    public TravelLocalOverviewDTO saveOrUpdata(TravelLocalOverviewDTO condition) {
        if (Objects.isNull(condition.getId())) {
            //保存
            return save(condition);
        }else {
            //修改
            return update(condition);
        }
    }

    private TravelLocalOverviewDTO update(TravelLocalOverviewDTO condition) {
        TravelLocalOverviewDTO result = new TravelLocalOverviewDTO();
        TravelLocalOverview localOverview = localOverviewMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(localOverview,result);

        BeanUtils.copyProperties(condition,localOverview);
        int insert = localOverviewMapper.updateByPrimaryKeySelective(localOverview);
        if (insert > 0) {
            BeanUtils.copyProperties(localOverview,result);
        }
        return result;
    }

    private TravelLocalOverviewDTO save(TravelLocalOverviewDTO condition) {
        TravelLocalOverview localOverview = new TravelLocalOverview();
        BeanUtils.copyProperties(condition,localOverview);
        int insert = localOverviewMapper.insert(localOverview);
        TravelLocalOverviewDTO result = new TravelLocalOverviewDTO();
        BeanUtils.copyProperties(condition,result);
        if (insert > 0) {
            TravelLocalOverview dbLocalOverview = localOverviewMapper.selectOne(localOverview);
            BeanUtils.copyProperties(dbLocalOverview,result);
        }
        return result;
    }

    @Override
    public TravelLocalOverviewDTO getDetails(TravelLocalOverviewDTO condition) {
        TravelLocalOverview query = new TravelLocalOverview();
        query.setIsDeleted(YesOrNoEnum.N.getCode());
        TravelLocalOverview dbLocalOverview;
        TravelLocalOverviewDTO result = new TravelLocalOverviewDTO();
        if (condition.getId() != null) {
            query.setId(condition.getId());
        }else if (condition.getTitleId() != null){
            query.setTitleId(condition.getTitleId());
        }else {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(),"id不能为null");
        }
        dbLocalOverview = localOverviewMapper.selectOne(query);
        BeanUtils.copyProperties(dbLocalOverview,result);
        return result;
    }

    @Override
    public TravelLocalOverviewDTO delete(TravelLocalOverviewDTO condition) {
        TravelLocalOverviewDTO result = new TravelLocalOverviewDTO();
        TravelLocalOverview localOverview = localOverviewMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(localOverview,result);

        localOverview.setIsDeleted(YesOrNoEnum.Y.getCode());
        int insert = localOverviewMapper.updateByPrimaryKeySelective(localOverview);
        if (insert > 0) {
            BeanUtils.copyProperties(localOverview,result);
        }
        return result;
    }
}
