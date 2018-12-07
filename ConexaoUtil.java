package br.edu.devmedia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class ConexaoUtil {
	
	private static ResourceBundle config;
	
	private static ConexaoUtil conexaoUtil;
	
	private ConexaoUtil() {
		config = ResourceBundle.getBundle("config");
	}
	
	public static ConexaoUtil getInstance() {
		if (conexaoUtil == null) {
			conexaoUtil = new ConexaoUtil();			
		}
		return conexaoUtil;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(config.getString("br.edu.devmedia.bd.driver.mysql"));
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/teste_devmedia?useSSL=false&useTimezone=true&serverTimezone=" + TimeZone.getDefault().getID(), 
				config.getString("br.edu.devmedia.bd.usuario"), config.getString("br.edu.devmedia.bd.senha"));
//		return DriverManager.getConnection(config.getString("br.edu.devmedia.bd.url.conexao"), 
//				config.getString("br.edu.devmedia.bd.usuario"), config.getString("br.edu.devmedia.bd.senha"));
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getInstance().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
