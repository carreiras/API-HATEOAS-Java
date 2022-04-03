package com.github.carreiras.sample.hateoas.domain.repository;

import com.github.carreiras.sample.hateoas.domain.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
