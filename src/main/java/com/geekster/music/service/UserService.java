package com.geekster.music.service;

import com.geekster.music.model.Admin;
import com.geekster.music.model.Playlist;
import com.geekster.music.model.User;
import com.geekster.music.model.dto.SignInInput;
import com.geekster.music.model.dto.SignUpOutput;
import com.geekster.music.repo.UserRepo;
import com.geekster.music.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PlaylistService playlistService;


    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = false;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();
        if(newEmail==null){
            signUpStatusMessage = "Email cannot be empty!!";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        User existingUser = userRepo.findFirstByUserEmail(newEmail);
        if(existingUser!=null){
            signUpStatusMessage = "Email is already registered!!";
            return  new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        try {
            String encryptPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
            user.setUserPassword(encryptPassword);
            userRepo.save(user);
            signUpStatus = true;
            signUpStatusMessage = "user added successfully";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);

        } catch (Exception e) {
            signUpStatusMessage = "Internal server error";
            return new SignUpOutput(false,signUpStatusMessage);
        }

    }

    public String signInUser(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();
        if(signInEmail==null){
            signInStatusMessage = "Email cannot be empty!!!";
            return signInStatusMessage;
        }
         User existingUser = userRepo.findFirstByUserEmail(signInEmail);
        if(existingUser==null){
            signInStatusMessage = "Email is not registered!!!";
            return signInStatusMessage;
        }

        try {
            String encryptPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(encryptPassword.equals(existingUser.getUserPassword())){
                return authenticationService.createToken(existingUser);
            }
            else{
                signInStatusMessage = "invalid credentials";
                return signInStatusMessage;
            }
        } catch (NoSuchAlgorithmException e) {
            signInStatusMessage = "internal error occurred";
            return signInStatusMessage;
        }

    }

    public String createPlaylist(Playlist playlist,String email,String token) {
        String signOutStatusMessage = null;
        if(email==null){
            signOutStatusMessage = "Email cannot be empty.";
            return signOutStatusMessage;
        }
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser==null){
            signOutStatusMessage = "Not a valid email";
            return signOutStatusMessage;
        }
        playlist.setUser(existingUser);
        return authenticationService.checkUser(playlist,token);
    }

    public List<Playlist> getPlaylists(String email) {
        if(email==null){
            return null;
        }
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser==null){
            return null;
        }
        return playlistService.getPlaylist(existingUser);
    }





    public String addSongToPlaylist(Integer playlistId, List<Integer> songId, String email, String token) {
        String signOutStatusMessage = null;
        if(email==null){
            signOutStatusMessage = "Email cannot be empty.";
            return signOutStatusMessage;
        }
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser==null){
            signOutStatusMessage = "Not a valid email";
            return signOutStatusMessage;
        }
        return authenticationService.addSongToPlayList(playlistId,songId,token);
    }

    public String signOutUser(String email, String token) {
        String signOutStatusMessage = null;
        if(email==null){
            signOutStatusMessage = "Email cannot be empty.";
            return signOutStatusMessage;
        }
        User existingUser = userRepo.findFirstByUserEmail(email);
        if(existingUser==null){
            signOutStatusMessage = "Not a valid email";
            return signOutStatusMessage;
        }
        return authenticationService.checkUserToken(token);

    }
}
