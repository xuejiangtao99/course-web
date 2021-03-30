package com.server.service;

import com.server.domain.User;
import com.server.dto.UserDto;
import com.server.dto.PageDto;


public interface UserService {

    void list(PageDto pageDto);

    void save(UserDto userDto);

    void deleteById(String id);

    void resetPassword(String id);

    User selectByUserName(String userName);
}
