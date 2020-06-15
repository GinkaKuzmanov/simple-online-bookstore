package org.books.simpleonlinebookstore.services.baseservices;

import org.books.simpleonlinebookstore.models.items.Music;

import java.util.Collection;

public interface MusicService {

    Collection<Music> getMusic();

    Music getMusicById(Long id);

    Music createMusic(Music music);

    Music updateMusic(Music music);

    Music deleteMusic(Long id);

    long getMusicCount();

}
