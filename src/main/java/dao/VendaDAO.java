package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ItemVenda;
import model.Usuario;
import model.Venda;

public class VendaDAO implements DAO<Venda>{

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
		sql.append("  usuario, ");
		sql.append("  valor ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ? ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, Date.valueOf(obj.getData()));
			stat.setInt(2,obj.getUsuario().getId());
			stat.setDouble(3, obj.getValor());
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
		return null;

	}
	public List<Venda> getByUsuario(Usuario usuario) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Venda> lista = new ArrayList<Venda>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.valor, ");
		sql.append("  v.data ");
		sql.append("FROM ");
		sql.append("  venda v ");
		sql.append("WHERE ");
		sql.append(" v.usuario = ? ");
		sql.append("ORDER BY ");
		sql.append("  v.data DESC ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, usuario.getId());

			rs = stat.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setValor(rs.getDouble("valor"));
				venda.setData(rs.getDate("data").toLocalDate());
				
				
				lista.add(venda);
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

public Venda getByVenda(Venda venda) {
	Connection conn = DAO.getConnection();
	if (conn == null) {
		return null;
	}

	venda.setProdutos(new ArrayList<ItemVenda>());
	
	StringBuffer sql = new StringBuffer();
	sql.append("SELECT ");
	sql.append("  i.id, ");
	sql.append("  i.valor, ");
	sql.append("  i.quant, ");
	sql.append("  i.cafe_id ");
	sql.append("FROM ");
	sql.append("  item_venda i ");
	sql.append("WHERE ");
	sql.append("  i.venda_id = ? ");

	ResultSet rs = null;
	PreparedStatement stat = null;
	try {
		stat = conn.prepareStatement(sql.toString());
		stat.setInt(1, venda.getId());

		rs = stat.executeQuery();
		CafeDAO dao = new CafeDAO();
		while (rs.next()) {
			ItemVenda item = new ItemVenda();
			item.setId(rs.getInt("id"));
			item.setValor(rs.getDouble("valor"));
			item.setQuant(rs.getInt("quant"));
			item.setCafe(dao.getById(rs.getInt("cafe_id")));
			venda.getProdutos().add(item);
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
	return venda;
}

public List<Venda> getByUltimas() {
	Connection conn = DAO.getConnection();
	if (conn == null) {
		return null;
	}

	List<Venda> lista = new ArrayList<Venda>();

	StringBuffer sql = new StringBuffer();
	sql.append("SELECT ");
	sql.append("  v.id, ");
	sql.append("  v.valor, ");
	sql.append("  v.data ");
	sql.append("FROM ");
	sql.append("  venda v ");
	sql.append("ORDER BY ");
	sql.append("  v.id DESC ");
	sql.append("LIMIT 10 ");

	ResultSet rs = null;
	PreparedStatement stat = null;
	try {
		stat = conn.prepareStatement(sql.toString());

		rs = stat.executeQuery();
		while (rs.next()) {
			Venda venda = new Venda();
			venda.setId(rs.getInt("id"));
			venda.setValor(rs.getDouble("valor"));
			venda.setData(rs.getDate("data").toLocalDate());
			
			
			lista.add(venda);
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
