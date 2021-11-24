package se.ifmo.labs.s311723.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HitResponseDTO {

    private double x;

    private double y;

    private int r;

    private boolean isHit;

    private double execTime;

    private LocalDateTime dateTime;
}
