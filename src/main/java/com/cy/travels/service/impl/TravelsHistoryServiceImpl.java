package com.cy.travels.service.impl;

import com.cy.travels.dao.TravelsHistoryMapper;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.model.dto.TravelsHistoryDTO;
import com.cy.travels.model.dto.TravelsTitleDTO;
import com.cy.travels.model.dto.UserDTO;
import com.cy.travels.model.entity.TravelsHistory;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.TravelsHistoryService;
import com.cy.travels.service.UserService;
import com.cy.travels.utils.RequestContextUtil;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class TravelsHistoryServiceImpl implements TravelsHistoryService {

    @Autowired
    private TravelsHistoryMapper travelsHistoryMapper;

    @Resource
    private UserService userService;

    @Override
    public TravelsHistoryDTO save(TravelsHistoryDTO travelsHistoryDTO) {

        TravelsHistory travelsHistory = new TravelsHistory();
        BeanUtils.copyProperties(travelsHistoryDTO,travelsHistory);
        travelsHistory.setCreateTime(new Date());
        travelsHistory.setIsDeleted(YesOrNoEnum.N.getCode());
        travelsHistoryMapper.insert(travelsHistory);
        //将id添到返回值
//        TravelsHistory resultDO =  travelsHistoryMapper.selectByPrimaryKey(travelsHistory.getId());
//        TravelsHistoryDTO result = new TravelsHistoryDTO();
//        BeanUtils.copyProperties(resultDO,result);
        return travelsHistoryDTO;
    }

//    @Override
//    public PageBean<TravelsTitleDTO> listPage(PageRequest<TravelsHistoryDTO> request) {
//
//        TravelsHistoryDTO query = request.getData();
//
//        if (Objects.isNull(query.getUserId())) {
//            String userStr = RequestContextUtil.getRequestHeader("header-user");
//            User user = new Gson().fromJson(userStr, User.class);
//            query.setUserId(user.getId());
//        }
//        PageHelper.startPage(request.getPageNum(),request.getPageSize());
//        List<TravelsTitleDTO> list = travelsHistoryMapper.getAllHistoryList(query);
//        for (TravelsTitleDTO travelsTitleDTO : list) {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(travelsTitleDTO.getCreator());
//            UserDTO user = userService.findUser(userDTO);
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
    public PageBean<TravelsHistoryDTO> listPage(PageRequest<TravelsHistoryDTO> request) {

        TravelsHistoryDTO query = request.getData();
        String userStr = RequestContextUtil.getRequestHeader("header-user");
        User user = new Gson().fromJson(userStr, User.class);
        query.setUserId(user.getId());
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<TravelsHistoryDTO> list = travelsHistoryMapper.getAllHistoryList(query);
        for (TravelsHistoryDTO travelsHistoryDTO : list) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(travelsHistoryDTO.getUserId());
            UserDTO user1 = userService.findUser(userDTO);
            travelsHistoryDTO.setUsername(user1.getUsername());
            travelsHistoryDTO.setHeadImg(user1.getHeadImg());
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
}
