package com.cy.travels.service.impl;

import com.cy.travels.dao.*;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.*;
import com.cy.travels.model.entity.*;
import com.cy.travels.service.TravelService;
import com.cy.travels.service.UserService;
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
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelAccommodationMapper accommodationMapper;

    @Autowired
    private TravelDelicacyMapper delicacyMapper;

    @Autowired
    private TravelLocalOverviewMapper localOverviewMapper;

    @Autowired
    private TravelsTitleMapper titleMapper;

    @Autowired
    private TravelTrafficMapper trafficMapper;

    @Resource
    private UserService userService;

    @Override
    public TravelsTitleDTO submit(TravelsTitleDTO travelsTitleDTO) {
        TravelsTitleDTO result = this.saveOrUpdata(travelsTitleDTO);
        result.setPublishDate(new Date());
        TravelsTitle title = new TravelsTitle();
        BeanUtils.copyProperties(result,title);
        int num = titleMapper.updateByPrimaryKeySelective(title);
        if (num > 0) {
            TravelsTitleDTO resultDTO = new TravelsTitleDTO();
            BeanUtils.copyProperties(title,resultDTO);
            return resultDTO;
        }else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),"发布失败");
        }
    }

    @Override
    public TravelsTitleDTO save(TravelsTitleDTO travelsTitleDTO) {
        check(travelsTitleDTO);
        travelsTitleDTO.setCreatetime(new Date());
        TravelsTitle title = new TravelsTitle();
        BeanUtils.copyProperties(travelsTitleDTO,title);
        title.setIsDeleted(YesOrNoEnum.N.getCode());
        int num = titleMapper.insert(title);
        if (num > 0) {
            TravelsTitleDTO result = new TravelsTitleDTO();
            BeanUtils.copyProperties(title,result);
            return result;
        }else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),"保存失败");
        }
    }

    @Override
    public TravelsTitleDTO updata(TravelsTitleDTO travelsTitleDTO) {
        check(travelsTitleDTO);
        travelsTitleDTO.setCreatetime(new Date());
        TravelsTitle title = new TravelsTitle();
        BeanUtils.copyProperties(travelsTitleDTO,title);
        title.setIsDeleted(YesOrNoEnum.N.getCode());
        int num = titleMapper.updateByPrimaryKeySelective(title);
        if (num > 0) {
            TravelsTitleDTO result = new TravelsTitleDTO();
            BeanUtils.copyProperties(title,result);
            return result;
        }else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),"保存失败");
        }
    }

    @Override
    public List<TravelsTitleDTO> getAllTravelList() {
        Example example = new Example(Travels.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("isDeleted", YesOrNoEnum.N.getCode());
        example.setOrderByClause("publish_date DESC");
        List<TravelsTitle> result = titleMapper.selectByExample(example);
        List<TravelsTitleDTO> resultList = new ArrayList<>();
        for (TravelsTitle t : result) {
            TravelsTitleDTO titleDTO = new TravelsTitleDTO();
            BeanUtils.copyProperties(t,titleDTO);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(titleDTO.getCreator());
            UserDTO user = userService.findUser(userDTO);
            titleDTO.setCreatorName(user.getUsername());
            titleDTO.setCreatorCover(user.getHeadImg());
            resultList.add(titleDTO);
        }
        return resultList;
    }

    @Override
    public PageBean<TravelsTitleDTO> listPage(PageRequest<QueryTravelsDTO> request) {
        Example example = new Example(Travels.class);

        QueryTravelsDTO query = request.getData();
        addCondition(query,example);

        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<TravelsTitle> list = titleMapper.selectByExample(example);
        List<TravelsTitleDTO> resultList = new ArrayList<>();
        for (TravelsTitle t : list) {
            TravelsTitleDTO titleDTO = new TravelsTitleDTO();
            BeanUtils.copyProperties(t,titleDTO);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(titleDTO.getCreator());
            UserDTO user = userService.findUser(userDTO);
            titleDTO.setCreatorName(user.getUsername());
            titleDTO.setCreatorCover(user.getHeadImg());
            resultList.add(titleDTO);
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
    public TravelsDetailsDTO getDetails(TravelsTitleDTO travelsTitleDTO) {
        TravelsDetailsDTO result = new TravelsDetailsDTO();
        TravelsTitle title = titleMapper.selectByPrimaryKey(travelsTitleDTO.getId());
        TravelsTitleDTO titleDTO = new TravelsTitleDTO();
        BeanUtils.copyProperties(title,titleDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(titleDTO.getCreator());
        UserDTO user = userService.findUser(userDTO);
        titleDTO.setCreatorName(user.getUsername());
        titleDTO.setCreatorCover(user.getHeadImg());
        result.setTitle(titleDTO);

        //其他部分
        TravelTraffic queryTraffic = new TravelTraffic();
        queryTraffic.setIsDeleted(YesOrNoEnum.N.getCode());
        queryTraffic.setTitleId(title.getId());
        TravelTraffic travelTraffic = trafficMapper.selectOne(queryTraffic);
        TravelTrafficDTO travelTrafficDTO = new TravelTrafficDTO();
        BeanUtils.copyProperties(travelTraffic,travelTrafficDTO);
        result.setTraffic(travelTrafficDTO);

        TravelAccommodation queryAccommodation = new TravelAccommodation();
        queryAccommodation.setIsDeleted(YesOrNoEnum.N.getCode());
        queryAccommodation.setTitleId(title.getId());
        TravelAccommodation travelAccommodation = accommodationMapper.selectOne(queryAccommodation);
        TravelAccommodationDTO travelAccommodationDTO = new TravelAccommodationDTO();
        BeanUtils.copyProperties(travelAccommodation,travelAccommodationDTO);
        result.setAccommodation(travelAccommodationDTO);

        TravelDelicacy queryDelicacy = new TravelDelicacy();
        queryDelicacy.setIsDeleted(YesOrNoEnum.N.getCode());
        queryDelicacy.setTitleId(title.getId());
        TravelDelicacy travelDelicacy = delicacyMapper.selectOne(queryDelicacy);
        TravelDelicacyDTO travelDelicacyDTO = new TravelDelicacyDTO();
        BeanUtils.copyProperties(travelDelicacy,travelDelicacyDTO);
        result.setDelicacy(travelDelicacyDTO);

        TravelLocalOverview queryLocalOverview = new TravelLocalOverview();
        queryLocalOverview.setIsDeleted(YesOrNoEnum.N.getCode());
        queryLocalOverview.setTitleId(title.getId());
        TravelLocalOverview travelLocalOverview = localOverviewMapper.selectOne(queryLocalOverview);
        TravelLocalOverviewDTO travelLocalOverviewDTO = new TravelLocalOverviewDTO();
        BeanUtils.copyProperties(travelLocalOverview,travelLocalOverviewDTO);
        result.setLocalOverview(travelLocalOverviewDTO);


        return result;
    }

    @Override
    public TravelsTitleDTO saveOrUpdata(TravelsTitleDTO travelsTitleDTO) {
        if (null != travelsTitleDTO.getId()) {
            //暂存（修改）
            return this.updata(travelsTitleDTO);
        } else {
            //新增（保存）
            return this.save(travelsTitleDTO);
        }
    }

    private void check(TravelsTitleDTO request) {
        if (Objects.isNull(request.getTitleContent())) {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(),"景点地名不能为空！");
        }
    }

    private void addCondition(QueryTravelsDTO query, Example example) {
        Example.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(query.getId())) {
            criteria.andEqualTo("id",query.getId());
        }
        if (Objects.nonNull(query.getName())) {
            criteria.andLike("name", SqlUtils.like(query.getName()));
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
            List<Long> list = titleMapper.getTravelsIdList(query.getInfo());
            criteria.andIn("id",list);
        }
        criteria.andLike("isDeleted", YesOrNoEnum.N.getCode());
        example.setOrderByClause("publish_date DESC");

    }
}
