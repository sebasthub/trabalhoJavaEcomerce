package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado. Faça o download.");
			e.printStackTrace();
			return null;
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://172.18.0.3:5432/moeCafe", "topicos1", "123456");
		} catch (SQLException e) {
			System.out.println("Problema ao conectar no banco de dados. Verifique as informacoes de conexao.");
			e.printStackTrace();
			return null;
		}

		return conn;
	}
	
	public boolean insert(T obj);
	public boolean update(T obj);
	public boolean delete(int id);
	public List<T> getAll();

}
