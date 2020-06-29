package org.books.simpleonlinebookstore.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.books.simpleonlinebookstore.exceptions.InvalidEntityException;
import org.books.simpleonlinebookstore.models.items.Music;
import org.books.simpleonlinebookstore.services.baseservices.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/music")
@Slf4j
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping
    public ResponseEntity<Collection<Music>> getMusicCollection() {
        Collection<Music> musicCollection = this.musicService.getMusic();
        return new ResponseEntity<>(musicCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusic(@PathVariable final Long id) {
        Music music = this.musicService.getMusicById(id);
        return new ResponseEntity<>(music, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Music> addMusic(@RequestBody Music music) {
        Music added = this.musicService.createMusic(music);
        URI location = MvcUriComponentsBuilder.fromMethodName(MusicController.class, "addMusic", Music.class)
                .pathSegment("{id}").buildAndExpand(added.getId()).toUri();
        log.info("Music added:{}", location);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public Music deleteMusic(@PathVariable final Long id) {
        return this.musicService.deleteMusic(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Music> updateMusic(@PathVariable final Long id, @RequestBody Music music) {
        if (!music.getId().equals(id)) throw new InvalidEntityException(
                String.format("Music ID=%s from path is different from Entity ID=%s", id, music.getId()));
        Music updated = this.musicService.updateMusic(music);
        log.info("Music updated: {}", updated);
        return ResponseEntity.ok(updated);
    }

}
