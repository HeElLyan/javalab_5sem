package ru.he.mongo.spring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.he.mongo.jpa.Concert;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "bands")
public class Band {
    @Id
    private String _id;
    private String title;
    private Integer albumsCount;
    private Integer fansCount;
    private List<String> keywords;
    private List<String> genres;
    private List<String> members;
    private String description;
    private Integer rating;
    private String state;
    private Boolean active;
    @DBRef
    private List<Concert> concerts;
}
