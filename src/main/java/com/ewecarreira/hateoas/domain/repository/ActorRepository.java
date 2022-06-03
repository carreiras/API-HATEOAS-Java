package com.ewecarreira.hateoas.domain.repository;

import com.ewecarreira.hateoas.domain.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
