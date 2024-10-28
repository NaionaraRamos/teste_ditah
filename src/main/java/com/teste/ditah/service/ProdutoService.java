package com.teste.ditah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.ditah.dto.ProdutoDto;
import com.teste.ditah.exception.ProdutoException;
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
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> obterTodosOsProdutos() {
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Long id, ProdutoDto produtoEditadoDto) {
        Produto oldProduto = obterProdutoPorId(id);

        if(oldProduto == null) {
            throw new ProdutoException( "Produto não existe.");
        }

        if(produtoEditadoDto.getNome() != null) oldProduto.setNome(produtoEditadoDto.getNome());
        if(produtoEditadoDto.getDescricao() != null) oldProduto.setDescricao(produtoEditadoDto.getDescricao());
        if(produtoEditadoDto.getPreco() != 0.0) oldProduto.setPreco(produtoEditadoDto.getPreco());

        produtoRepository.save(oldProduto);
        return oldProduto;
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
