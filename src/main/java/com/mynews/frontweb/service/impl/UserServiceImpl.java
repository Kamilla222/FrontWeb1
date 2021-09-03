package com.mynews.frontweb.service.impl;

import com.mynews.frontweb.model.Users;
import com.mynews.frontweb.repository.UsersRepository;
import com.mynews.frontweb.service.FrontWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.mynews.frontweb.config.Role;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements FrontWebService<Users,Long> {

    private final JdbcTemplate jdbcTemplate;
    private final UsersRepository usersRepository;
    private final String sqlUpdate = "update users set firstname = ?, lastname = ?, email = ?, role = ? where id = ?";

    public List<Users> findAll() {
        return usersRepository.findAll();
    }


    public void saveBatch(List<Users> usersList) {
        int [] updateUsers = jdbcTemplate.batchUpdate(sqlUpdate,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, usersList.get(i).getFirstname());
                        ps.setString(2,usersList.get(i).getLastname());
                        ps.setString(3,usersList.get(i).getEmail());
                        ps.setString(4, usersList.get(i).getRole().name());
                        ps.setLong(5,usersList.get(i).getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return usersList.size();
                    }
                });
    }

    public List<Users> findAllByRole(Role role) {
        return usersRepository.findAllByRole(role);
    }
}

