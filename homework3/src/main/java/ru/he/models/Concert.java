package ru.he.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Concert implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_name", referencedColumnName = "name")
    private City city;

    @ManyToMany
    @JoinTable(name = "band_concert",
            joinColumns = @JoinColumn(name = "concert_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "band_id", referencedColumnName = "id"))
    private List<Band> bands;
}