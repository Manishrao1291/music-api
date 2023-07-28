package com.geekster.music.repo;

import com.geekster.music.model.Admin;
import com.geekster.music.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepo extends JpaRepository<Song,Integer> {
    Song findFirstBySongId(Integer songId);
}
