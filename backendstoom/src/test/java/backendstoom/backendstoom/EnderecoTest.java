package backendstoom.backendstoom;

import com.fasterxml.jackson.databind.ObjectMapper;

import backendstoom.backendstoom.domain.Endereco;
import backendstoom.backendstoom.service.EnderecoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoTest {
	
	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private EnderecoService enderecoService;
	
//	@Before
//    public void init() {
//        Endereco endereco = new Endereco("teste", 16L,"10","teste","São Paulo","SP","Brasil", 140560L, 10.2, 11.2);
//        
//        when(enderecoService.findById(1L).thenReturn(Optional.of(endereco));
//    }
	
	@Test
    public void listAll() throws Exception {

        List<Endereco> enderecos = Arrays.asList(
        		new Endereco("teste",16L,"10","teste","São Paulo","SP","Brasil",140560L, 10.2, 11.2),
        		new Endereco("Teste 1", 16L, "11", "teste 1", "Brasilia", "SP", "Brasil", 140430L, 10.3, 11.3));
        
        when(enderecoService.list()).thenReturn(enderecos);

        mockMvc.perform(get("/enderecos"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].streetName", is("teste")))
                .andExpect(jsonPath("$[0].number", is(16)))
                .andExpect(jsonPath("$[0].complement", is("10")))
                .andExpect(jsonPath("$[0].neighbourhood", is("teste")))
                .andExpect(jsonPath("$[0].city", is("São Paulo")))
                .andExpect(jsonPath("$[0].state", is("SP")))
                .andExpect(jsonPath("$[0].country", is("Brasil")))
                .andExpect(jsonPath("$[0].zipcode", is(140560)))
                .andExpect(jsonPath("$[0].latitude", is(10.2)))
                .andExpect(jsonPath("$[0].longitude", is(11.2)))
		        .andExpect(jsonPath("$[1].id", is(2)))
		        .andExpect(jsonPath("$[1].streetName", is("teste 1")))
		        .andExpect(jsonPath("$[1].number", is(16)))
		        .andExpect(jsonPath("$[1].complement", is("11")))
		        .andExpect(jsonPath("$[1].neighbourhood", is("teste 1")))
		        .andExpect(jsonPath("$[1].city", is("Brasilia")))
		        .andExpect(jsonPath("$[1].state", is("SP")))
		        .andExpect(jsonPath("$[1].country", is("Brasil")))
		        .andExpect(jsonPath("$[1].zipcode", is(140430)))
		        .andExpect(jsonPath("$[1].latitude", is(10.3)))
		        .andExpect(jsonPath("$[1].longitude", is(11.3)));

        verify(enderecoService, times(1)).list();
    }
	
	@Test
    public void findOne() throws Exception {

        mockMvc.perform(get("/enderecos/consultar/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].streetName", is("teste")))
                .andExpect(jsonPath("$[0].number", is(16)))
                .andExpect(jsonPath("$[0].complement", is("10")))
                .andExpect(jsonPath("$[0].neighbourhood", is("teste")))
                .andExpect(jsonPath("$[0].city", is("São Paulo")))
                .andExpect(jsonPath("$[0].state", is("SP")))
                .andExpect(jsonPath("$[0].country", is("Brasil")))
                .andExpect(jsonPath("$[0].zipcode", is(140560)))
                .andExpect(jsonPath("$[0].latitude", is(10.2)))
                .andExpect(jsonPath("$[0].longitude", is(11.2)));

        verify(enderecoService, times(1)).findById(1L);
    }
	
	@Test
    public void enderecoNotFound_404() throws Exception {
        mockMvc.perform(get("/enderecos/consultar/5")).andExpect(status().isNotFound());
    }
	
	@Test
    public void enderecoSave() throws Exception {

        Endereco endereco = new Endereco("teste 4",17L,"13","teste 4","São Paulo","SP","Brasil",140570L, 15.2, 16.2);
        when(enderecoService.save(any(Endereco.class))).thenReturn(endereco);

        mockMvc.perform(post("/enderecos")
                .content(om.writeValueAsString(endereco))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].streetName", is("teste")))
                .andExpect(jsonPath("$[0].number", is(16)))
                .andExpect(jsonPath("$[0].complement", is("10")))
                .andExpect(jsonPath("$[0].neighbourhood", is("teste")))
                .andExpect(jsonPath("$[0].city", is("São Paulo")))
                .andExpect(jsonPath("$[0].state", is("SP")))
                .andExpect(jsonPath("$[0].country", is("Brasil")))
                .andExpect(jsonPath("$[0].zipcode", is(140560)))
                .andExpect(jsonPath("$[0].latitude", is(10.2)))
                .andExpect(jsonPath("$[0].longitude", is(11.2)));

        verify(enderecoService, times(1)).save(any(Endereco.class));
    }
	
	@Test
    public void enderecoDelete() throws Exception {

        doNothing().when(enderecoService).delete(1L);

        mockMvc.perform(delete("/enderecos/1"))
                .andExpect(status().isOk());

        verify(enderecoService, times(1)).delete(1L);
    }
}
