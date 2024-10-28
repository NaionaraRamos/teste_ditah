package com.teste.ditah.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoException extends RuntimeException {
    private String mensagem;
}
