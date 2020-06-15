package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.MusicRepository;
import org.books.simpleonlinebookstore.exceptions.EntityNotFoundException;
import org.books.simpleonlinebookstore.models.items.Music;
import org.books.simpleonlinebookstore.services.baseservices.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public Collection<Music> getMusic() {
        return this.musicRepository.findAll();
    }

    @Override
    public Music getMusicById(Long id) {
        return this.musicRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Music with ID=%s not found.", id)
                )
        );
    }

    @Override
    public Music createMusic(Music music) {
        return null;
    }

    @Override
    public Music updateMusic(Music music) {
        return null;
    }

    @Override
    public Music deleteMusic(Long id) {
        return null;
    }

    @Override
    public long getMusicCount() {
        return 0;
    }
}
