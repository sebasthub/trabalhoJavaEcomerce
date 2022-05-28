package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import model.Usuario;

@Named
@RequestScoped
public class UsuarioController {
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
