package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Util;
import aplication.UtilizadorFlash;
import dao.CafeDAO;
import dao.FornecedorDAO;
import model.Cafe;
import model.Fornecedor;
import model.Intencidade;
import model.TipoUsuario;

@Named
@ViewScoped
public class CafeController implements Serializable{
	private static final long serialVersionUID = 5362104422512133524L;
	
	private Cafe cafe;
	private ArrayList<Cafe> cafes;
	private List<Fornecedor> listaFornecedores;
	
	public void criar() {
		Util.redirect("cadastroCafe.xhtml");

	}

	public void delete(int id) {
		CafeDAO c = new CafeDAO();
		if (c.delete(id)) {
			Util.addMessageInfo("atualizado");
		}else {
			Util.addMessageError("erro no update");
		}

	}
	
	public void editar(int id) {
		CafeDAO c = new CafeDAO();
		UtilizadorFlash<Cafe> flash = new UtilizadorFlash<Cafe>();
		flash.inserir("cafe", c.getById(id));
		Util.redirect("cadastroCafe.xhtml");
	}
	
	public Cafe getCafe() {
		if(cafe == null) {
			cafe = new Cafe();
		}
		return cafe;
	}
	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}
	public ArrayList<Cafe> getCafes() {
		if(cafes == null) {
			CafeDAO c = new CafeDAO();
			cafes =  (ArrayList<Cafe>) c.getAll();
			if(cafes == null) {
				cafes = new ArrayList<Cafe>();
			}
		}
		return cafes;
	}
	public void setCafes(ArrayList<Cafe> cafes) {
		this.cafes = cafes;
	}
	public List<Fornecedor> getListaFornecedores() {
		if(listaFornecedores == null) {
			FornecedorDAO f = new FornecedorDAO();
			listaFornecedores = f.getAll();
			if (listaFornecedores == null) {
				listaFornecedores = new ArrayList<Fornecedor>();
			}
		}
		return listaFornecedores;
	}
	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}
	public Intencidade[] getListaIntencidade() {
		return Intencidade.values();
	}
}
