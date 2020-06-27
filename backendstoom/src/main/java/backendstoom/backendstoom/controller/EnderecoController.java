package backendstoom.backendstoom.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendstoom.backendstoom.domain.Endereco;
import backendstoom.backendstoom.service.EnderecoService;

@RestController
@CrossOrigin("${origem-permitida}")
@RequestMapping("/enderecos")
public class EnderecoController {
	public static final Logger logger = LoggerFactory.getLogger(Endereco.class);

	@Autowired
	EnderecoService enderecoService;

	@PostMapping
	public Endereco save(@RequestBody Endereco endereco) {
		try {
			endereco = enderecoService.save(endereco);
		} catch (Exception be) {
			logger.error("Erro no metodo salvar Endereço.");
			new Exception("Erro ao salvar endereço."); 
		}

		return endereco;
	}
	
	@PostMapping("/consultar")
	public Endereco finByEndereco(@RequestBody Endereco endereco) {
		Endereco enderecoPesquisa = null;

		try {
			enderecoPesquisa = enderecoService.findByEndereco(enderecoPesquisa);

			if (enderecoPesquisa == null) {
				logger.info("Endereço não encontrado.");
				new Exception("Endereço " +  endereco.getStreetName() + " não encontrado.");
			}

		} catch (Exception be) {
			logger.info("Erro ao pesquisar endereço " + be.getMessage());
			new Exception("Erro na rotina de pesquisar endereço. " + be.getMessage());
		}

		return enderecoPesquisa;
	}

	@GetMapping
	public List<Endereco> list() {
		List<Endereco> enderecos = null;

		try {
			enderecos = enderecoService.list();
		} catch (Exception be) {
			logger.error("Erro ao Listar endereços " + be.getMessage());
			new Exception("Erro na rotina de Listar endereços. " + be.getMessage());
		}

		return enderecos;
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<Endereco> findOne(@PathVariable Long id) {
		Optional<Endereco> endereco = null;
		
		try {
			endereco = enderecoService.findById(id);
		} catch (Exception be) {
			logger.error("Erro ao pesquisar endereço com o id {0} " + be.getMessage(), id);
			new Exception("Erro na rotina de pesquisar endereço. " + be.getMessage());
		}

		return endereco;
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		try {
			enderecoService.delete(id);
		} catch (Exception be) {
			logger.error("Erro ao excluir o endereço com o id {0} " + be.getMessage(), id);
			new Exception("Erro ao remover endereço. " + be.getMessage());
		}
	}
}
