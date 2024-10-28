package com.teste.ditah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.ditah.dto.ProdutoDto;
import com.teste.ditah.exception.ProdutoException;
import com.teste.ditah.model.Produto;
import com.teste.ditah.service.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.criarProduto(produto);
    }

    @GetMapping
    public List<Produto> buscarTodosOsProdutos() {
        return produtoService.obterTodosOsProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterProdutoPorId(@PathVariable Long id) {
        try {
            Produto produto = produtoService.obterProdutoPorId(id);

           // if(produto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");

            return ResponseEntity.status(HttpStatus.OK).body(produto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        try {
            return produtoService.atualizarProduto(id, produtoDto);
        } catch (ProdutoException e) {
            throw new ProdutoException(e.getMensagem());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable Long id) {
        try {
            produtoService.excluirProduto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
