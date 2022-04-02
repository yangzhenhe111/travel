package com.cy.travels.service.impl;

import com.cy.travels.dao.TravelsMapper;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.QueryTravelsDTO;
import com.cy.travels.model.dto.TravelsDTO;
import com.cy.travels.model.dto.UserDTO;
import com.cy.travels.model.entity.Travels;
import com.cy.travels.service.TravelsService;
import com.cy.travels.service.UserService;
import com.cy.travels.utils.Constant;
import com.cy.travels.utils.SqlUtils;
import com.cy.travels.utils.dto.PageBean;
import com.cy.travels.utils.dto.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class TravelsServiceImpl implements TravelsService {
    @Autowired
    private TravelsMapper travelsMapper;

    @Resource
    private UserService userService;

    @Override
    public TravelsDTO submit(TravelsDTO travelsDTO) {
        TravelsDTO result = this.saveOrUpdata(travelsDTO);
        result.setPublishDate(new Date());
        Travels travels = new Travels();
        BeanUtils.copyProperties(result,travels);
        int num = travelsMapper.updateByPrimaryKeySelective(travels);
        if (num > 0) {
            TravelsDTO resultDTO = new TravelsDTO();
            BeanUtils.copyProperties(travels,resultDTO);
            return resultDTO;
        }else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),"发布失败");
        }
    }

    @Override
    public TravelsDTO save(TravelsDTO travelsDTO) {
        this.check(travelsDTO);
        travelsDTO.setCreatetime(new Date());
        Travels travels = new Travels();
        BeanUtils.copyProperties(travelsDTO,travels);
        travels.setIsDeleted(YesOrNoEnum.N.getCode());
        int num = travelsMapper.insert(travels);
        if (num > 0) {
            TravelsDTO result = new TravelsDTO();
            BeanUtils.copyProperties(travels,result);
            return result;
        }else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),"保存失败");
        }
    }

    @Override
    public TravelsDTO updata(TravelsDTO travelsDTO) {
        this.check(travelsDTO);
        Travels travels = new Travels();
        BeanUtils.copyProperties(travelsDTO,travels);
        int num = travelsMapper.updateByPrimaryKeySelective(travels);
        if (num > 0) {
            TravelsDTO result = new TravelsDTO();
            BeanUtils.copyProperties(travels,result);
            return result;
        }else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),"修改失败");
        }
    }

    private void check(TravelsDTO request) {
        if (Objects.isNull(request.getName())) {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(),"景点地名不能为空！");
        }
    }

    @Override
    public List<TravelsDTO> getAllTravelList() {

        Example example = new Example(Travels.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("isDeleted", YesOrNoEnum.N.getCode());
        example.setOrderByClause("publish_date DESC");
        List<Travels> result = travelsMapper.selectByExample(example);
        List<TravelsDTO> resultList = new ArrayList<>();
        for (Travels t : result) {
            TravelsDTO travelsDTO = new TravelsDTO();
            BeanUtils.copyProperties(t,travelsDTO);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(travelsDTO.getCreator());
            UserDTO user = userService.findUser(userDTO);
            travelsDTO.setCreatorName(user.getUsername());
            travelsDTO.setCreatorCover(user.getHeadImg());
            resultList.add(travelsDTO);
        }
        return resultList;
    }

    private void addCondition(QueryTravelsDTO query, Example example) {
        Example.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(query.getId())) {
            criteria.andEqualTo("id",query.getId());
        }
        if (Objects.nonNull(query.getName())) {
            criteria.andLike("name",SqlUtils.like(query.getName()));
        }
        if (Objects.nonNull(query.getAddress())) {
            criteria.andLike("address", SqlUtils.like(query.getAddress()));
        }
        if (Objects.nonNull(query.getBriefDesc())) {
            criteria.andLike("briefDesc",SqlUtils.like(query.getBriefDesc()));
        }
        if (Objects.nonNull(query.getCreator())) {
            criteria.andEqualTo("creator",query.getCreator());
        }
        if (Objects.nonNull(query.getOpentimeStart()) && Objects.nonNull(query.getOpentimeEnd())) {
            criteria.andBetween("opentime",query.getOpentimeStart(),query.getOpentimeEnd());
        }
        if (Objects.nonNull(query.getPublishDateStart()) && Objects.nonNull(query.getPublishDateEnd())) {
            criteria.andBetween("publishDate",query.getPublishDateStart(),query.getPublishDateEnd());
        }
        if (Objects.nonNull(query.getBudgetMax()) && Objects.nonNull(query.getBudgetMin())) {
            criteria.andBetween("budge",query.getBudgetMin(),query.getBudgetMax());
        }
        if (Objects.nonNull(query.getInfo())) {
            List<Long> list = travelsMapper.getTravelsIdList(query.getInfo());
            criteria.andIn("id",list);
        }
        criteria.andLike("isDeleted", YesOrNoEnum.N.getCode());
        example.setOrderByClause("publish_date DESC");

    }

    @Override
    public PageBean<TravelsDTO> listPage(PageRequest<QueryTravelsDTO> request) {
        Example example = new Example(Travels.class);

        QueryTravelsDTO query = request.getData();
        addCondition(query,example);

        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Travels> list = travelsMapper.selectByExample(example);
        List<TravelsDTO> resultList = new ArrayList<>();
        for (Travels t : list) {
            TravelsDTO travelsDTO = new TravelsDTO();
            BeanUtils.copyProperties(t,travelsDTO);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(travelsDTO.getCreator());
            UserDTO user = userService.findUser(userDTO);
            travelsDTO.setCreatorName(user.getUsername());
            travelsDTO.setCreatorCover(user.getHeadImg());
            resultList.add(travelsDTO);
        }
        PageInfo pageInfo = new PageInfo(list);
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(request.getPageNum());
        pageBean.setPageSize(request.getPageSize());
        pageBean.setData(resultList);
        pageBean.setTotalCount(pageInfo.getSize());
        pageBean.setTotalPage(pageInfo.getPages());
        return pageBean;
    }

    @Override
    public TravelsDTO getDetails(TravelsDTO travelsDTO) {

        Travels travels = travelsMapper.selectByPrimaryKey(travelsDTO.getId());
        TravelsDTO resultDTO = new TravelsDTO();
        BeanUtils.copyProperties(travels,resultDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(resultDTO.getCreator());
        UserDTO user = userService.findUser(userDTO);
        resultDTO.setCreatorName(user.getUsername());
        resultDTO.setCreatorCover(user.getHeadImg());
        return resultDTO;
    }

    @Override
    public TravelsDTO saveOrUpdata(TravelsDTO travelsDTO) {
        if (null != travelsDTO.getId()) {
            //暂存（修改）
            return this.updata(travelsDTO);
        } else {
            //新增（保存）
            return this.save(travelsDTO);
        }
    }
}
