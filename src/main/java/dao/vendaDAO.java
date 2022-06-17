package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.ItemVenda;
import model.Venda;

public class vendaDAO implements DAO<Venda>{

	@Override
	public boolean insert(Venda obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO venda ( ");
		sql.append("  data, ");
		sql.append("  valor ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ? ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, Date.valueOf(obj.getData()));
			stat.setDouble(2, obj.getValor());
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt("id"));
			}
			
			salvarItensVenda(obj, conn);
			
			conn.commit();

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
	
	private void salvarItensVenda(Venda obj, Connection conn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO item_venda ( ");
		sql.append("  valor, ");
		sql.append("  quant, ");
		sql.append("  cafe_id, ");
		sql.append("  venda_id ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		for (ItemVenda itemVenda : obj.getProdutos()) {
			stat = conn.prepareStatement(sql.toString());
			stat.setDouble(1, itemVenda.getValor());
			stat.setInt(2, itemVenda.getQuant());
			stat.setInt(3, itemVenda.getCafe().getId());
			stat.setInt(4, obj.getId());
			stat.execute();
		}
	}

	@Override
	public boolean update(Venda obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Venda> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
