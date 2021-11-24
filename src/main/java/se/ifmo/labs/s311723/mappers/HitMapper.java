package se.ifmo.labs.s311723.mappers;

import org.springframework.stereotype.Component;
import se.ifmo.labs.s311723.dto.HitRequestDTO;
import se.ifmo.labs.s311723.dto.HitResponseDTO;
import se.ifmo.labs.s311723.entity.Hit;

@Component
public class HitMapper {

    public Hit hitDtoToHit(HitRequestDTO hitRequestDTO) {
        return new Hit(hitRequestDTO.getX(), hitRequestDTO.getY(), hitRequestDTO.getR());
    }

    public HitResponseDTO hitToHitDto(Hit hit) {
        return HitResponseDTO.builder()
                .x(hit.getX())
                .y(hit.getY())
                .r(hit.getR())
                .isHit(hit.isHit())
                .execTime(hit.getExecTime())
                .dateTime(hit.getDateTime())
                .build();
    }
}
