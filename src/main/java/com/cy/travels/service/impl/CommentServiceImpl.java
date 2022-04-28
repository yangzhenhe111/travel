package com.cy.travels.service.impl;

import com.cy.travels.dao.CommentMapper;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.CommentDTO;
import com.cy.travels.model.dto.CommentRespDTO;
import com.cy.travels.model.dto.TravelsDTO;
import com.cy.travels.model.dto.UserDTO;
import com.cy.travels.model.entity.Comment;
import com.cy.travels.model.entity.Travels;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.CommentService;
import com.cy.travels.service.TravelsService;
import com.cy.travels.service.UserService;
import com.cy.travels.utils.RedisUtil;
import com.cy.travels.utils.RequestContextUtil;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int like(CommentDTO commentDTO) {
        check(commentDTO);
        Comment comment = commentMapper.selectByPrimaryKey(commentDTO.getId());
        comment.setLikeNum(comment.getLikeNum()+1);

        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    private void check(CommentDTO request) {
        if (Objects.isNull(request.getId())) {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(),"ID不能为空！");
        }
    }

    /**
     * 对于获取评论，初始界面可只获取最简单的评论，随后点进去之后，还有二级评论列表
     * @param request
     * @return
     */
    @Override
    public PageBean<CommentRespDTO> listPage(PageRequest<CommentDTO> request) {
        Example example = new Example(Comment.class);
        CommentDTO query = request.getData();
        addCondition(query,example);
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Comment> list = commentMapper.selectByExample(example);

        List<CommentRespDTO> result = new ArrayList<>();
        for (Comment c : list) {
            CommentRespDTO commentResp = new CommentRespDTO();
            BeanUtils.copyProperties(c,commentResp);
//            //获取列表时，若是二级评论列表，且并不是一级评论的子评论，则在相应评论上加上父级评论
//            if (query.getParentId() != null) {
//                if (!query.getParentId().equals(c.getParentId())) {
//                    Comment comment = commentMapper.selectByPrimaryKey(query.getParentId());
//                    CommentDTO commentDTO = new CommentDTO();
//                    BeanUtils.copyProperties(c,commentDTO);
//                    commentResp.setParentComment(commentDTO);
//                }
//            }
            result.add(commentResp);
        }
        PageInfo pageInfo = new PageInfo(list);
        PageBean pageBean = new PageBean();
        pageBean.setData(result);
        pageBean.setCurrentPage(request.getPageNum());
        pageBean.setPageSize(request.getPageSize());
        pageBean.setTotalPage(pageInfo.getPages());
        pageBean.setTotalCount(list.size());
        return pageBean;
    }

    private void addCondition(CommentDTO query, Example example) {
        Example.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(query.getId())) {
            criteria.andEqualTo("id",query.getId());
        }
        if (Objects.nonNull(query.getTravelsId())) {
            criteria.andEqualTo("travelsId",query.getTravelsId());
        }
        if (Objects.nonNull(query.getParentId())) {
            criteria.andEqualTo("parentId", query.getParentId());
        }
        criteria.andLike("isDeleted", YesOrNoEnum.N.getCode());
        example.setOrderByClause("create_time DESC");

    }

    @Override
    public int save(CommentDTO commentDTO) {
        UserDTO requstUser = new UserDTO();
        requstUser.setId(commentDTO.getUserId());
        UserDTO user = userService.findUser(requstUser);
        commentDTO.setUsername(user.getUsername());
        commentDTO.setHeadImg(user.getHeadImg());
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setLikeNum(0L);
        comment.setCreateTime(new Date());
        comment.setIsDeleted(YesOrNoEnum.N.getCode());
        int num = commentMapper.insert(comment);
        return num;
    }
}
