package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Util;
import aplication.UtilizadorFlash;
import dao.UsuarioDAO;
import model.TipoUsuario;
import model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable{

	private static final long serialVersionUID = 2999452247279382111L;
	private Usuario usuario;
	private ArrayList<Usuario> usuarios;

	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}
	
	public ArrayList<Usuario> getUsuarios() {
		UsuarioDAO u = new UsuarioDAO();
		usuarios = (ArrayList<Usuario>) u.getAll();
		if(usuarios == null) {
			usuarios = new ArrayList<Usuario>();
		}
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
	
	public void editar(int id) {
		UsuarioDAO u = new UsuarioDAO();
		UtilizadorFlash<Usuario> flash = new UtilizadorFlash<Usuario>();
		flash.inserir("usuario", u.getById(id));
		Util.redirect("cadastroUsuario.xhtml");
	}
	
	public void criar() {
		Util.redirect("cadastroUsuario.xhtml");
	}
	
	public void delete(int id) {
		UsuarioDAO u = new UsuarioDAO();
		u.delete(id);
	}
	
	
	public void voltar() {
		Util.redirect("/Moecafe/faces/principal.xhtml");
	}
}
