package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoPostgresJDBC implements ConexaoJDBC {
	
	private Connection connection = null;
	
	public ConexaoPostgresJDBC() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		
		Properties properties = new Properties();
		properties.put("user", "postgres");
		properties.put("password", "123456lu");
		
		this.connection = DriverManager.getConnection("jdbc:postgresql://localhost/WebService", properties);
		this.connection.setAutoCommit(false);
	}

	@Override
	public Connection getConexao() {
		return this.connection;
	}

	@Override
	public void close() {
		
		if(this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE,null,e);
			}
		}
		
	}

	@Override
	public void commit() throws SQLException {
		
		this.connection.commit();
		this.close();
	}

	@Override
	public void rollback() {
		
		if(this.connection != null) {
			try {
				this.connection.rollback();
			} catch (SQLException e) {
				Logger.getLogger(ConexaoPostgresJDBC.class.getName()).log(Level.SEVERE,null,e);
			} finally {
				this.close();
			}
		}
		
	}
	
	

}
