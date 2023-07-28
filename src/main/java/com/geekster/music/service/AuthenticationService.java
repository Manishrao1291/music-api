package com.geekster.music.service;

import com.geekster.music.model.Admin;
import com.geekster.music.model.AuthenticationToken;
import com.geekster.music.model.Playlist;
import com.geekster.music.model.User;
import com.geekster.music.repo.AuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {


    @Autowired
    AuthenticationRepo authenticationRepo;

    @Autowired
    SongService songService;

    @Autowired
    PlaylistService playlistService;

    public String createToken(Admin existingAdmin) {
        AuthenticationToken authToken = new AuthenticationToken(existingAdmin);
        authenticationRepo.save(authToken);
        return "Token generated successfully.";
    }

    public String createToken(User existingUser) {
        AuthenticationToken authToken = new AuthenticationToken(existingUser);
        authenticationRepo.save(authToken);
        return "Token generated successfully.";
    }

    public String checkAdminToken(String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        authenticationRepo.delete(existingToken);
        tokenMessage = "User signOut successfully.";
        return tokenMessage;
    }

    public String checkAdminToken(String token, Integer songId) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }

         return songService.deleteSong(songId);

    }




    public String checkAdminTokenUpdate(String token, Integer songId, String singer, String songName) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }

        return songService.updateSong(songId, singer, songName);
    }

    public String checkUser(Playlist playlist, String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        return playlistService.createPlaylist(playlist);
    }

    public String addSongToPlayList(Integer playlistId, List<Integer> songId, String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        return songService.addSongToPlaylist(playlistId,songId);
    }

    public String checkUserToken(String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        authenticationRepo.delete(existingToken);
        tokenMessage = "User signOut successfully.";
        return tokenMessage;
    }
}
