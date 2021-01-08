package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}