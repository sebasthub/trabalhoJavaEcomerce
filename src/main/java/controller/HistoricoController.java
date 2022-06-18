package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import dao.vendaDAO;
import model.ItemVenda;
import model.Usuario;
import model.Venda;

@Named
@ViewScoped
public class HistoricoController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Usuario usuario;
	ArrayList<Venda> vendas;
	
	public HistoricoController() {
		usuario = (Usuario) Session.getInstance().get("login");
		if(usuario == null) Util.redirect("login.xhtml");
		vendaDAO v = new vendaDAO();
		vendas = (ArrayList<Venda>) v.getByUsuario(usuario);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ArrayList<Venda> getVendas() {
		return vendas;
	}
	public void setVendas(ArrayList<Venda> vendas) {
		this.vendas = vendas;
	}
	
	
}
