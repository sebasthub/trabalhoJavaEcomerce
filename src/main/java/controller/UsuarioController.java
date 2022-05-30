package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import aplication.Util;
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

	public void criar() {
		usuario.setSenha(Util.hash(usuario));
		UsuarioDAO d = new UsuarioDAO();
		if(d.insert(usuario)) {
			Util.addMessageInfo("adicionado com sucesso");
		}else {
			Util.addMessageError("erro na inserção");
		}
		limpar();
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
	}
	
	public void editar(int id) {
		UsuarioDAO u = new UsuarioDAO();
		setUsuario(u.getById(id));
	}
	
	public void delete(int id) {
		UsuarioDAO u = new UsuarioDAO();
		u.delete(id);
		limpar();
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
	}
	
	public void voltar() {
		Util.redirect("/Moecafe/faces/principal.xhtml");
	}
}
