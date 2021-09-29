package br.com.luxfacta.ms.cadastro.controller;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luxfacta.ms.cadastro.domain.Cliente;
import br.com.luxfacta.ms.cadastro.dto.ClienteDTO;
import br.com.luxfacta.ms.cadastro.dto.ClienteNewDTO;
import br.com.luxfacta.ms.cadastro.service.ClienteService;



@RestController
@RequestMapping(value = "clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@Autowired
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findCliente(@PathVariable Integer id) {
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAllClientes(){
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list
									.stream()
									.map(x -> new ClienteDTO(x))
									.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.FromNewDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("{/id}")
						.buildAndExpand(obj.getId())
						.toUri();
		
		
		return ResponseEntity.created(uri).build();
	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Void> update(@RequestBody ClienteDTO objDto,
//									   @PathVariable Integer id){
//		Cliente obj = service.FromDTO(objDto);
//		obj.setId(id);
//		obj = service.update(obj);
//		return ResponseEntity.noContent().build();
//	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateSomeParams(@RequestBody ClienteDTO objDto,
									   @PathVariable Integer id){
		Cliente obj = service.FromDTO(objDto);
		obj.setId(id);
		obj = service.updateSomeParams(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
//	@GetMapping("/pegarFatura/{id}")
//	public ResponseEntity<Fatura> getFaturaCliente(@PathVariable Integer id){
//		Fatura fatura = service.getFatura(id);
//		return ResponseEntity.ok().body(fatura);
//	}
	
}
