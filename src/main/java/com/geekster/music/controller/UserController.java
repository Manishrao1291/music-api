package com.geekster.music.controller;

import com.geekster.music.model.Admin;
import com.geekster.music.model.Playlist;
import com.geekster.music.model.User;
import com.geekster.music.model.dto.SignInInput;
import com.geekster.music.model.dto.SignUpOutput;
import com.geekster.music.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("signup/user")
    public SignUpOutput signUpUser(@RequestBody @Valid User user){
         return userService.signUpUser(user);
    }


    @PostMapping("signIn/user")
    public String signInInput(@RequestBody @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    @PostMapping("playlist/create")
    public String createPlaylist(@RequestBody @Valid Playlist playlist, @RequestParam String email, @RequestParam  String token){
        return  userService.createPlaylist(playlist,email, token);
    }

    @GetMapping("playlists")
    public List<Playlist> getPlaylists(@RequestParam String email){
        return userService.getPlaylists(email);
    }

    @DeleteMapping("signOut/user")
    public String signOutAdmin(@RequestParam String email, @RequestParam String token){
        return userService.signOutUser(email,token);
    }


    @PutMapping("update/playlist")
    public String addSongToPlaylist(@RequestParam Integer playlistId,@RequestParam List<Integer> songId,@RequestParam String  email,@RequestParam String token ){
        return userService.addSongToPlaylist(playlistId,songId,email,token);
    }
}
