package model;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Usuario {
	private Integer id;
	@NotEmpty
	private String login;
	@NotEmpty
	private String senha;
	@NotEmpty
	private String cpf;
	@NotNull
	private TipoUsuario tipoDeUsuario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public TipoUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}
	public void setTipoDeUsuario(TipoUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(senha, other.senha);
	}
	
	
	
	
	
}
