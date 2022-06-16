package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cafe;
import model.Intencidade;
import model.TipoUsuario;
import model.Usuario;

public class CafeDAO implements DAO<Cafe>{

	@Override
	public boolean insert(Cafe obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO cafe ( ");
		sql.append("  nome, ");
		sql.append("  valor, ");
		sql.append("  tipo, ");
		sql.append("  fornecedor, ");
		sql.append("  intencidade, ");
		sql.append("  \"localDeProducao\" ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ? ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setDouble(2, obj.getValor());
			stat.setString(3, obj.getTipo());
			stat.setInt(4, obj.getFornecedor().getId());
			stat.setInt(5, obj.getIntencidade().getId());
			stat.setString(6, obj.getLocalDeProducao());
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
	public boolean update(Cafe obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE cafe SET  ");
		sql.append("  nome = ?, ");
		sql.append("  valor = ?, ");
		sql.append("  tipo = ?, ");
		sql.append("  fornecedor = ?, ");
		sql.append("  intencidade = ?, ");
		sql.append("  \"localDeProducao\" = ? ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setDouble(2, obj.getValor());
			stat.setString(3, obj.getTipo());
			stat.setInt(4, obj.getFornecedor().getId());
			stat.setInt(5, obj.getIntencidade().getId());
			stat.setString(6, obj.getLocalDeProducao());
			stat.setInt(7, obj.getId());

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
	public boolean delete(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM cafe ");
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
	public List<Cafe> getAll() {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		} 

		List<Cafe> lista = new ArrayList<Cafe>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  id, ");
		sql.append("  nome, ");
		sql.append("  tipo, ");
		sql.append("  fornecedor, ");
		sql.append("  image, ");
		sql.append("  intencidade, ");
		sql.append("  valor ");
		sql.append("FROM ");
		sql.append("  cafe ");
		sql.append("ORDER BY ");
		sql.append(" id ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Cafe cafe = new Cafe();
				cafe.setId(rs.getInt("id"));
				cafe.setNome(rs.getString("nome"));
				cafe.setTipo(rs.getString("tipo"));
				cafe.setValor(rs.getDouble("valor"));
				cafe.setImage(rs.getString("image"));
				cafe.setIntencidade(Intencidade.valueOf(rs.getInt("intencidade")));
				FornecedorDAO f = new FornecedorDAO();
				cafe.setFornecedor(f.getById(rs.getInt("fornecedor")));
				
				lista.add(cafe);
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
	
	public Cafe getById(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		Cafe cafe = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  id, ");
		sql.append("  nome, ");
		sql.append("  valor, ");
		sql.append("  tipo, ");
		sql.append("  fornecedor, ");
		sql.append("  intencidade, ");
		sql.append("  image, ");
		sql.append("  \"localDeProducao\" ");
		sql.append("FROM ");
		sql.append("  cafe ");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				cafe = new Cafe();
				cafe.setId(rs.getInt("id"));
				cafe.setNome(rs.getString("nome"));
				cafe.setValor(rs.getDouble("valor"));
				cafe.setTipo(rs.getString("tipo"));
				cafe.setImage(rs.getString("image"));
				FornecedorDAO f = new FornecedorDAO();
				cafe.setFornecedor(f.getById(rs.getInt("fornecedor")));
				cafe.setIntencidade(Intencidade.valueOf(rs.getInt("intencidade")));
				cafe.setLocalDeProducao(rs.getString("localDeProducao"));
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
		return cafe;

	}
}
