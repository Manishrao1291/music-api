package com.geekster.music.service;

import com.geekster.music.model.Playlist;
import com.geekster.music.model.Song;
import com.geekster.music.model.User;
import com.geekster.music.repo.PlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepo playlistRepo;

    public String createPlaylist(Playlist playlist) {
        playlistRepo.save(playlist);
        return "playlist added successfully";
    }

    public List<Playlist> getPlaylist(User user) {
        Integer userId = user.getUserId();
        List<Playlist> playlists = playlistRepo.findFirstByUser(user);
        return playlists;
    }


    public String addSongToPlaylist(Integer playlistId, List<Song> songList) {
        Playlist playlist = playlistRepo.findFirstByPlaylistId(playlistId);
        playlist.setSongList(songList);
        playlistRepo.save(playlist);
        return "song added successfully to playlist.";
    }
}
