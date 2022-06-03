package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import aplication.Util;
import aplication.UtilizadorFlash;
import dao.UsuarioDAO;
import model.TipoUsuario;
import model.Usuario;

@Named
@RequestScoped
public class CadastroUsuarioController {
	private Usuario usuario;
	
	private CadastroUsuarioController() {
		UtilizadorFlash<Usuario> flash = new UtilizadorFlash<Usuario>();
		usuario = flash.pegar("usuario");
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
	
	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}
	
	public void criar() {
		usuario.setSenha(Util.hash(usuario));
		UsuarioDAO d = new UsuarioDAO();
		if(d.insert(usuario)) {
			Util.addMessageInfo("adicionado com sucesso");
		}else {
			Util.addMessageError("erro na inserção");
		}
		limpar();
		Util.redirect("usuario.xhtml");
	}
	
	public void limpar() {
		setUsuario(null);
	}
	
	public void delete() {
		UsuarioDAO u = new UsuarioDAO();
		if(u.delete(getUsuario().getId())) {
			Util.addMessageInfo("deletado com sucesso");
		}else {
			Util.addMessageError("error ao deletar");
		}
		limpar();
		Util.redirect("usuario.xhtml");
	}
	
	public void update() {
		UsuarioDAO u = new UsuarioDAO();
		Usuario usuarioComparativo = u.getById(usuario.getId());
		usuario.setSenha(Util.hash(usuario));
		if(usuario.equals(usuarioComparativo)) {
			if (u.update(usuario)) {
				Util.addMessageInfo("atualizado com sucesso");
			}else {
				Util.addMessageError("erro ao atualizar");
			}
		}else {
			Util.addMessageError("senha não confere");
		}
		Util.redirect("usuario.xhtml");
	}
	
}
