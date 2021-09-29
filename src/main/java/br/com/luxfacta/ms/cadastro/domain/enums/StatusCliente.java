package br.com.luxfacta.ms.cadastro.domain.enums;

public enum StatusCliente {
//
//	PENDENTE("p"),
//	QUITADO("q"),
//	CANCELADO("c");
//	
//	private String code;
//
//	private StatusCliente(String code) {
//		this.code = code;
//	}
//	
//	public String getCode() {
//		return code;
//	}
//	
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private StatusCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusCliente toEnum(Integer cod) {
		if( cod == null ) {
			return null;
		}
		
		for(StatusCliente x : StatusCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido : " + cod);
	}
	
}
