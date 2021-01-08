package ru.he.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.he.models.Band;
import ru.he.repositories.BandRepository;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public Band publish(Long bandId) {
        Band band = bandRepository.findById(bandId).orElseThrow(IllegalArgumentException::new);
        band.publish();
        bandRepository.save(band);
        return band;
    }
}
