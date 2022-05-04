package com.cy.travels.service.impl;

import com.cy.travels.dao.TracelCollectionMapper;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.model.dto.TravelCollectionDTO;
import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.model.dto.TravelsTitleDTO;
import com.cy.travels.model.dto.UserDTO;
import com.cy.travels.model.entity.TravelCollection;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.TravelCollectionService;
import com.cy.travels.service.UserService;
import com.cy.travels.utils.RequestContextUtil;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class TravelCollectionServiceImpl implements TravelCollectionService {

    @Autowired
    private TracelCollectionMapper tracelCollectionMapper;

    @Resource
    private UserService userService;

    @Override
    public int save(TravelCollectionDTO condition) {
        User user = userService.getCurrentUser();
        condition.setUserId(user.getId());
        TravelCollection collection = new TravelCollection();
        BeanUtils.copyProperties(condition,collection);
        collection.setCreatedDate(new Date());
        collection.setIsDeleted(YesOrNoEnum.N.getCode());
        int insert = tracelCollectionMapper.insert(collection);
        return insert;
    }

//    @Override
//    public PageBean<TravelsTitleDTO> listPage(PageRequest<TravelCollectionDTO> request) {
//
//        TravelCollectionDTO query = request.getData();
//        String userStr = RequestContextUtil.getRequestHeader("header-user");
//        User user = new Gson().fromJson(userStr, User.class);
//        query.setUserId(user.getId());
//
//        PageHelper.startPage(request.getPageNum(),request.getPageSize());
//        List<TravelsTitleDTO> list = tracelCollectionMapper.getAllCollectionList(query);
//        for (TravelsTitleDTO travelsTitleDTO : list) {
//            travelsTitleDTO.setCreatorName(user.getUsername());
//            travelsTitleDTO.setCreatorCover(user.getHeadImg());
//        }
//        PageInfo pageInfo = new PageInfo(list);
//        PageBean pageBean = new PageBean();
//        pageBean.setPageSize(request.getPageSize());
//        pageBean.setCurrentPage(request.getPageNum());
//        pageBean.setData(pageInfo.getList());
//        pageBean.setTotalPage(pageInfo.getPages());
//        pageBean.setTotalCount(pageInfo.getSize());
//        return pageBean;
//    }

    @Override
    public PageBean<TravelCollectionDTO> listPage(PageRequest<TravelCollectionDTO> request) {

        TravelCollectionDTO query = request.getData();
        String userStr = RequestContextUtil.getRequestHeader("header-user");
        User user = new Gson().fromJson(userStr, User.class);
        query.setUserId(user.getId());

        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<TravelCollectionDTO> list = tracelCollectionMapper.getAllCollectionList(query);
        for (TravelCollectionDTO travelsTitleDTO : list) {
            travelsTitleDTO.setUsername(user.getUsername());
            travelsTitleDTO.setHeadImg(user.getHeadImg());
        }
        PageInfo pageInfo = new PageInfo(list);
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(request.getPageSize());
        pageBean.setCurrentPage(request.getPageNum());
        pageBean.setData(pageInfo.getList());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setTotalCount(pageInfo.getSize());
        return pageBean;
    }

    @Override
    public TravelCollectionDTO selectOne(TravelCollectionDTO condition) {
        User user = userService.getCurrentUser();
        condition.setUserId(user.getId());
        TravelCollection collection = new TravelCollection();
        BeanUtils.copyProperties(condition,collection);
        collection.setIsDeleted(YesOrNoEnum.N.getCode());
        collection.setId(null);
        TravelCollection selectOne = tracelCollectionMapper.selectOne(collection);
        BeanUtils.copyProperties(selectOne,condition);
        return condition;
    }

    @Override
    public int updata(TravelCollectionDTO condition) {
        TravelCollectionDTO selectOne = this.selectOne(condition);
        TravelCollection collection = new TravelCollection();
        BeanUtils.copyProperties(selectOne,collection);
        collection.setIsDeleted(YesOrNoEnum.Y.getCode());
        int updata = tracelCollectionMapper.updateByPrimaryKeySelective(collection);
        return updata;
    }

    @Override
    public Integer selectCount(TravelCollectionDTO condition) {
        TravelCollection query = new TravelCollection();
        BeanUtils.copyProperties(condition,query);
        query.setIsDeleted(YesOrNoEnum.N.getCode());
        query.setUserId(null);
        query.setId(null);
        int count = tracelCollectionMapper.selectCount(query);
        return count;
    }
}
