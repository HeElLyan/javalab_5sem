package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.Metallist;

public interface MetallistRepository extends JpaRepository<Metallist, Long> {
}
