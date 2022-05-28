package controller;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
		//UsuarioDAO u = new UsuarioDAO();
		//u.Login("sebas", "1234");
		System.out.println("babtata");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
