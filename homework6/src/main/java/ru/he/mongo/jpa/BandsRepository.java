package ru.he.mongo.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BandsRepository extends MongoRepository<Band, String> {

    @Query(value = "{active: true, $or: [{keywords: ?keywords}, {albumsCount: {$gt: ?1}}]}")
    List<Band> find(@Param("keywords") List<String> keywords, @Param("albumsCount") int maxAlbumsCount);
}
