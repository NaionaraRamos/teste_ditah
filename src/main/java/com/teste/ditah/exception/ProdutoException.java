package com.teste.ditah.exception;

//import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoException extends RuntimeException {
   // private HttpStatus httpStatus;
    private String mensagem;
}
