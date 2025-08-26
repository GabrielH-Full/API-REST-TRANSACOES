package com.javaitau.api_transacao.controller;


import com.javaitau.api_transacao.business.services.TransacaoService;
import com.javaitau.api_transacao.controller.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Endpoint responsavel pelas transições")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gravação com sucesso"),
            @ApiResponse(responseCode = "422", description = "Criterio de trasação recusado"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<Void> receberTransacao(@RequestBody TransacaoRequestDTO dto){
        transacaoService.adicionarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
}
    @DeleteMapping
    @Operation(description = "Endpoint responsavel por deletar transições")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gravação com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro do servidor")
    })
    public ResponseEntity<Void> deletarTransacao(){
        transacaoService.limoparTransacao();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}