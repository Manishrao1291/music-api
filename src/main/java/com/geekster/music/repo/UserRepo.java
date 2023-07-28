package com.geekster.music.repo;

import com.geekster.music.model.Admin;
import com.geekster.music.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);


    User findFirstByuserEmail(String signInEmail);
}
