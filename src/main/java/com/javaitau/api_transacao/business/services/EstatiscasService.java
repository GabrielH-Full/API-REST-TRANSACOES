package com.javaitau.api_transacao.business.services;


import com.javaitau.api_transacao.controller.dtos.EstatisticasResponseDTO;
import com.javaitau.api_transacao.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstatiscasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calculaTransacoesEstatisticas(Integer intervaloBusca) {

        log.info("Lista de Transação, dentro do intervalo de busca");
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        log.info("objeto DoubleSummaryStatistics para fazer os calculos");
        DoubleSummaryStatistics statsEstisticas = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        return new EstatisticasResponseDTO(
                statsEstisticas.getCount(),
                statsEstisticas.getSum(),
                statsEstisticas.getAverage(),
                statsEstisticas.getMin(),
                statsEstisticas.getMax()
        );
    }
}