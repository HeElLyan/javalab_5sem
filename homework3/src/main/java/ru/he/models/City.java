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
public class City implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_name", referencedColumnName = "name")
    private Country country;

    @OneToMany
    private List<Concert> concerts;

    @ManyToMany
    @JoinTable(name = "band_city",
            joinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "band_id", referencedColumnName = "id"))
    private List<Band> bands;
}
