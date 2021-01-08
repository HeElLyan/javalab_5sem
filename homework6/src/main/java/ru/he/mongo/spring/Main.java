package ru.he.mongo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);

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
                .active(false)
                .keywords(Arrays.asList("metalcore", "progressive metal", "extreme vocal"))
                .members(Arrays.asList("sam", "phil", "matthew"))
                .build();

        template.insert(concert1, "concerts");
        template.insert(concert2, "concerts");

        template.insert(periphery, "bands");
        template.insert(architects, "bands");

        List<Band> bands = template.find(new Query(
                where("active").is(true)
                        .orOperator(where("keywords").is("progressive metal"),
                                where("albumsCount").gt(10))), Band.class, "bands");
        System.out.println(bands);

        periphery.setAlbumsCount(15);
        template.save(periphery);

        List<Band> bands2 = template.find(new Query(
                where("active").is(true)
                        .orOperator(where("keywords").is("progressive metal"),
                                where("albumsCount").gt(10))), Band.class, "bands");
        System.out.println(bands2);

        template.dropCollection("bands");
        template.dropCollection("concerts");
    }
}
