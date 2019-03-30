package com.lucas.fornec.ws;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fornecedorDAO.FornecedorDAO;



@Path("FornecedorController")
public class FornecedorController {

	
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarFornecedores")
	public List<Fornecedor> listarFornecedores() throws ClassNotFoundException, SQLException{
		
		try {
			FornecedorDAO fornecDAO = new FornecedorDAO();
			return fornecDAO.listar();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obterFornecedor/{id}")
    public Fornecedor obterFornecedor(@PathParam("id") int id) throws SQLException, ClassNotFoundException {
    	try {
			FornecedorDAO fornecDAO = new FornecedorDAO();
			return fornecDAO.selecionar(id);
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirFornecedor")
    public Response inserirFornecedor(Fornecedor fornec) throws ClassNotFoundException, SQLException {
    	try {
    		FornecedorDAO fornecDAO = new FornecedorDAO();
    		fornecDAO.inserir(fornec);
    		return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
    }
	
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/alterarFornecedor")
    public Response alterarFornecedor(Fornecedor fornec) throws ClassNotFoundException, SQLException {
    	try {
			FornecedorDAO fornecDAO = new FornecedorDAO();
			fornecDAO.alterar(fornec);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
    }
    
    @DELETE
    @Path("/excluirFornecedor/{id}")
    public Response excluirFornecedor(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
    	try {
			FornecedorDAO fornecDAO = new FornecedorDAO();
			fornecDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (SQLException e) {
			Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
    }
}