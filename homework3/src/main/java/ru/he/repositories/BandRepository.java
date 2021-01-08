package ru.he.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.he.models.Band;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BandRepository extends PagingAndSortingRepository<Band, Long>  {

    @RestResource(path = "published", rel = "published")
    @Query("from Band band where band.state = 'Published'")
    Page<Band> findAllPublished(Pageable pageable);

    @RestResource(path = "name", rel = "name")
    List<Band> findAllByName(String name);
}

