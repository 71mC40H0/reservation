package com.zerobase.reservation.domain.dto;

import com.zerobase.reservation.domain.model.Kiosk;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KioskDto {
    private Long id;
    private Long restaurantId;

    public static KioskDto from(Kiosk kiosk) {
        return KioskDto.builder()
                .id(kiosk.getId())
                .restaurantId(kiosk.getRestaurant().getId())
                .build();
    }
}
