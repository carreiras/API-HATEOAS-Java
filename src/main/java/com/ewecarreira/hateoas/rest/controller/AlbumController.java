package com.ewecarreira.hateoas.rest.controller;

import com.ewecarreira.hateoas.domain.entity.Album;
import com.ewecarreira.hateoas.domain.repository.AlbumRepository;
import com.ewecarreira.hateoas.rest.dto.AlbumModel;
import com.ewecarreira.hateoas.rest.assemblers.AlbumModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumModelAssembler albumModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<AlbumModel>> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return new ResponseEntity<>(albumModelAssembler.toCollectionModel(albums), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumModel> getAlbumById(@PathVariable("id") Long id) {
        return albumRepository.findById(id)
                .map(albumModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}