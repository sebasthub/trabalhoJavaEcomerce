package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import model.Usuario;

@Named
@ViewScoped
public class PrincipalController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
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
	
	

}
