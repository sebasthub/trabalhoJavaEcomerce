package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import model.Cafe;
import model.ItemVenda;
import model.Usuario;

@Named
@ViewScoped
public class CarrinhoControler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2619117591309905224L;
	private Usuario usuario;
	private ArrayList<ItemVenda> carrinho;
	public CarrinhoControler() {
		usuario = (Usuario) Session.getInstance().get("login");
		if(usuario == null) Util.redirect("login.xhtml");
		carrinho = (ArrayList<ItemVenda>) Session.getInstance().get("carrinho");
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ArrayList<ItemVenda> getCarrinho() {
		if (carrinho == null) {
			carrinho = new ArrayList<ItemVenda>();
		}
		return carrinho;
	}
	public void setCarrinho(ArrayList<ItemVenda> carrinho) {
		this.carrinho = carrinho;
	}
	public void delete( ItemVenda item ) {
		carrinho.remove(item);
	}
}
