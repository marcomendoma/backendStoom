package backendstoom.backendstoom.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backendstoom.backendstoom.domain.Endereco;

@Repository
@Transactional
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	@Query(
			value = " Select * from Endereco " +
					" where streetName = :endereco ",
			nativeQuery = true
	)
	Endereco findByEndereco(String endereco);
}
