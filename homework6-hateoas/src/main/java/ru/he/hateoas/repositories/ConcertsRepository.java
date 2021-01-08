package ru.he.hateoas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.he.hateoas.models.Concert;

public interface ConcertsRepository extends PagingAndSortingRepository<Concert, String> {
}
