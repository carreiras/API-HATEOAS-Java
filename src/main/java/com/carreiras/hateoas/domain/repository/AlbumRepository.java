package com.carreiras.hateoas.domain.repository;

import com.carreiras.hateoas.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ewerton on 20-10-22
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
