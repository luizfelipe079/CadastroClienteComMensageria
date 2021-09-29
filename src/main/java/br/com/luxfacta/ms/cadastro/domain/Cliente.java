package br.com.luxfacta.ms.cadastro.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.luxfacta.ms.cadastro.domain.enums.Perfil;
import br.com.luxfacta.ms.cadastro.domain.enums.StatusCliente;
import lombok.Data;


@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "cpf")
	@NotEmpty(message = "{campo.cpf.obrigatorio}")
	@CPF
	private String cpf;
	
	@Column(name = "nome")
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "email")
	@NotEmpty(message = "{campo.email.obrigatorio}")
	private String email;

	@Column(name = "senha")
	@NotEmpty(message = "O campo senha é obrigatório!")
	@JsonIgnore
	private String senha;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}
	
	public Cliente(Integer id, String cpf, String nome, String email, String senha, StatusCliente status) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.status = (status == null) ? null : status.getCod();
		addPerfil(Perfil.CLIENTE);
	}
	
	public Set<Perfil> getPerfil(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	public StatusCliente getStatus() {
		return StatusCliente.toEnum(status);
	}

	public void setEstado(StatusCliente status) {
		this.status = status.getCod();
	}
	
	
	
	
}
