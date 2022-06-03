package com.ewecarreira.hateoas.rest.assemblers;

import com.ewecarreira.hateoas.domain.entity.Actor;
import com.ewecarreira.hateoas.domain.entity.Album;
import com.ewecarreira.hateoas.rest.controller.ActorController;
import com.ewecarreira.hateoas.rest.controller.AlbumController;
import com.ewecarreira.hateoas.rest.dto.ActorModel;
import com.ewecarreira.hateoas.rest.dto.AlbumModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
                        .add(linkTo(
                                methodOn(AlbumController.class).getAlbumById(album.getId())).withSelfRel()))
                .collect(Collectors.toList());
    }
}
