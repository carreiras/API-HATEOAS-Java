package com.carreiras.hateoas.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

/**
 * Created by Ewerton on 20-10-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(collectionRelation = "actors", itemRelation = "actor")
public class ActorModel extends RepresentationModel<ActorModel> {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private List<AlbumModel> albums;
}
