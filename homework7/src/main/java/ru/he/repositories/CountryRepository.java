package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
