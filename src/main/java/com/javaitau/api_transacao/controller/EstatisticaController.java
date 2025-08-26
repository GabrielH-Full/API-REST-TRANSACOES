package com.javaitau.api_transacao.controller;

import com.javaitau.api_transacao.business.services.EstatiscasService;
import com.javaitau.api_transacao.controller.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class EstatisticaController {

    private EstatiscasService estatiscasService;

    @Autowired
    public EstatisticaController(EstatiscasService estatiscasService) {
        this.estatiscasService = estatiscasService;
    }

    @GetMapping
    @Operation(description = "Endpoint responsavel pelas estatisticas de transições")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarTodasEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatiscasService.calculaTransacoesEstatisticas(intervaloBusca));
    }
}