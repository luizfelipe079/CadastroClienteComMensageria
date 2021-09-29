package br.com.luxfacta.ms.cadastro.dto;

import java.io.Serializable;

import br.com.luxfacta.ms.cadastro.domain.Cliente;
import br.com.luxfacta.ms.cadastro.domain.enums.StatusCliente;


public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String email;
	private StatusCliente status;
	
	public ClienteDTO() {
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		status = obj.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusCliente getStatus() {
		return status;
	}

	public void setStatus(StatusCliente status) {
		this.status = status;
	}

}
