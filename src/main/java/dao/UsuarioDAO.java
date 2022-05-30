package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoUsuario;
import model.Usuario;



public class UsuarioDAO implements DAO<Usuario>{

	@Override
	public boolean insert(Usuario obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ( ");
		sql.append("  cpf, ");
		sql.append("  login, ");
		sql.append("  senha, ");
		sql.append("  tipo_usuario ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ? ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCpf());
			stat.setString(2, obj.getLogin());
			stat.setString(3, obj.getSenha());
			stat.setInt(4, obj.getTipoDeUsuario().getId());

			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean update(Usuario obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario SET  ");
		sql.append("  cpf = ?, ");
		sql.append("  login = ?, ");
		sql.append("  senha = ?, ");
		sql.append("  tipo_usuario = ? ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCpf());
			stat.setString(2, obj.getLogin());
			stat.setString(3, obj.getSenha());
			stat.setInt(4, obj.getTipoDeUsuario().getId());
			stat.setInt(5, obj.getId());

			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public Usuario getById(int id) {

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
		sql.append("  u.id = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
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
		return usuario;
	}
	
	@Override
	public boolean delete(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Usuario> getAll() {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Usuario> lista = new ArrayList<Usuario>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.cpf, ");
		sql.append("  u.login, ");
		sql.append("  u.senha, ");
		sql.append("  u.tipo_usuario ");
		sql.append("FROM ");
		sql.append("  usuario u ");
		sql.append("ORDER BY ");
		sql.append("  u.id ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipoDeUsuario(TipoUsuario.valueOf(rs.getInt("tipo_usuario")));

				lista.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = null;
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
		return lista;
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
