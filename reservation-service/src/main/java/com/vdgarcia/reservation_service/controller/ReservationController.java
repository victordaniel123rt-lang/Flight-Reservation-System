package com.vdgarcia.reservation_service.controller;

import com.vdgarcia.reservation_service.dto.ReservationDTO;
import com.vdgarcia.reservation_service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservaciones")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationDTO> crear(@RequestBody ReservationDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }
}
