package com.rentcar.app.repository;

import com.rentcar.app.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for {@link Agent} entities.
 * This interface provides standard CRUD operations for Agent entities and allows
 * for the definition of custom query methods.
 */
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
