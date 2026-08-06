package com.vdgarcia.flight_service.controller;
import com.vdgarcia.flight_service.dto.FlightDTO;
import com.vdgarcia.flight_service.dto.FlightDisponibleDTO;
import com.vdgarcia.flight_service.model.Flight;
import com.vdgarcia.flight_service.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/vuelos")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService service;

    @GetMapping
    public ResponseEntity<List<FlightDTO>> obtenerTodos(){
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<FlightDTO> crear(@RequestBody FlightDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDTO> actualizar(@PathVariable Long id, @RequestBody FlightDTO dto){
        return ResponseEntity.ok(service.actualizar(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDisponibleDTO>> obtenerDisponibles(){
        return ResponseEntity.ok(service.disponibles());
    }


}
