package com.github.carreiras.sample.hateoas.rest.assemblers;

import com.github.carreiras.sample.hateoas.domain.entity.Actor;
import com.github.carreiras.sample.hateoas.domain.entity.Album;
import com.github.carreiras.sample.hateoas.rest.controller.ActorController;
import com.github.carreiras.sample.hateoas.rest.controller.AlbumController;
import com.github.carreiras.sample.hateoas.rest.dto.ActorModel;
import com.github.carreiras.sample.hateoas.rest.dto.AlbumModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlbumModelAssembler extends RepresentationModelAssemblerSupport<Album, AlbumModel> {

    public AlbumModelAssembler() {
        super(AlbumController.class, AlbumModel.class);
    }

    @Override
    public AlbumModel toModel(Album album) {
        AlbumModel albumModel = instantiateModel(album);

        albumModel.add(linkTo(
                methodOn(AlbumController.class).getAlbumById(album.getId())).withSelfRel());

        albumModel.setId(album.getId());
        albumModel.setTitle(album.getTitle());
        albumModel.setDescription(album.getDescription());
        albumModel.setReleaseDate(album.getReleaseDate());
        albumModel.setActors(toActorModel(album.getActors()));

        return albumModel;
    }

    @Override
    public CollectionModel<AlbumModel> toCollectionModel(Iterable<? extends Album> albums) {
        CollectionModel<AlbumModel> albumModels = super.toCollectionModel(albums);

        albumModels.add(linkTo(
                methodOn(AlbumController.class).getAllAlbums()).withSelfRel());

        return albumModels;
    }

    private List<ActorModel> toActorModel(List<Actor> actors) {
        if (actors.isEmpty()) {
            return Collections.emptyList();
        }

        return actors.stream()
                .map(actor -> ActorModel.builder()
                        .id(actor.getId())
                        .firstName(actor.getFirstName())
                        .lastName(actor.getLastName())
                        .build()
                        .add(linkTo(
                                methodOn(ActorController.class).getActorById(actor.getId())).withSelfRel()))
                .collect(Collectors.toList());
    }
}
