package com.sbnz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.model.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

	List<Destination> findByActive(boolean b);

	Optional<Destination> findByIdAndActive(Long id, boolean b);

}
