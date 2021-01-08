package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
