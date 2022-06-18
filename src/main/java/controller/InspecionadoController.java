package controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import aplication.Session;
import model.Usuario;
import model.Venda;

@Named
@RequestScoped
public class InspecionadoController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9019876551695222697L;
	
	Venda venda;
	
	public InspecionadoController() {
		venda = (Venda) Session.getInstance().get("inspect");
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
}
