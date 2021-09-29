package br.com.luxfacta.ms.cadastro.dto;

import java.io.Serializable;

import br.com.luxfacta.ms.cadastro.domain.Cliente;
import br.com.luxfacta.ms.cadastro.domain.enums.StatusCliente;


public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private StatusCliente status;
	
	public ClienteNewDTO() {
	}
	
	public ClienteNewDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		email = obj.getEmail();
		senha = obj.getSenha();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public Date getDatNasc() {
//		return datNasc;
//	}
//
//	public void setDatNasc(Date datNasc) {
//		this.datNasc = datNasc;
//	}

//	public boolean isStatus() {
//		return status;
//	}
//
//	public void setStatus(boolean status) {
//		this.status = status;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public StatusCliente getStatus() {
		return status;
	}

	public void setStatus(StatusCliente status) {
		this.status = status;
	}

}
