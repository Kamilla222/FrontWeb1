package com.mynews.frontweb.service;

import com.mynews.frontweb.config.Role;
import com.mynews.frontweb.model.Users;

import java.util.List;

public interface UserService extends FrontWebService {
    List<Users> findAllByRole(Role role);
    void saveBatch(List<Users> usersList);
}
