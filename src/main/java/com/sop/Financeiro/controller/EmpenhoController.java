package com.sop.Financeiro.controller;

import com.sop.Financeiro.dtos.EmpenhoDTO;
import com.sop.Financeiro.service.EmpenhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/empenho")
@RequiredArgsConstructor
public class EmpenhoController {

    private final EmpenhoService empenhoService;

    @GetMapping
    public ResponseEntity<List<EmpenhoDTO>> listarTodos() {
        return ResponseEntity.ok(empenhoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpenhoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empenhoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmpenhoDTO> criar(@RequestBody EmpenhoDTO dto) {
        return ResponseEntity.ok(empenhoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpenhoDTO> atualizar(@PathVariable Long id, @RequestBody EmpenhoDTO dto) {
        return ResponseEntity.ok(empenhoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        empenhoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
