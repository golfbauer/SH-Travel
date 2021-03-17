package de.hhn.se.labswp.wstgsh.webapi.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SehenswuerdigkeitRepository extends JpaRepository<Sehenswuerdigkeit, Long> {
}