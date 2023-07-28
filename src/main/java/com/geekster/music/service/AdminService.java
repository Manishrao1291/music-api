package com.geekster.music.service;

import com.geekster.music.model.Admin;
import com.geekster.music.model.Song;
import com.geekster.music.model.dto.SignInInput;
import com.geekster.music.model.dto.SignUpOutput;
import com.geekster.music.repo.AdminRepo;
import com.geekster.music.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    SongService songService;


    public SignUpOutput signUpAdmin(Admin admin) {
        boolean signUpStatus = false;
        String signUpStatusMessage = null;

        String newEmail = admin.getAdminEmail();
        if(newEmail==null){
            signUpStatusMessage = "Email cannot be empty!!";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(newEmail);
        if(existingAdmin!=null){
            signUpStatusMessage = "Email is already registered!!";
            return  new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        try {
            String encryptPassword = PasswordEncrypter.encryptPassword(admin.getAdminPassword());
            admin.setAdminPassword(encryptPassword);
            adminRepo.save(admin);
            signUpStatus = true;
            signUpStatusMessage = "admin added successfully";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);

        } catch (Exception e) {
            signUpStatusMessage = "Internal server error";
            return new SignUpOutput(false,signUpStatusMessage);
        }

    }


    public String signInAdmin(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();
        if(signInEmail==null){
            signInStatusMessage = "Email cannot be empty!!!";
            return signInStatusMessage;
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(signInEmail);
        if(existingAdmin==null){
            signInStatusMessage = "Email is not registered!!!";
            return signInStatusMessage;
        }

        try {
            String encryptPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(encryptPassword.equals(existingAdmin.getAdminPassword())){
                return authenticationService.createToken(existingAdmin);
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

    public String signOutAdmin(String email, String token) {
        String signOutStatusMessage = null;
        if(email==null){
            signOutStatusMessage = "Email cannot be empty.";
            return signOutStatusMessage;
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin==null){
            signOutStatusMessage = "Not a valid email";
            return signOutStatusMessage;
        }
        return authenticationService.checkAdminToken(token);

    }

    public String addSong(Song song, String email) {
        if(email== null){
            return  " provide email";
        }
        Admin existingAdmin =  adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin == null){
            return  " only admin can add";
        }
        song.setAdmin(existingAdmin);

        return songService.addSong(song);


    }


    public String deleteSong(String email, String token, Integer songId) {
        if(email== null){
            return  " provide email";
        }
        Admin existingAdmin =  adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin == null){
            return  " only admin can delete";
        }

        return authenticationService.checkAdminToken(token,songId);


    }

    public String updateSong(String email, String token, Integer songId, String singer, String songName) {
        if(email== null){
            return  " provide email";
        }
        Admin existingAdmin =  adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin == null){
            return  " only admin can update";
        }
        return authenticationService.checkAdminTokenUpdate(token,songId,songName,singer);

    }
}
