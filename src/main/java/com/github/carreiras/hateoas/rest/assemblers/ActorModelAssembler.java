package com.github.carreiras.hateoas.rest.assemblers;

import com.github.carreiras.hateoas.domain.entity.Actor;
import com.github.carreiras.hateoas.domain.entity.Album;
import com.github.carreiras.hateoas.rest.controller.ActorController;
import com.github.carreiras.hateoas.rest.controller.AlbumController;
import com.github.carreiras.hateoas.rest.dto.ActorModel;
import com.github.carreiras.hateoas.rest.dto.AlbumModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Created by Ewerton on 20-10-22
 */
@Component
public class ActorModelAssembler extends RepresentationModelAssemblerSupport<Actor, ActorModel> {

    public ActorModelAssembler() {
        super(ActorController.class, ActorModel.class);
    }

    @Override
    public ActorModel toModel(Actor actor) {
        ActorModel actorModel = instantiateModel(actor);

        actorModel.add(linkTo(
                methodOn(ActorController.class).getActorById(actor.getId())).withSelfRel());

        actorModel.setId(actor.getId());
        actorModel.setFirstName(actor.getFirstName());
        actorModel.setLastName(actor.getLastName());
        actorModel.setBirthDate(actor.getBirthDate());
        actorModel.setAlbums(toAlbumModel(actor.getAlbums()));

        return actorModel;
    }

    @Override
    public CollectionModel<ActorModel> toCollectionModel(Iterable<? extends Actor> actors) {
        CollectionModel<ActorModel> actorModels = super.toCollectionModel(actors);

        actorModels.add(linkTo(
                methodOn(ActorController.class).getAllActors()).withSelfRel());

        return actorModels;
    }

    private List<AlbumModel> toAlbumModel(List<Album> albums) {
        if (albums.isEmpty()) {
            return Collections.emptyList();
        }

        return albums.stream()
                .map(album -> AlbumModel.builder()
                        .id(album.getId())
                        .title(album.getTitle())
                        .build()
                        .add(WebMvcLinkBuilder.linkTo(
                                methodOn(AlbumController.class).getAlbumById(album.getId())).withSelfRel()))
                .collect(Collectors.toList());
    }
}
