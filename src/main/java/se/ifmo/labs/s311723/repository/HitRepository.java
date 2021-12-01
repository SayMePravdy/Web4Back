package se.ifmo.labs.s311723.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.ifmo.labs.s311723.entity.Hit;

import javax.transaction.Transactional;
import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {

    List<Hit> findAllByUser_id(Long user_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE from t_hit h where h.x = :x and h.y = :y and h.r = :r and h.user_id = " +
            "(SELECT u.id from t_user u where u.login = :login)", nativeQuery = true)
    void deleteHit(@Param("x") double x, @Param("y") double y, @Param("r") int r, @Param("login") String login);

}
