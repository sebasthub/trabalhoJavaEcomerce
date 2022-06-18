package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import dao.VendaDAO;
import model.Usuario;
import model.Venda;

@Named
@ViewScoped
public class HistoryController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6814926691032959602L;
	Usuario usuario;
	ArrayList<Venda> vendas;
	
	public HistoryController() {
		usuario = (Usuario) Session.getInstance().get("login");
		if(usuario == null) Util.redirect("login.xhtml");
		System.out.println(usuario);
		VendaDAO v = new VendaDAO();
		vendas = (ArrayList<Venda>) v.getByUsuario(usuario);
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
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ArrayList<Venda> getVendas() {
		if (vendas == null) {
			System.out.println(usuario);
			VendaDAO v = new VendaDAO();
			vendas = (ArrayList<Venda>) v.getByUsuario(usuario);
			if (vendas == null) {
				
				vendas = new ArrayList<Venda>();
			}
		}
		return vendas;
	}
	public void setVendas(ArrayList<Venda> vendas) {
		this.vendas = vendas;
	}
	
	public void inspecionar(Venda v) {
		VendaDAO d = new VendaDAO();
		v = d.getByVenda(v);
		Session.getInstance().set("inspect", v);
		Util.redirect("inspecionado.xhtml");
	}
	
}
