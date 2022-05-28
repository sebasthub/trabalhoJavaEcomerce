package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.TipoUsuario;
import model.Usuario;



public class UsuarioDAO implements DAO{

	@Override
	public boolean insert(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario Login(String login, String senha) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		Usuario usuario = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.login, ");
		sql.append("  u.senha, ");
		sql.append("  u.cpf, ");
		sql.append("  u.tipo_usuario ");
		sql.append("FROM ");
		sql.append("  usuario u ");
		sql.append("WHERE ");
		sql.append("  u.login = ? ");
		sql.append("  AND u.senha = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, login);
			stat.setString(2, senha);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setTipoDeUsuario(TipoUsuario.valueOf(rs.getInt("tipo_usuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("done");
		return usuario;
	}
}
