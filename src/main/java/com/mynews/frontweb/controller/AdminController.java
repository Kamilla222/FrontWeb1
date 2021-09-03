package com.mynews.frontweb.controller;
import com.mynews.frontweb.config.Role;
import com.mynews.frontweb.model.Users;
import com.mynews.frontweb.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AdminController {

// так делать нельзя, должно быть MyService
    private final UserServiceImpl myService;

    @GetMapping("/admin")
    public String getNews() {
        return "newsAdmin";
    }

    @GetMapping("/ad")
    public String auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> currentPrincipalName =
                (Collection<GrantedAuthority>) authentication.getAuthorities();
        if (new ArrayList<>(currentPrincipalName).stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList())
            .contains("admin:read")) {
            return "redirect:/userslist";
        }
        return "redirect:/";
    }

    @GetMapping("/userslist")
    public String getUsers(ModelMap modelMap) {
        List<Users> usersList = myService.findAllByRole(Role.valueOf("USER"));
        modelMap.addAttribute("usersList", usersList);
        return "userlist";
    }

    @PostMapping("/save-user")
    public String updateUsers(@RequestParam(name = "id") String id,
                              @RequestParam(name = "firstname") String firstname,
                              @RequestParam(name = "lastname") String lastname,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "role") String role
    ) {
        String[] idMas = id.split(",");
        String[] nameMas = firstname.split(",");
        String[] surnameMas = lastname.split(",");
        String[] emailMas = email.split(",");
        String[] roleMas = role.split(",");
        List<Users> usersList = new ArrayList<>();
        for (int i = 0; i < idMas.length; i++) {
            usersList.add(
                    Users.builder()
                            .id(Long.parseLong(idMas[i]))
                            .firstname(nameMas[i])
                            .lastname(surnameMas[i])
                            .email(emailMas[i])
                            .role(Role.valueOf(roleMas[i]))
                            .build()
            );
        }
        myService.saveBatch(usersList);
        return "redirect://admin";
    }


}
