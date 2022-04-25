package com.cy.travels.service.impl;

import com.cy.travels.dao.TravelAccommodationMapper;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.TravelAccommodationDTO;
import com.cy.travels.model.entity.TravelAccommodation;
import com.cy.travels.service.TravelAccommodationService;
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
public class TravelAccommodationServiceImpl implements TravelAccommodationService {

    @Autowired
    private TravelAccommodationMapper accommodationMapper;

    @Override
    public TravelAccommodationDTO saveOrUpdata(TravelAccommodationDTO condition) {
        if (Objects.isNull(condition.getId())) {
            //保存
            return save(condition);
        }else {
            //修改
            return update(condition);
        }
    }

    private TravelAccommodationDTO update(TravelAccommodationDTO condition) {
        TravelAccommodationDTO result = new TravelAccommodationDTO();
        TravelAccommodation Accommodation = accommodationMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(Accommodation,result);

        BeanUtils.copyProperties(condition,Accommodation);
        int insert = accommodationMapper.updateByPrimaryKeySelective(Accommodation);
        if (insert > 0) {
            BeanUtils.copyProperties(Accommodation,result);
        }
        return result;
    }

    private TravelAccommodationDTO save(TravelAccommodationDTO condition) {
        TravelAccommodation Accommodation = new TravelAccommodation();
        BeanUtils.copyProperties(condition,Accommodation);
        int insert = accommodationMapper.insert(Accommodation);
        TravelAccommodationDTO result = new TravelAccommodationDTO();
        BeanUtils.copyProperties(condition,result);
        if (insert > 0) {
            TravelAccommodation dbAccommodation = accommodationMapper.selectOne(Accommodation);
            BeanUtils.copyProperties(dbAccommodation,result);
        }
        return result;
    }

    @Override
    public TravelAccommodationDTO getDetails(TravelAccommodationDTO condition) {
        TravelAccommodation query = new TravelAccommodation();
        query.setIsDeleted(YesOrNoEnum.N.getCode());
        TravelAccommodation dbAccommodation;
        TravelAccommodationDTO result = new TravelAccommodationDTO();
        if (condition.getId() != null) {
            query.setId(condition.getId());
        }else if (condition.getTitleId() != null){
            query.setTitleId(condition.getTitleId());
        }else {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(),"id不能为null");
        }
        dbAccommodation = accommodationMapper.selectOne(query);
        BeanUtils.copyProperties(dbAccommodation,result);
        return result;
    }

    @Override
    public TravelAccommodationDTO delete(TravelAccommodationDTO condition) {
        TravelAccommodationDTO result = new TravelAccommodationDTO();
        TravelAccommodation Accommodation = accommodationMapper.selectByPrimaryKey(condition.getId());
        BeanUtils.copyProperties(Accommodation,result);

        Accommodation.setIsDeleted(YesOrNoEnum.Y.getCode());
        int insert = accommodationMapper.updateByPrimaryKeySelective(Accommodation);
        if (insert > 0) {
            BeanUtils.copyProperties(Accommodation,result);
        }
        return result;
    }
}
