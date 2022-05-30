package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;

public class FornecedorDAO  implements DAO<Fornecedor>{

	@Override
	public boolean insert(Fornecedor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Fornecedor obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Fornecedor> getAll() {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Fornecedor> lista = new ArrayList<Fornecedor>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  id, ");
		sql.append("  marca, ");
		sql.append("  email ");
		sql.append("FROM ");
		sql.append("  fornecedor ");
		sql.append("ORDER BY ");
		sql.append(" id ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setId(rs.getInt("id"));
				f.setMarca(rs.getString("marca"));
				f.setEmail(rs.getString("email"));
				
				lista.add(f);
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
