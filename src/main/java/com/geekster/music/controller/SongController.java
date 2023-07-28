package com.geekster.music.controller;

import com.geekster.music.model.Song;
import com.geekster.music.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping("songs")
    public List<Song> getSongs(){
        return songService.getSongs();
    }

}
