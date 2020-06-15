package org.books.simpleonlinebookstore.dao;

import lombok.NonNull;
import org.books.simpleonlinebookstore.models.items.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findById(Long id);
    Optional<Music> findByArtistAndProducer(@NonNull @NotEmpty String artist, @NonNull @NotEmpty String producer);
}
