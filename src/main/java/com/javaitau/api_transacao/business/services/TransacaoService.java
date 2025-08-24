package com.javaitau.api_transacao.business.services;

import com.javaitau.api_transacao.controller.dtos.TransacaoRequestDTO;
import com.javaitau.api_transacao.infrastructure.exception.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service //1
@RequiredArgsConstructor //1 - injeçoes de indepedencias
@Slf4j
public class TransacaoService {
    //armazenamento em memoria por array list

    private final List<TransacaoRequestDTO> listTransacao = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto) {
        log.info("Iniciada o processamento de gravar transações");

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e Hora maiores que o atual");
            throw new UnprocessableEntity("data e hora maiores que data e hora atuais");
        }
        if(dto.valor() < 0){
            log.error("Valor negativo");
            throw new UnprocessableEntity("Valor nao pode ser negativo");
        }
        listTransacao.add(dto);
    }

    public void limoparTransacao() {
        listTransacao.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Estatistica dos ultimos 60 segundos");
        OffsetDateTime dataHoraintervalo= OffsetDateTime.now().minusSeconds(intervaloBusca);

        return listTransacao.stream().filter(transacoes -> transacoes.dataHora()
                .isAfter(dataHoraintervalo)).toList();
    }


}
