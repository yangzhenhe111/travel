package com.cy.travels.service.impl;

import com.cy.travels.dao.UserMapper;
import com.cy.travels.enums.LoginStatusEnum;
import com.cy.travels.enums.ResultEnum;
import com.cy.travels.enums.YesOrNoEnum;
import com.cy.travels.exception.BusinessException;
import com.cy.travels.model.dto.UserDTO;
import com.cy.travels.model.entity.User;
import com.cy.travels.service.UserService;
import com.cy.travels.utils.RedisUtil;
import com.cy.travels.utils.RequestContextUtil;
import com.cy.travels.utils.dto.Result;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDTO findUser(UserDTO userDTO) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(userDTO.getId()) && userDTO.getId() > 0) {
            criteria.andEqualTo("id", userDTO.getId());
        }
        if (Objects.nonNull(userDTO.getEmail())) {
            criteria.andEqualTo("email", userDTO.getEmail());
        }
//        if (Objects.nonNull(userDTO.getPassword())) {
//            criteria.andEqualTo("password",userDTO.getPassword());
//        }
        if (Objects.nonNull(userDTO.getUsername())) {
            criteria.andEqualTo("username", userDTO.getUsername());
        }
        if (Objects.nonNull(userDTO.getTel())) {
            criteria.andEqualTo("tel", userDTO.getTel());
        }
        criteria.andEqualTo("isDeleted", YesOrNoEnum.N.getCode());
        User user = userMapper.selectOneByExample(example);
        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(user, result);
        return result;
    }

    @Override
    public int register(UserDTO userDTO) {
        if (Objects.isNull(userDTO.getPassword())) {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(), "password不能为null");
        }
        if (Objects.isNull(userDTO.getUsername())) {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(), "username不能为null");
        }
        if (Objects.isNull(userDTO.getTel())) {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(), "tel不能为null");
        }
        if (Objects.isNull(userDTO.getEmail())) {
            throw new BusinessException(ResultEnum.IS_NOT_NULL.getCode(), "email不能为null");
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User query = new User();
        query.setIsDeleted(YesOrNoEnum.N.getCode());
        query.setUsername(userDTO.getUsername());
        int count = userMapper.selectCount(query);
        if (count > 0) {
            throw new BusinessException(ResultEnum.UNKNOWN.getCode(), "账号已存在");
        }
        User query1 = new User();
        query1.setIsDeleted(YesOrNoEnum.N.getCode());
        query1.setEmail(userDTO.getEmail());
        int count1 = userMapper.selectCount(query1);
        if (count1 > 0) {
            throw new BusinessException(ResultEnum.UNKNOWN.getCode(), "邮箱已存在");
        }
        User query2 = new User();
        query2.setIsDeleted(YesOrNoEnum.N.getCode());
        query2.setTel(userDTO.getTel());
        int count2 = userMapper.selectCount(query2);
        if (count2 > 0) {
            throw new BusinessException(ResultEnum.UNKNOWN.getCode(), "手机号已存在");
        }

//        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
//        user.setPassword(md5Password);
        user.setCreatetime(new Date());
        user.setTotalPoints(0L);
        user.setHeadImg("tx0.jpg");
        user.setIsDeleted(YesOrNoEnum.N.getCode());

        return userMapper.insert(user);
    }

    @Override
    public UserDTO editSaveUserInfo(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        int num = userMapper.updateByPrimaryKeySelective(user);
        if (num > 0) {
            User resultUser = userMapper.selectByPrimaryKey(user.getId());
            UserDTO result = new UserDTO();
            BeanUtils.copyProperties(resultUser, result);
            return result;
        } else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), "保存信息失败");
        }
    }

    @Override
    public UserDTO updatePwd(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User userData = userMapper.selectByPrimaryKey(user.getId());
        userData.setPassword(user.getPassword());
        int num = userMapper.updateByPrimaryKeySelective(userData);

        if (num > 0) {
            User resultUser = userMapper.selectByPrimaryKey(user.getId());
            UserDTO result = new UserDTO();
            BeanUtils.copyProperties(resultUser, result);
            return result;
        } else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), "更新密码失败");
        }
    }

    @Override
    public UserDTO updateHeadImg(UserDTO userDTO) {

        String userStr = RequestContextUtil.getRequestHeader("header-user");

        User user = new Gson().fromJson(userStr, User.class);
        User userData = userMapper.selectByPrimaryKey(user.getId());
        userData.setHeadImg(userDTO.getHeadImg());
        int num = userMapper.updateByPrimaryKeySelective(userData);

        if (num > 0) {
            User resultUser = userMapper.selectByPrimaryKey(user.getId());
            UserDTO result = new UserDTO();
            BeanUtils.copyProperties(resultUser, result);
            return result;
        } else {
            throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), "更新头像失败");
        }
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = new User();
//        String md5Password = DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes());
//        userDTO.setPassword(md5Password);
        UserDTO findUser = this.findUser(userDTO);
        if (null != findUser) {
            BeanUtils.copyProperties(findUser, user);
            user.setLoginStatus(LoginStatusEnum.LOGIN.getCode());
            int num = userMapper.updateByPrimaryKeySelective(user);
            if (num > 0) {
                return findUser;
            } else {
                throw new BusinessException(ResultEnum.INTERNAL_SERVER_ERROR.getCode(), "修改登录状态错误");
            }
        }

        return null;
    }

    @Override
    public List<User> getLoginUserList() {
        User user = new User();
        user.setLoginStatus(LoginStatusEnum.LOGIN.getCode());
        return userMapper.select(user);
    }

    @Override
    public User getCurrentUser() {
        log.info("[header-user]:" + request.getHeader("header-user"));
        String header = request.getHeader("header-user");
        //检查请求header的信息是否存在
        if (Objects.nonNull(header)) {
            //存在
            User headerUser = new Gson().fromJson(header, User.class);

            LinkedHashMap<String, Object> redisUser = (LinkedHashMap<String, Object>) redisUtil.get("user-" + headerUser.getId());
            log.info("[redis]:" + redisUser.toString());

            String redisUserId = String.valueOf(redisUser.get("id"));
            String headerUserId = String.valueOf(headerUser.getId());
            if (redisUserId.equals(headerUserId)) {
                return headerUser;
            }
        }
        throw new BusinessException(ResultEnum.UNAUTHORIZED.getCode(), "用户还未登陆，请登录");
    }
}
