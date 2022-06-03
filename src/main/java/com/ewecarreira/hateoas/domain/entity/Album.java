package com.ewecarreira.hateoas.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
@ToString(exclude = "actors")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String releaseDate;

    @ManyToMany(mappedBy = "albums", fetch = FetchType.EAGER)
    private List<Actor> actors;
}
