package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import model.Cafe;
import model.Usuario;

@Named
@ViewScoped
public class CarrinhoControler implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2619117591309905224L;
	private Usuario usuario;
	private ArrayList<Cafe> carrinho;
	public CarrinhoControler() {
		usuario = (Usuario) Session.getInstance().get("login");
		if(usuario == null) Util.redirect("login.xhtml");
		carrinho = (ArrayList<Cafe>) Session.getInstance().get("carrinho");
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ArrayList<Cafe> getCarrinho() {
		if (carrinho == null) {
			carrinho = new ArrayList<Cafe>();
		}
		return carrinho;
	}
	public void setCarrinho(ArrayList<Cafe> carrinho) {
		this.carrinho = carrinho;
	}
}
