package com.sop.Financeiro.controller;

import com.sop.Financeiro.dtos.PagamentoDTO;
import com.sop.Financeiro.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pagamento")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> criar(@RequestBody PagamentoDTO dto) {
        return ResponseEntity.ok(pagamentoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizar(@PathVariable Long id, @RequestBody PagamentoDTO dto) {
        return ResponseEntity.ok(pagamentoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pagamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
