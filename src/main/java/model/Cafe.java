package model;

import java.util.Objects;

public class Cafe {
	private Integer id;
	private String nome;
	private double valor;
	private String tipo;
	private String localDeProducao;
	private Fornecedor fornecedor;
	private Intencidade intencidade;
	private String image;
	
	
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
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLocalDeProducao() {
		return localDeProducao;
	}
	public void setLocalDeProducao(String localDeProducao) {
		this.localDeProducao = localDeProducao;
	}
	public Fornecedor getFornecedor() {
		if(fornecedor == null) {
			fornecedor = new Fornecedor();
		}
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Intencidade getIntencidade() {
		return intencidade;
	}
	public void setIntencidade(Intencidade intencidade) {
		this.intencidade = intencidade;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cafe other = (Cafe) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
	
	
	
}
