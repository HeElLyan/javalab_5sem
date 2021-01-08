package ru.he.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metallist {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String role;

    @ManyToOne
    @JoinColumn(name = "band_name", referencedColumnName = "name")
    private Band band;
}
