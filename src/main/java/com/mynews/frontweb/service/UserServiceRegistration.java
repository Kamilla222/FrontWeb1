package com.mynews.frontweb.service;

import com.mynews.frontweb.model.dto.UserDto;
import com.mynews.frontweb.exception.UserAlreadyExistException;
import com.mynews.frontweb.model.Users;

public interface UserServiceRegistration {

    Users registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;
}
