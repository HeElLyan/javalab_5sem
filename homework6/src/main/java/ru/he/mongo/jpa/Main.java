package ru.he.mongo.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        BandsRepository bandsRepository = context.getBean(BandsRepository.class);
        ConcertsRepository concertsRepository = context.getBean(ConcertsRepository.class);

        Concert concert1 = Concert.builder()
                .title("HellFest")
                .country("USA")
                .genres(Arrays.asList("progressive metal", "metalcore", "mdm", "death metal"))
                .build();

        Concert concert2 = Concert.builder()
                .title("Wacken Open Air")
                .country("USA")
                .genres(Arrays.asList("progressive metal", "metalcore", "mdm", "death metal"))
                .build();

        concertsRepository.save(concert1);
        concertsRepository.save(concert2);

        Band periphery = Band.builder()
                .title("Periphery")
                .active(true)
                .albumsCount(9)
                .fansCount(20000000)
                .genres(Arrays.asList("progressive metal", "djent"))
                .keywords(Arrays.asList("progressive metal", "djent", "extreme vocal"))
                .rating(10)
                .description("My lovely prog band")
                .build();

        Band architects = Band.builder()
                .title("Architects")
                .albumsCount(12)
                .fansCount(100000000)
                .genres(Arrays.asList("progressive metal", "djent", "metalcore"))
                .active(true)
                .keywords(Arrays.asList("metalcore", "progressive metal", "extreme vocal"))
                .members(Arrays.asList("sam", "phil", "matthew"))
                .build();

        List<Concert> concerts = Arrays.asList(concert1, concert2);
        periphery.setConcerts(concerts);
        architects.setConcerts(concerts);

        bandsRepository.insert(periphery);
        bandsRepository.insert(architects);

        architects.setAlbumsCount(13);

        bandsRepository.save(architects);

        System.out.println(bandsRepository.find(Arrays.asList("progressive metal"), 10));

//        bandsRepository.delete(periphery);
//        bandsRepository.delete(architects);
//        concertsRepository.delete(concert1);
//        concertsRepository.delete(concert2);
    }
}
