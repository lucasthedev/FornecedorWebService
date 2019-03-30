package infra;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoJDBC {
	
	public Connection getConexao();
	
	public void close();
	
	public void commit() throws SQLException;
	
	public void rollback();
	
}
