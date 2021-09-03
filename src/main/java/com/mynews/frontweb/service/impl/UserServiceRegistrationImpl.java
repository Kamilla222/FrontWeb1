package com.mynews.frontweb.service.impl;

import com.mynews.frontweb.config.Role;
import com.mynews.frontweb.model.dto.UserDto;
import com.mynews.frontweb.exception.UserAlreadyExistException;
import com.mynews.frontweb.model.Users;
import com.mynews.frontweb.repository.UsersRepository;
import com.mynews.frontweb.service.UserServiceRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceRegistrationImpl implements UserServiceRegistration {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException{
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }
        Users user= new Users();
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(Role.USER);
        return usersRepository.save(user);
    }

    private boolean emailExist(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
}
