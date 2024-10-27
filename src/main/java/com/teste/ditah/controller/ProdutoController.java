package com.teste.ditah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.ditah.model.Produto;
import com.teste.ditah.service.ProdutoService;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    
    @PostMapping
    public Produto criarProduto(Produto produto) {
        return produtoService.criarProduto(produto);
    }

    @GetMapping("/{id}")
    public Produto obterProdutoPorId(Long id) {
        return produtoService.obterProdutoPorId(id);
    }

    @GetMapping
    public List<Produto> buscarTodosOsProdutos() {
        return produtoService.obterTodosOsProdutos();
    }

    //public Produto atualizarProduto(Long id, Produto produtoEditado) { 
    // }

    @DeleteMapping("/{id}")
    public void excluirProduto(Long id) {
        produtoService.excluirProduto(id);
    }
}
