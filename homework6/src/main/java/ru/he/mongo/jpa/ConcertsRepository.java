package ru.he.mongo.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConcertsRepository extends PagingAndSortingRepository<Concert, String> {
}
