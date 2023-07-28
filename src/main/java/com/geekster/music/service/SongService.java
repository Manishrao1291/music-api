package com.geekster.music.service;

import com.geekster.music.model.Song;
import com.geekster.music.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    @Autowired
    SongRepo songRepo;

    @Autowired
    PlaylistService playlistService;

    public String addSong(Song song) {
        songRepo.save(song);
        return  "song added";
    }

    public List<Song> getSongs() {
        return songRepo.findAll();

    }

    public String deleteSong(Integer songId) {
        songRepo.deleteById(songId);
        return " song deleted ......" ;
    }


    public String updateSong(Integer songId, String singer, String songName) {
        Song song = songRepo.findFirstBySongId(songId);
        if(song== null){
            return  " song not found";
        }
        song.setSinger(singer);
        song.setSongName(songName);
        songRepo.save(song);
        return  "song updated ......";

    }

    public String addSongToPlaylist(Integer playlistId, List<Integer> songId) {
        List<Song> songList = new ArrayList<Song>();
        for(Integer ele:songId){
            Song song = songRepo.findFirstBySongId(ele);
            if(song==null){
                return "song not found,please enter a valid song";
            }
            else{
                songList.add(song);
            }
        }

        return playlistService.addSongToPlaylist(playlistId,songList);
    }
}
