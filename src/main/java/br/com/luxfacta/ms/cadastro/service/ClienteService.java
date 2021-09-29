package br.com.luxfacta.ms.cadastro.service;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.luxfacta.ms.cadastro.configs.RabbitMQConfig;
import br.com.luxfacta.ms.cadastro.domain.Cliente;
import br.com.luxfacta.ms.cadastro.dto.ClienteDTO;
import br.com.luxfacta.ms.cadastro.dto.ClienteNewDTO;
import br.com.luxfacta.ms.cadastro.repository.ClienteRepository;
import br.com.luxfacta.ms.cadastro.service.exception.DataIntegrityException;
import br.com.luxfacta.ms.cadastro.service.exception.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private RabbitTemplate template;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired 
	private BCryptPasswordEncoder pe;
	
	public Cliente findById(Integer id) {
		Cliente cliente = repo.findById(id).orElseThrow( 
											() -> new ObjectNotFoundException(
												"Objeto não encontrado! Id:" + 
												id + ", Tipo: " + 
												Cliente.class.getName()));
		return cliente;
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		
		template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, obj);

		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir uma Cliente ");
		}
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = repo.findById(obj.getId()).orElseThrow( 
													() -> new ObjectNotFoundException(
																"Objeto não encontrado! Id:" + obj.getId() + ", Tipo: " + 
																Cliente.class.getName()));
	updateData(newObj, obj);
	return repo.save(newObj);
	}
	
	public Cliente updateSomeParams(Cliente obj) {
		Cliente newObj = repo.findById(obj.getId()).orElseThrow( 
				() -> new ObjectNotFoundException(
							"Objeto não encontrado! Id:" + obj.getId() + ", Tipo: " + 
							Cliente.class.getName()));
		updateDataSomeParams(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateData(Cliente newObj, Cliente obj) {
		newObj.setCpf(obj.getCpf());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void updateDataSomeParams(Cliente newObj, Cliente obj) {
		if(obj.getCpf() != null) {
			newObj.setCpf(obj.getCpf());
		}
		if(obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
		if(obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
	}
	
	public Cliente FromNewDTO(ClienteNewDTO objDto) {
		return new Cliente(objDto.getId(), 
						   objDto.getCpf(), 
						   objDto.getNome(),
						   objDto.getEmail(),
						   pe.encode(objDto.getSenha()),
						   objDto.getStatus());
	}
	
	public Cliente FromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), 
						   null, 
						   objDto.getNome(), 
						   objDto.getEmail(),
						   null,
						   objDto.getStatus());
	}
	
//	public Fatura getFatura(Integer id) {
//		
//		RestTemplate restTemplate = new RestTemplateBuilder()
//											.rootUri("http://localhost:8081/faturas")
//											.basicAuthentication("admin", "password")
//											.build();
//		
//		Fatura fatura = restTemplate.getForObject("/"+id, Fatura.class);
//		System.out.println(fatura);
//		return fatura;
		
//		Fatura fatura = restTemplate.getForObject("http://localhost:8081/faturas/"+id, Fatura.class);
//		return fatura;
//		
//	}
}
