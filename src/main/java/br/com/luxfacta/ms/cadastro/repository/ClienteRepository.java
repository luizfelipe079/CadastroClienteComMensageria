package br.com.luxfacta.ms.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.luxfacta.ms.cadastro.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
