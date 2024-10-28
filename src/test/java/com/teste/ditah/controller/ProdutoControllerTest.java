package com.teste.ditah.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.teste.ditah.model.Produto;
import com.teste.ditah.service.ProdutoService;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;

    private final String URL = "/produto";

    @Test
    void testCriarProduto() throws Exception {
        Produto produto = new Produto("Nome do produto", "Descricao do produto", 1.25);
        System.out.println("produto: " );
        String requestBody = "Produto()";
        //MockHttpServletResponse response = performPost(produto.toString(), status().isOk());
        //JSONObject jsonObject = new JSONObject(response.getContentAsString());
    }

    @Test
    void testAtualizarProduto() throws Exception {
        String novoNomeProduto = "Novo nome do produto";
        Produto produtoASerAtualizado = new Produto();
        produtoASerAtualizado.setNome(novoNomeProduto);
        
        MockHttpServletResponse response = performPatch(2L, produtoASerAtualizado.toString(), status().is2xxSuccessful());
        System.out.println("patch: " + response);
    }

    @Test
    void testBuscarTodosOsProdutos() throws Exception {
        MockHttpServletResponse response = performGet(status().isOk());

    }

    @Test
    void testExcluirProduto() throws Exception {
        performDelete(1L, status().isNoContent());
        Produto produtoProcurado = produtoService.obterProdutoPorId(1L);
        assertNull(produtoProcurado);
    }

    @Test
    void testObterProdutoPorId() throws Exception {
        
        Produto produto = new Produto("Nome do produto", "Descricao do produto", 1.25);
        produtoService.criarProduto(produto);

        MockHttpServletResponse response = performGetById(produto.getId(), status().isOk());
        String expectedResult = "{\"id\":3,\"nome\":\"Nome do produto\",\"descricao\":\"Descricao do produto\",\"preco\":1.25}";

        assertEquals(expectedResult, response.getContentAsString());

        //MockHttpServletResponse response = performGet(status().isOk());
        //JSONObject jsonObject = new JSONObject(response.getContentAsString());
        //System.out.println(jsonObject);
       // JSONArray result = jsonObject.getJSONArray("produto");
    }

    private MockHttpServletResponse performGet(ResultMatcher expectedStatus) throws Exception {
        return mockMvc.perform(get(URL)).andExpect(expectedStatus).andReturn().getResponse();
    }

    private MockHttpServletResponse performGetById(Long idProduto, ResultMatcher expectedStatus) throws Exception {
        return mockMvc.perform(get(URL + "/" + idProduto)).andExpect(expectedStatus).andReturn().getResponse();
    }

    private MockHttpServletResponse performPatch(Long idProduto, String requestBody,ResultMatcher expectedStatus) throws Exception {
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
