package com.javaitau.api_transacao.controller.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(Double valor, OffsetDateTime dataHora) {

}
