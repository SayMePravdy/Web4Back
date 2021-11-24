package se.ifmo.labs.s311723.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.labs.s311723.dto.HitRequestDTO;
import se.ifmo.labs.s311723.entity.Hit;
import se.ifmo.labs.s311723.mappers.HitMapper;
import se.ifmo.labs.s311723.service.HitService;
import se.ifmo.labs.s311723.service.UserService;

@RestController
@RequestMapping("/user")
public class HitController {

    @Autowired
    private HitMapper hitMapper;

    @Autowired
    private HitService hitService;

    @PostMapping("/doHit")
    public ResponseEntity<?> hit(@RequestBody HitRequestDTO hitRequestDTO) {
        double startTime = System.nanoTime();
        Hit hit = hitMapper.hitDtoToHit(hitRequestDTO);
        hitService.hit(hit, startTime);
        return new ResponseEntity<>(hitMapper.hitToHitDto(hit), HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clear() {
        hitService.clear();
        return ResponseEntity.ok().body("Hits deleted");
    }
}
