package com.teste.ditah.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.teste.ditah.model.Produto;
import com.teste.ditah.service.ProdutoService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;

    private final String URL = "/produto";

    @Test
    void testObterProdutoPorId() throws Exception {
        
        String produto = "{\"nome\":\"Produto 3\",\"descricao\":\"Descrevendo o produto 3\",\"preco\":3}";
        performPost(produto, status().isOk());

        MockHttpServletResponse response = performGetById(3L, status().isOk());
        String expectedResult = "{\"id\":3,\"nome\":\"Produto 3\",\"descricao\":\"Descrevendo o produto 3\",\"preco\":3.0}";

        assertEquals(expectedResult, response.getContentAsString());
    }

    @Test
    void testBuscarTodosOsProdutos() throws Exception {

        String expectedResult = "[{\"id\":1,\"nome\":\"Produto 1\",\"descricao\":\"Descrevendo o produto 1\",\"preco\":35.0},{\"id\":2,\"nome\":\"Produto 2\",\"descricao\":\"Descrevendo o produto 2\",\"preco\":5.89},{\"id\":3,\"nome\":\"Produto 3\",\"descricao\":\"Descrevendo o produto 3\",\"preco\":3.0}]";
        MockHttpServletResponse response = performGet(status().isOk());
        assertEquals(expectedResult, response.getContentAsString());
    }

    @Test
    void testAtualizarProduto() throws Exception {
        performPatch(2L, "{\"nome\":\"Novo nome do produto\"}", status().is2xxSuccessful());
        assertEquals("Novo nome do produto", produtoService.obterProdutoPorId(2L).getNome());
    }

    @Test
    void testExcluirProduto() throws Exception {
        performDelete(1L, status().isNoContent());
        MockHttpServletResponse response = performGetById(1L, status().isNotFound());
        assertEquals("Produto não encontrado", response.getContentAsString());
    }

    private MockHttpServletResponse performGet(ResultMatcher expectedStatus) throws Exception {
        return mockMvc.perform(get(URL)).andExpect(expectedStatus).andReturn().getResponse();
    }

    private MockHttpServletResponse performGetById(Long idProduto, ResultMatcher expectedStatus) throws Exception {
        return mockMvc.perform(get(URL + "/" + idProduto)).andExpect(expectedStatus).andReturn().getResponse();
    }

    private MockHttpServletResponse performPatch(Long idProduto, String requestBody, ResultMatcher expectedStatus) throws Exception {
        return mockMvc.perform(patch(URL + "/" + idProduto).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                      .andExpect(expectedStatus).andReturn().getResponse();
    }

    private MockHttpServletResponse performDelete(Long idProduto, ResultMatcher expectedStatus) throws Exception {
        return mockMvc.perform(delete(URL + "/" + idProduto).contentType(MediaType.APPLICATION_JSON))
                      .andExpect(expectedStatus).andReturn().getResponse();
    }

    private MockHttpServletResponse performPost(String requestBody,ResultMatcher expectedStatus) throws Exception {
        return mockMvc.perform(post(URL).content(requestBody).contentType(MediaType.APPLICATION_JSON))
                      .andExpect(expectedStatus).andReturn().getResponse();
    }
}