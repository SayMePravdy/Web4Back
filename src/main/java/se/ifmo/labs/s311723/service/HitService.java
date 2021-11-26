package se.ifmo.labs.s311723.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.ifmo.labs.s311723.entity.Hit;
import se.ifmo.labs.s311723.entity.User;
import se.ifmo.labs.s311723.repository.HitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HitService {

    @Autowired
    private UserService userService;

    @Autowired
    private HitRepository hitRepository;

    public List<Hit> getAllUserHits() {
        return hitRepository.findAllByUser_id(userService.findByLogin(
                SecurityContextHolder.getContext().getAuthentication().getName()).getId());
    }

    public void hit(Hit hit, double startTime) {
        hit.setHit(checkHit(hit));
        hit.setDateTime(LocalDateTime.now());
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        hit.setUser(user);
        hit.setExecTime((System.nanoTime() - startTime) / 1000000000d);
        hitRepository.save(hit);
    }

    public void clear() {
        List<Hit> userHits = hitRepository.findAllByUser_id(userService.findByLogin(
                SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        hitRepository.deleteAllInBatch(userHits);
    }

    private boolean checkHit(Hit hit) {
        double x = hit.getX();
        double y = hit.getY();
        int r = hit.getR();

        if (x >= 0 && y >= 0 && y <= r && x * 2 <= r) {
            return true;
        }

        if (x <= 0 && y >= 0 && ((x * x + y * y) * 4 <= (r * r))) {
            return true;
        }

        return x >= 0 && y <= 0 && y >= (2 * x - r);

    }
}
