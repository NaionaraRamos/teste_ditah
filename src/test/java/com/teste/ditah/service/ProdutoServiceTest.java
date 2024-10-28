package com.teste.ditah.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.ditah.dto.ProdutoDto;
import com.teste.ditah.model.Produto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Test
    void testObterProdutoPorId() {
        
        Produto produto = new Produto("Nome do produto", "Descricao do produto", 1.25);
        produtoService.criarProduto(produto);

        Produto produtoCriado = produtoService.obterProdutoPorId(produto.getId());
        assertEquals(3, produtoCriado.getId());
    }

    @Test
    void testExcluirProduto() {
        produtoService.excluirProduto(2L);
        Produto buscarProduto = produtoService.obterProdutoPorId(2L);
        assertNull(buscarProduto);
    }

    @Test
    void testObterTodosOsProdutos() throws Exception {
        List<Produto> listaProdutos = produtoService.obterTodosOsProdutos();
        assertEquals(3, listaProdutos.size());
    }

    @Test
    void testAtualizarProduto() throws Exception {
        String novaDescricao = "Nova descricao do produto";
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setDescricao(novaDescricao);
        produtoService.atualizarProduto(1L, produtoDto);
        assertEquals(novaDescricao, produtoService.obterProdutoPorId(1L).getDescricao());
    }
}
