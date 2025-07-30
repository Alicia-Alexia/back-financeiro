package com.sop.Financeiro.controller;

import com.sop.Financeiro.dtos.DespesaDTO;
import com.sop.Financeiro.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/despesa")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<DespesaDTO>> listarTodos() {
        return ResponseEntity.ok(despesaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(despesaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> criar(@RequestBody DespesaDTO dto) {
        return ResponseEntity.ok(despesaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaDTO> atualizar(@PathVariable Long id, @RequestBody DespesaDTO dto) {
        return ResponseEntity.ok(despesaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        despesaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
