package controller;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import dao.UsuarioDAO;
import model.Usuario;

@Named
@RequestScoped
public class LoginController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6924510484517579401L;
	private Usuario usuario = new Usuario();
	
	public void entrar() {
		UsuarioDAO u = new UsuarioDAO();
		usuario.setSenha(Util.hash(usuario));
		usuario = u.Login(usuario.getLogin(), usuario.getSenha());
		if (usuario != null) {
			Session.getInstance().set("login", usuario);
			Util.redirect("principal.xhtml");
		}else {
			Util.addMessageError("usuario ou senha invalido");
		}
		
	}
	
	public void limpar() {
		usuario = null;

	}

	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
