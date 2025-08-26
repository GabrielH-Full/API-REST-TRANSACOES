package com.javaitau.api_transacao.controller;


import com.javaitau.api_transacao.business.services.TransacaoService;
import com.javaitau.api_transacao.controller.dtos.TransacaoRequestDTO;
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
    private final TransacaoService transacaoService;

    @PostMapping("/registrar")
    public ResponseEntity<Void> receberTransacao(@RequestBody TransacaoRequestDTO dto){
        transacaoService.adicionarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
}
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletarTransacao(){
        transacaoService.limoparTransacao();
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}