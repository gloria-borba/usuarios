package br.com.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static String DB_URL = "jdbc:h2:tcp://localhost/~poo";
	private static String USER = "admin";
	private static String PASS = "admin";

	public static Connection getConnection() {
		System.out.println("Conectando ao banco de dados");
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

