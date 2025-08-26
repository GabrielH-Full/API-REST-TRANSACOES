package com.javaitau.api_transacao.healthcheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    // injenção o DataSource, que é a fonte de conexões com o banco.
    private final DataSource dataSource;

    @Autowired
    public HealthCheckController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> body = new LinkedHashMap<>();

        // LÓGICA CORRETA: Tentamos obter uma conexão diretamente do DataSource.
        try (Connection connection = dataSource.getConnection()) {

            body.put("status", "UP");
            body.put("database", "UP");

            // RETURN CORRETO': Retornamos 200 OK
            return ResponseEntity.ok(body);

        } catch (SQLException e) {
            // TRATAMENTO DE ERRO: Capturamos a falha na conexão.

            body.put("status", "DOWN");
            body.put("database", "DOWN");
            body.put("error", e.getMessage());

            // RETURN CORRETO (FALHA): Retornamos 503 Service Unavailable
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
        }
    }

}
