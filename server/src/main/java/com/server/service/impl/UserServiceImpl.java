package com.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.domain.User;
import com.server.dto.UserDto;
import com.server.dto.PageDto;
import com.server.example.UserExample;
import com.server.exception.BusinessException;
import com.server.exception.BusinessExceptionCode;
import com.server.mapper.UserMapper;
import com.server.service.UserService;
import com.server.utils.CopyUtil;
import com.server.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Date;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        List<UserDto> list = CopyUtil.copyList(users, UserDto.class);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setList(list);
    }


    public void save(UserDto userDto){
        User user = CopyUtil.copyObject(userDto,User.class);
        if(StringUtils.isEmpty(userDto.getId())){
            this.insert(user);
        }else{
            this.update(user);
        }
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    private void insert(User user){
        Date date = new Date();
        user.setId(UuidUtil.getShortUuid());
        User userDb = selectByUserName(user.getName());
        if(userDb != null){
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);

    }

    private void update(User user){

        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user); //selective 会自动进行非空判断,如果有空值,则不更新
    }


    @Override
    public void resetPassword(String id) {

        User user = new User();
        user.setId(id);
        user.setPassword("04d53ca3e28a522a4ddf8b07428b4599");
        userMapper.updateByPrimaryKeySelective(user);
    }



    /***
     * 根据登录名查用户信息
     * @author XueJiangTao
     * @date 2021/3/29
     * @param [userName]
     * @return com.server.domain.User
     */
    public User selectByUserName(String userName){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0){
            return null;
        }
        return users.get(0);
    }
}
