package se.ifmo.labs.s311723.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ifmo.labs.s311723.dto.HitRequestDTO;
import se.ifmo.labs.s311723.dto.HitResponseDTO;
import se.ifmo.labs.s311723.entity.Hit;
import se.ifmo.labs.s311723.mappers.HitMapper;
import se.ifmo.labs.s311723.service.HitService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class HitController {

    @Autowired
    private HitMapper hitMapper;

    @Autowired
    private HitService hitService;

    @GetMapping("/hits")
    public ResponseEntity<?> getAllUserHits() {
        List<Hit> userHits = hitService.getAllUserHits();
        List<HitResponseDTO> hits = userHits.stream()
                .map(hitMapper::hitToHitDto)
                .collect(ArrayList::new,
                        (list, e) -> list.add(0, e),
                        (list1, list2) -> list1.addAll(0, list2));
        return new ResponseEntity<>(hits, HttpStatus.OK);
    }

    @PostMapping("/doHit")
    public ResponseEntity<?> hit(@RequestBody HitRequestDTO hitRequestDTO) {
        double startTime = System.nanoTime();
        Hit hit = hitMapper.hitDtoToHit(hitRequestDTO);
        hitService.hit(hit, startTime);
        HitResponseDTO hitResponseDTO = hitMapper.hitToHitDto(hit);
        return new ResponseEntity<>(hitResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clear() {
        hitService.clear();
        return ResponseEntity.ok().body("Hits deleted");
    }

    @DeleteMapping("/deleteHit")
    public ResponseEntity<?> deleteHit(@Param("x") double x, @Param("y") double y, @Param("r") int r) {
        hitService.delete(x, y, r);
        return ResponseEntity.ok().body("Hit deleted");
    }
}
