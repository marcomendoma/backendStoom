package backendstoom.backendstoom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backendstoom.backendstoom.domain.Endereco;
import backendstoom.backendstoom.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco findByEndereco(Endereco endereco) {
		return enderecoRepository.findByEndereco(endereco.getStreetName());
	}
	
	public List<Endereco> list() {
		return enderecoRepository.findAll();
	}
	
	public void delete(Long id) {
		enderecoRepository.deleteById(id);
	}
}
