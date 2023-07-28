package com.geekster.music.repo;

import com.geekster.music.model.Admin;
import com.geekster.music.model.Playlist;
import com.geekster.music.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepo extends JpaRepository<Playlist,Integer> {
    List<Playlist> findFirstByUser(User user);

    Playlist findFirstByPlaylistId(Integer playlistId);
}
