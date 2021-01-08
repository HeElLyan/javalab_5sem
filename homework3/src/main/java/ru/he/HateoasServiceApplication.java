package ru.he;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.he.models.*;
import ru.he.repositories.*;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasServiceApplication.class, args);

        BandRepository bandRepository = context.getBean(BandRepository.class);
        CityRepository cityRepository = context.getBean(CityRepository.class);
        ConcertRepository concertRepository = context.getBean(ConcertRepository.class);
        CountryRepository countryRepository = context.getBean(CountryRepository.class);
        GenreRepository genreRepository = context.getBean(GenreRepository.class);
        MetallistRepository metallistRepository = context.getBean(MetallistRepository.class);

        Band periphery = Band.builder()
                .description("Best prog metal band ever")
                .name("Periphery")
                .state("Draft")
                .build();

        Band agonist = Band.builder()
                .description("Best MDM band")
                .name("The Agonist")
                .state("Draft")
                .build();

        Band arch = Band.builder()
                .description("MDM band")
                .name("Arch")
                .state("Deleted")
                .build();

        Band jinjer = Band.builder()
                .description("Norm band")
                .name("Jinjer")
                .state("Deleted")
                .build();

        bandRepository.saveAll(asList(periphery, agonist, arch, jinjer));

        Genre prog = Genre.builder()
                .name("prog metal")
                .bands(asList(periphery, jinjer))
                .build();

        Genre mdm = Genre.builder()
                .name("MDM")
                .bands(asList(agonist, arch))
                .build();

        genreRepository.saveAll(asList(prog, mdm));

        Metallist el = Metallist.builder()
                .name("el")
                .role("vocalist")
                .band(periphery)
                .build();

        Metallist mark = Metallist.builder()
                .name("mark")
                .role("guitarist")
                .band(periphery)
                .build();

        Metallist ravil = Metallist.builder()
                .name("ravil")
                .role("guitarist")
                .band(agonist)
                .build();

        Metallist vlad = Metallist.builder()
                .name("vlad")
                .role("basist")
                .band(agonist)
                .build();

        Metallist samat = Metallist.builder()
                .name("samat")
                .role("drummer")
                .band(periphery)
                .build();

        Metallist taty = Metallist.builder()
                .name("taty")
                .role("vocalist")
                .band(jinjer)
                .build();

        metallistRepository.saveAll(asList(el, mark, ravil, vlad, samat, taty));

        Country netherlands = Country.builder()
                .name("Netherlands")
                .build();

        Country japan = Country.builder()
                .name("Japan")
                .build();

        countryRepository.saveAll(asList(netherlands, japan));

        City amsterdam = City.builder()
                .name("Amsterdam")
                .country(netherlands)
                .build();

        City haag = City.builder()
                .name("Haag")
                .country(netherlands)
                .build();

        City tokyo = City.builder()
                .name("Tokyo")
                .country(japan)
                .build();

        City kyoto = City.builder()
                .name("Kyoto")
                .country(japan)
                .build();

        cityRepository.saveAll(asList(tokyo, kyoto, amsterdam, haag));

        Concert concert1 = Concert.builder()
                .name("HellFest")
                .city(tokyo)
                .bands(asList(periphery, agonist))
                .build();

        Concert concert2 = Concert.builder()
                .name("HAILSTAN")
                .city(tokyo)
                .bands(asList(periphery, jinjer))
                .build();

        concertRepository.saveAll(asList(concert1, concert2));
    }
}
