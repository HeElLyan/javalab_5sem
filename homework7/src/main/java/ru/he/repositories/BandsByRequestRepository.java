package ru.he.repositories;

import ru.he.dto.BandDto;
import ru.he.dto.BandRequest;

import java.util.List;

public interface BandsByRequestRepository {
    List<BandDto> findByRequest(BandRequest bandRequest);
}
