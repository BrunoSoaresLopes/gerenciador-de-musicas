package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection conectar() {
		try {
			return DriverManager.getConnection("jdbc:sqlite:musica.db");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar: " + e.getMessage());
			return null;
		}
	}   
}
