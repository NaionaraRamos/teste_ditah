package com.teste.ditah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.ditah.model.Produto;
import com.teste.ditah.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto obterProdutoPorId(Long id) {
        return produtoRepository.findById(id).get();
    }

    public List<Produto> obterTodosOsProdutos() {
        return produtoRepository.findAll();
    }

    //public Produto atualizarProduto(Long id, Produto produtoEditado) {
        
   // }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
