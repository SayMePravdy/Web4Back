package se.ifmo.labs.s311723.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.ifmo.labs.s311723.entity.Hit;

import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {

    List<Hit> findAllByUser_id(Long user_id);


}
