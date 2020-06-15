package org.books.simpleonlinebookstore.web;

import org.books.simpleonlinebookstore.models.items.Music;
import org.books.simpleonlinebookstore.services.baseservices.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Music>> getMusicCollection() {
        Collection<Music> musicCollection = this.musicService.getMusic();
        return new ResponseEntity<>(musicCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusic(@PathVariable final Long id) {
        Music music = this.musicService.getMusicById(id);
        return new ResponseEntity<>(music, HttpStatus.OK);
    }

}
