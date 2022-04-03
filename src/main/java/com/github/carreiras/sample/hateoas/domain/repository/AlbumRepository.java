package com.github.carreiras.sample.hateoas.domain.repository;

import com.github.carreiras.sample.hateoas.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
