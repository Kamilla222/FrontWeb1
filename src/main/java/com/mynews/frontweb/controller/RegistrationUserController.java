package com.mynews.frontweb.controller;

import com.mynews.frontweb.model.dto.UserDto;
import com.mynews.frontweb.exception.UserAlreadyExistException;
import com.mynews.frontweb.model.Users;
import com.mynews.frontweb.service.impl.UserServiceRegistrationImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RegistrationUserController {


    private final UserServiceRegistrationImpl userServiceRegistration;

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        model.addAttribute("userAlreadyExist", Optional.empty());
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(ModelMap modelMap, UserDto userDto) {
        try{
            Users registeredUser = userServiceRegistration.registerNewUserAccount(userDto);
            // FIXME: 23.08.2021 Проверить работоспособность данного атрибута
            modelMap.addAttribute("registeredUser", registeredUser);
        } catch (UserAlreadyExistException userAlreadyExistException) {

            modelMap.addAttribute("userAlreadyExist",true);
            modelMap.addAttribute("user", new UserDto());
            return "registration";
        }
        return "redirect:/login";
    }
    }


