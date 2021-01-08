package ru.he.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.he.models.Band;
import ru.he.models.QBand;

public interface BandRepository extends PagingAndSortingRepository<Band, Long>, QuerydslPredicateExecutor<Band>, QuerydslBinderCustomizer<QBand> {
    @Override
    default void customize(QuerydslBindings bindings, QBand qBand) {
        bindings.bind(qBand.concerts.any().name).as("concerts.name").first(
                StringExpression::containsIgnoreCase
        );
    }
}

