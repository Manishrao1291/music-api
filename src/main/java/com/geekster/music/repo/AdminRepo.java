package com.geekster.music.repo;

import com.geekster.music.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Admin findFirstByAdminEmail(String newEmail);
}
