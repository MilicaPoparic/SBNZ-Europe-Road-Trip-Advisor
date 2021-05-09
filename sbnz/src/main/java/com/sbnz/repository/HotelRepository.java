package com.sbnz.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.model.Hotel;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

	List<Hotel> findByActive(boolean b);

	Optional<Hotel> findByIdAndActive(Long id, boolean b);

}
