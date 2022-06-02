package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cafe;
import model.Intencidade;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
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
	
}
