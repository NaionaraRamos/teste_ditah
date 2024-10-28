package com.teste.ditah.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.teste.ditah.model.Produto;
import com.teste.ditah.repository.ProdutoRepository;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.h2.util.json.JSONArray;
import org.json.JSONObject;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;

    private final Produto produto1 = new Produto();//"Nome", "Descricao do produto", 4.5);
   // produto1.setNome("Nome do produto");
   // produto1.setDescricao("Descricao do produto")
   // produto1.setPreco(4.5);

    @BeforeEach
    void setUp() {
        //Produto produto1 = new Produto();
        produto1.setNome("Nome do produto");
        produto1.setDescricao("Descricao do produto");
        produto1.setPreco(4.5);
    }

   /* @Test
    void testCriarProduto() throws Exception { // teste repete o obterProdutoPorId
        produto1.setNome("Nome");
        produto1.setDescricao("Descricao");
        produto1.setPreco(35);
        Produto returnedProduto = produtoService.criarProduto(produto1);
        //System.out.println("Returned: " + returnedProduto.toString());

    }*/

    @Test
    void testExcluirProduto() {
        produtoService.excluirProduto(produto1.getId());
    }

    @Test
    void testObterProdutoPorId() {
        
        Produto produto = new Produto("Nome do produto", "Descricao do produto", 1.25);
        produtoService.criarProduto(produto);

        Produto produtoCriado = produtoService.obterProdutoPorId(produto.getId());
        assertEquals(3, produtoCriado.getId());
    }

    @Test
    void testObterTodosOsProdutos() throws Exception {
        //pegar 1 e 2
       // produto1.setNome("Nome do Produto");
       // produto1.setDescricao("Descrição do produto");
       // produto1.setPreco(4.5);
      //  produtoService.criarProduto(produto1);
    }
}
