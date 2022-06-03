package com.ewecarreira.hateoas.domain.repository;

import com.ewecarreira.hateoas.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
