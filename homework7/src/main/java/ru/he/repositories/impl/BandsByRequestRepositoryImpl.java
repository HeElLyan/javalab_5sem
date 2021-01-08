package ru.he.repositories.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import ru.he.dto.BandDto;
import ru.he.dto.BandRequest;
import ru.he.models.Band;
import ru.he.repositories.BandsByRequestRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static ru.he.models.QBand.band;

@Repository
public class BandsByRequestRepositoryImpl implements BandsByRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BandDto> findByRequest(BandRequest bandRequest) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (bandRequest.getName() != null) {
            predicate.or(band.name.containsIgnoreCase(bandRequest.getName()));
        }
        if (bandRequest.getState() != null) {
            predicate.or(band.state.containsIgnoreCase(bandRequest.getState()));
        }

        return new JPAQuery<Band>(entityManager)
                .select(band.name, band.state)
                .from(band)
                .where(predicate)
                .fetch().stream()
                .map(row -> BandDto.builder()
                        .name(row.get(band.name))
                        .state(row.get(band.state))
                        .build())
                .collect(Collectors.toList());
    }
}
