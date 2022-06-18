package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venda {
	Integer id;
	LocalDate data;
	double valor;
	Usuario usuario;
	ArrayList<ItemVenda> produtos;
	
	public Venda(){
		this.data = LocalDate.now();
	}
	
	public Venda(double valor, ArrayList<ItemVenda> produtos, Usuario usuario) {
		super();
		this.usuario = usuario;
		this.data = LocalDate.now();
		this.valor = valor;
		this.produtos = produtos;
	}

	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public ArrayList<ItemVenda> getProdutos() {
		if (produtos == null) {
			produtos = new ArrayList<ItemVenda>();
		}
		return produtos;
	}
	public void setProdutos(ArrayList<ItemVenda> produtos) {
		this.produtos = produtos;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
