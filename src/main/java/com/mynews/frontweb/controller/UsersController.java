package com.mynews.frontweb.controller;

import com.mynews.frontweb.model.Users;
import com.mynews.frontweb.service.FrontWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final FrontWebService<Users,Long> myService;

    @GetMapping("/users")
    public String getUsers(ModelMap modelMap) {
        List<Users> usersList = myService.findAll();
        modelMap.addAttribute("userList", usersList);
        return "users";

    }

}
