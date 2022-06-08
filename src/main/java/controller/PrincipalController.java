package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Session;
import aplication.Util;
import aplication.UtilizadorFlash;
import model.TipoUsuario;
import model.Usuario;

@Named
@ViewScoped
public class PrincipalController implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private boolean admin;
	
	public PrincipalController() {
		if (getUsuario().getTipoDeUsuario() == TipoUsuario.ADMINISTRADOR) {
			admin = true;
		}else {
			admin = false;
		}
		
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
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean eAdmin() {
		if (usuario.getTipoDeUsuario() == TipoUsuario.ADMINISTRADOR) {
			return true;
		}
		return false;
	}
	
	public void conta() {
		UtilizadorFlash<Usuario> flash = new UtilizadorFlash<Usuario>();
		flash.inserir("usuario", usuario);
		Util.redirect("cadastroUsuario.xhtml?faces-redirect=true");
	}
}
