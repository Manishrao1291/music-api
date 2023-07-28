package com.geekster.music.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @NotNull
    private String playlistName;

    @OneToOne
    @JoinColumn (name= "fk-userID")
    private  User user;

    @ManyToMany
    @JoinTable(name = "song_playlist_table",joinColumns = @JoinColumn(name = "fk_playList_id"),inverseJoinColumns = @JoinColumn(name = "fk_song_id"))
    private List<Song> songList;


}
