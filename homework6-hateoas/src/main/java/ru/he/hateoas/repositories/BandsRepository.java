package ru.he.hateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.he.hateoas.models.Band;

import java.awt.print.Pageable;
import java.util.List;

public interface BandsRepository extends MongoRepository<Band, String> {

    @RestResource(path = "trueBand", rel = "trueBand")
    @Query(value = "{active: true, $or: [{keywords: ?keywords}, {albumsCount: {$gt: ?1}}]}")
    List<Band> find(@Param("keywords") List<String> keywords, @Param("albumsCount") int maxAlbumsCount, Pageable pageable);
}
