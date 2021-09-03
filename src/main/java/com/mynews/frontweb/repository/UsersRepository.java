package com.mynews.frontweb.repository;

import com.mynews.frontweb.model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mynews.frontweb.config.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

    List<Users> findAllByRole(Role role);


}
