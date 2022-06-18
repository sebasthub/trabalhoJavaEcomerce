package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import aplication.UtilizadorFlash;
import dao.CafeDAO;
import model.Cafe;
import model.ItemVenda;
import model.TipoUsuario;
import model.Usuario;

@Named
@ViewScoped
public class PrincipalController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private ArrayList<Cafe> produtos;
	private ArrayList<ItemVenda> carrinho;
	private boolean admin;
	
	public PrincipalController() {
		if (getUsuario().getTipoDeUsuario() == TipoUsuario.ADMINISTRADOR) {
			admin = true;
		}else {
			admin = false;
		}
		UtilizadorFlash<String> s = new UtilizadorFlash<String>();
		String mensage = s.pegar("mensagem");
		if(mensage != null) Util.addMessageInfo(mensage);
	}
	
	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = (Usuario) Session.getInstance().get("login");
			if(usuario == null) {
				usuario = new Usuario();
			}
		}
		return usuario;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public ArrayList<Cafe> getProdutos() {
		if (produtos  == null) {
			CafeDAO c = new CafeDAO();
			produtos = (ArrayList<Cafe>) c.getAll();
			if (produtos  == null)  produtos = new ArrayList<Cafe>();
		}
		return produtos;
	}

	public void setProdutos(ArrayList<Cafe> produtos) {
		this.produtos = produtos;
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

	public boolean eAdmin() {
		if (usuario.getTipoDeUsuario() == TipoUsuario.ADMINISTRADOR) {
			return true;
		}
		return false;
	}
	
	
	public void adicionar(int id) {
		CafeDAO c = new CafeDAO();
		Cafe cafe =  c.getById(id);
		ItemVenda i = new ItemVenda(cafe, cafe.getValor());
		getCarrinho().add(i);
		System.out.println(carrinho.toString());
	}
	
	public void irCarrinho() {
		Session.getInstance().set("carrinho", getCarrinho());
		Util.redirect("carrinho.xhtml");
	}
	
	public void sair() {
		Session.getInstance().set("login", null);
		Util.redirect("#");
	}
}
