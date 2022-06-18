package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import dao.UsuarioDAO;
import dao.VendaDAO;
import model.Usuario;
import model.Venda;

@Named
@ViewScoped
public class VendasController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Usuario usuario;
	ArrayList<Venda> vendas;
	String nome;
	
	public VendasController() {
		VendaDAO v = new VendaDAO();
		vendas = (ArrayList<Venda>) v.getByUltimas();
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
	
	public String getNome() {
		if (nome == null) {
			nome = new String();
		}
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void inspecionar(Venda v) {
		VendaDAO d = new VendaDAO();
		v = d.getByVenda(v);
		Session.getInstance().set("inspect", v);
		Util.redirect("inspecionado.xhtml");
	}
	
	public void filtrar() {
		try {
			UsuarioDAO d = new UsuarioDAO();
			Usuario u = d.getByNome(getNome());
			if (u == null) {
				return;
			}
			VendaDAO v = new VendaDAO();
			vendas = (ArrayList<Venda>) v.getByUsuario(u);
		}catch(Error e) {
			Util.addMessageError("error ao filtrar");
		}
	}
	
	public void removerFiltro() {
		VendaDAO v = new VendaDAO();
		vendas = (ArrayList<Venda>) v.getByUltimas();
		nome = null;
	}
}
