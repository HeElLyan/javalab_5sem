package ru.he.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.he.dto.BandDto;
import ru.he.dto.BandRequest;
import ru.he.models.Band;
import ru.he.repositories.BandRepository;
import ru.he.repositories.BandsByRequestRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {

//    @Autowired
//    private BandsByRequestRepository bandsByRequestRepository;
//
//    @GetMapping("/bands/search")
//    public ResponseEntity<List<BandDto>> searchByPredicate(BandRequest bandRequest) {
//        return ResponseEntity.ok(bandsByRequestRepository.findByRequest(bandRequest));
//    }

    @Autowired
    private BandRepository bandRepository;

    @GetMapping("/bands/search")
    public ResponseEntity<List<BandDto>> searchByPredicate(@QuerydslPredicate(root = Band.class, bindings = BandRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(bandRepository.findAll(predicate).spliterator(), true)
                        .map(band ->
                                BandDto.builder()
                                        .name(band.getName())
                                        .state(band.getState())
                                        .build()).collect(Collectors.toList()));
    }
}
