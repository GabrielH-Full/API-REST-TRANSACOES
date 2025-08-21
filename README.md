# API-REST 
#### Uma API REST que recebe Transações e retorna Estatísticas sob essas transações

---

### 1. Bibliotecas

- lombok
- web spring-boot
- actuator starter spring-boot

---

### 2. Endpoints da API

A seguir serão especificados os endpoints que devem estar presentes na sua API e a funcionalidade esperada de cada um deles.

 **2.1. Receber Transações: `POST /transacao`**

Este é o endpoint que irá receber as Transações. Cada transação consiste de um valor e uma dataHora de quando ela aconteceu:

````json
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
````

API só aceitará transações que:

1. Tenham os campos valor e dataHora preenchidos
2. A transação NÃO DEVE acontecer no futuro
3. A transação DEVE ter acontecido a qualquer momento no passado
4. A transação NÃO DEVE ter valor negativo
5. A transação DEVE ter valor igual ou maior que 0 (zero)

Como resposta, espera-se que este endpoint responda com:

- `201 Created` sem nenhum corpo
    - A transação foi aceita (ou seja foi validada, está válida e foi registrada)
- `422 Unprocessable Entity` sem nenhum corpo
    - A transação **não** foi aceita por qualquer motivo (1 ou mais dos critérios de aceite não foram atendidos - por exemplo: uma transação com valor menor que `0`)
- `400 Bad Request` sem nenhum corpo
    - A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido)

**2.2. Limpar Transações: `DELETE /transacao`**

Este endpoint simplesmente apaga todos os dados de transações que estejam armazenados.

Como resposta, espera-se que este endpoint responda com:
- 200 OK sem nenhum corpo 
  - Todas as informações foram apagadas com sucesso


**2.3. Calcular Estatísticas: `GET /estatistica`**

Este endpoint deve retornar estatísticas das transações que aconteceram nos últimos 60 segundos (1 minuto). As estatísticas que devem ser calculadas são:

```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```
Como resposta, espera-se que este endpoint responda com:

- `200 OK` com os dados das estatísticas
    - Um JSON com os campos `count`, `sum`, `avg`, `min` e `max` todos preenchidos com seus respectivos valores
    - **Atenção!** Quando não houverem transações nos últimos 60 segundos considere todos os valores como `0` (zero)
