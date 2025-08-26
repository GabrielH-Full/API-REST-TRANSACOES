package com.javaitau.api_transacao.business.services;


import com.javaitau.api_transacao.controller.dtos.EstatisticasResponseDTO;
import com.javaitau.api_transacao.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatiscasService {

    @Autowired
    private TransacaoService transacaoService;

    public EstatisticasResponseDTO calculaTransacoesEstatisticas(Integer intervaloBusca) {

       // log.info("Iniciada a busca de estatisticas de transações{}", intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics statsEstisticas = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

       // log.info("Lista retornada com sucesso");
        return new EstatisticasResponseDTO(
                statsEstisticas.getCount(),
                statsEstisticas.getSum(),
                statsEstisticas.getAverage(),
                statsEstisticas.getMin(),
                statsEstisticas.getMax()
        );
    }
}