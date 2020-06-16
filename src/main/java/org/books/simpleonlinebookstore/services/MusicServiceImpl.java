package org.books.simpleonlinebookstore.services;

import org.books.simpleonlinebookstore.dao.MusicRepository;
import org.books.simpleonlinebookstore.exceptions.EntityNotFoundException;
import org.books.simpleonlinebookstore.exceptions.InvalidEntityException;
import org.books.simpleonlinebookstore.models.items.Music;
import org.books.simpleonlinebookstore.services.baseservices.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    public Music createMusic(@Valid Music music) {
        this.musicRepository.findByArtistAndProducer(music.getArtist(), music.getProducer())
                .ifPresent(m -> {
                    throw new InvalidEntityException(String.format("Music:'%s' and '%s' already exists.",
                            m.getArtist(), m.getProducer()));
                });
        return this.musicRepository.saveAndFlush(music);
    }

    @Override
    public Music updateMusic(@Valid Music music) {
        music.setDatePublished(LocalDateTime.now());
        return this.musicRepository.saveAndFlush(music);
    }

    @Override
    public Music deleteMusic(Long id) {
        Music music = getMusicById(id);
        this.musicRepository.deleteById(id);
        return music;
    }

    @Override
    public long getMusicCount() {
        return this.musicRepository.count();
    }
}
