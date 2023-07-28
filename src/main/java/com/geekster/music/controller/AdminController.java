package com.geekster.music.controller;

import com.geekster.music.model.Admin;
import com.geekster.music.model.Song;
import com.geekster.music.model.dto.SignInInput;
import com.geekster.music.model.dto.SignUpOutput;
import com.geekster.music.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService  adminService;



    @PostMapping("signup/admin")
    public SignUpOutput signUpAdmin(@RequestBody @Valid Admin admin){
        return adminService.signUpAdmin(admin);
    }

    @PostMapping("signIn/admin")
    public String signInInput(@RequestBody @Valid SignInInput signInInput){
        return adminService.signInAdmin(signInInput);
    }


    @DeleteMapping("SignOut/admin")
    public String signOutAdmin(@RequestParam String email, @RequestParam String token){
        return  adminService.signOutAdmin(email, token);
    }

    @PostMapping("add/song")
    public String addSong( @RequestParam @Valid String  adminEmail ,@RequestBody Song song){
        return  adminService.addSong(song , adminEmail);
    }

    @DeleteMapping("delete/song")
    public String deleteSong(@RequestParam String email, @RequestParam String token, @RequestParam Integer songId){
        return  adminService.deleteSong(email, token , songId);
    }

    @PutMapping("update/song")
    public String updateSong(@RequestParam String email, @RequestParam String token, @RequestParam Integer songId, @RequestParam String songName,@RequestParam String singer){
        return  adminService.updateSong(email, token, songId, singer, songName);
    }




}
