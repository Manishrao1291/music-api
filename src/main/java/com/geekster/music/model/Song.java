package com.geekster.music.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @NotNull
    private String songName;

    @NotNull
    private String singer;

    @NotNull
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn (name= "fk-admin")
    private  Admin admin;

}
