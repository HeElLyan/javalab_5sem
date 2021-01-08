package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.Concert;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
