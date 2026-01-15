package com.tk.user.repo;

import com.tk.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <User, Long> {
    Optional<User> findUserByfName(String fName);
}
