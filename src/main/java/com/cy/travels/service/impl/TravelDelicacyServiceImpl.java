package com.cy.travels.service.impl;

import com.cy.travels.dao.TravelDelicacyMapper;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.TravelDelicacyDTO;
import com.cy.travels.model.entity.TravelDelicacy;
import com.cy.travels.service.TravelDelicacyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@Transactional
public class TravelDelicacyServiceImpl implements TravelDelicacyService {

    @Autowired
    private TravelDelicacyMapper delicacyMapper;

    @Override
    public TravelDelicacyDTO saveOrUpdata(TravelDelicacyDTO condition) {
        if (Objects.isNull(condition.getId())) {
            //保存
            return save(condition);
        } else {
            //修改
            return update(condition);
        }
    }

    private TravelDelicacyDTO update(TravelDelicacyDTO condition) {
        TravelDelicacyDTO result = new TravelDelicacyDTO();
        TravelDelicacy Delicacy = delicacyMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(Delicacy, result);

        BeanUtils.copyProperties(condition, Delicacy);
        int insert = delicacyMapper.updateByPrimaryKeySelective(Delicacy);
        if (insert > 0) {
            BeanUtils.copyProperties(Delicacy, result);
        }
        return result;
    }

    private TravelDelicacyDTO save(TravelDelicacyDTO condition) {
        TravelDelicacy Delicacy = new TravelDelicacy();
        BeanUtils.copyProperties(condition, Delicacy);
        int insert = delicacyMapper.insert(Delicacy);
        TravelDelicacyDTO result = new TravelDelicacyDTO();
        BeanUtils.copyProperties(condition, result);
        if (insert > 0) {
            TravelDelicacy dbDelicacy = delicacyMapper.selectOne(Delicacy);
            BeanUtils.copyProperties(dbDelicacy, result);
        }
        return result;
    }

    @Override
    public TravelDelicacyDTO getDetails(TravelDelicacyDTO condition) {
        TravelDelicacy query = new TravelDelicacy();
        query.setIsDeleted(YesOrNoEnum.N.getCode());
        TravelDelicacy dbDelicacy;
        TravelDelicacyDTO result = new TravelDelicacyDTO();
        if (condition.getId() != null) {
            query.setId(condition.getId());
        } else if (condition.getTitleId() != null) {
            query.setTitleId(condition.getTitleId());
        } else {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(), "id不能为null");
        }
        dbDelicacy = delicacyMapper.selectOne(query);
        BeanUtils.copyProperties(dbDelicacy, result);
        return result;
    }

    @Override
    public TravelDelicacyDTO delete(TravelDelicacyDTO condition) {
        TravelDelicacyDTO result = new TravelDelicacyDTO();
        TravelDelicacy Delicacy = delicacyMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(Delicacy, result);

        Delicacy.setIsDeleted(YesOrNoEnum.Y.getCode());
        int insert = delicacyMapper.updateByPrimaryKeySelective(Delicacy);
        if (insert > 0) {
            BeanUtils.copyProperties(Delicacy, result);
        }
        return result;
    }
}
