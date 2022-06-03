package com.ewecarreira.hateoas.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(collectionRelation = "albums", itemRelation = "album")
public class AlbumModel extends RepresentationModel<AlbumModel> {

    private Long id;
    private String title;
    private String description;
    private String releaseDate;
    private List<ActorModel> actors;
}
