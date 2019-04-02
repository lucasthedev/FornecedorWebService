package fornecedorDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lucas.fornec.ws.Fornecedor;

import infra.ConexaoJDBC;
import infra.ConexaoPostgresJDBC;

public class FornecedorDAO {
	
	private final ConexaoJDBC conexao;
	
	public FornecedorDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoPostgresJDBC();
	}
	
	public int inserir(Fornecedor fornec) throws SQLException, ClassNotFoundException{
		int id = 0;
		
		String sqlQuery = "INSERT INTO fornecedor (nome, email, comentario, cnpj) VALUES (?,?,?,?) RETURNING id";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setString(1, fornec.getNome());
			stmt.setString(2, fornec.getEmail());
			stmt.setString(3, fornec.getComentario());
			stmt.setString(4, fornec.getCnpj());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id");
			}
			
			this.conexao.commit();
			
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		
		return id;
	}
	
	public int alterar(Fornecedor fornec) throws SQLException, ClassNotFoundException{
		String sqlQuery = "UPDATE fornecedor SET nome = ?, email = ?, comentario = ?, cnpj = ? WHERE id = ?";
		int linhasAfetadas = 0;
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setString(1, fornec.getNome());
			stmt.setString(2, fornec.getEmail());
			stmt.setString(3, fornec.getComentario());
			stmt.setString(4, fornec.getCnpj());
			stmt.setInt(5, fornec.getId());
			
			linhasAfetadas = stmt.executeUpdate();
			
			this.conexao.commit();
			
			
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		
		return linhasAfetadas;
	}
	
	public List<Fornecedor> listar() throws SQLException, ClassNotFoundException{
		String sqlQuery = "SELECT id, nome, email, comentario, cnpj FROM fornecedor ORDER BY nome";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
			
			while(rs.next()) {
				fornecedores.add(parser(rs));
			}
			
			return fornecedores;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public int excluir(int id) throws SQLException, ClassNotFoundException{
		int linhasAfetas = 0;
		
		String sqlQuery = "DELETE FROM fornecedor WHERE id = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			linhasAfetas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		
		return linhasAfetas;
	}
	
	public Fornecedor selecionar(int id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT id, nome, email, comentario, cnpj FROM fornecedor WHERE id = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return parser(rs);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return null;
	}
	
	private Fornecedor parser(ResultSet resultSet) throws SQLException {
		Fornecedor fornec = new Fornecedor();
		
		fornec.setId(resultSet.getInt("id"));
		fornec.setNome(resultSet.getString("nome"));
		fornec.setEmail(resultSet.getString("email"));
		fornec.setComentario(resultSet.getString("comentario"));
		fornec.setCnpj(resultSet.getString("cnpj"));
		
		return fornec;
	}
	
}
