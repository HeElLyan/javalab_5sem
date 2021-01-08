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
public class Band implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "bands")
    private List<Genre> genres;

    @OneToMany(mappedBy = "band")
    private List<Metallist> metallists;

    @ManyToMany(mappedBy = "bands")
    private List<Concert> concerts;

    private String state;

    public void publish() {
        if (this.state.equals("Draft")) {
            this.state = "Published";
        } else if (this.state.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }
}
