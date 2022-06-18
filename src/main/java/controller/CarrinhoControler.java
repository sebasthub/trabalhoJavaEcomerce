package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import aplication.UtilizadorFlash;
import dao.VendaDAO;
import model.Cafe;
import model.ItemVenda;
import model.Usuario;
import model.Venda;

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
		if (usuario == null) {
			usuario = new Usuario();
		}
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
	
	public void maisUm(ItemVenda i) {
		i.setQuant(i.getQuant() + 1);
	}
	
	public void menosUm(ItemVenda i) {
		if(i.getQuant() > 1) i.setQuant(i.getQuant() - 1);
	}
	
	public void comprar() {
		UtilizadorFlash<String> m = new UtilizadorFlash<String>();
		Venda v = new Venda(valorTotal(carrinho), carrinho,usuario);
		VendaDAO d = new VendaDAO();
		if (d.insert(v)) {
			m.inserir("mensagem", "realizado com sucesso");
		}else {
			m.inserir("mensagem", "erro na compra");
		}
		m.inserir("mensagem", "");
		Util.redirect("principal.xhtml");
	}
	
	public double valorTotal(ArrayList<ItemVenda> carrinho) {
		double total = 0;
		for(int i = 0; i < carrinho.size(); i++) {
			total = total + (carrinho.get(i).getValor()*carrinho.get(i).getQuant());
		}
		return total;
	}
}
