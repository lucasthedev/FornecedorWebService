package com.lucas.fornec.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("FornecedorController")
public class FornecedorController {

	List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarFornecedores")
	public List<Fornecedor> listarFornecedores(){
		
		Fornecedor fornecedor1 = new Fornecedor();
		fornecedor1.setNome("Tupy Metais");
		fornecedor1.setEmail("comercialtupy@tupy.com.br");
		fornecedor1.setComentario("Horário de atendimento: 08 às 18 horas.");
		fornecedor1.setCnpj("03.917.757/0001-32");
		
		Fornecedor fornecedor2 = new Fornecedor();
		fornecedor2.setNome("Tigre Tubos e Conexões");
		fornecedor2.setEmail("comercialtigre@tigre.com.br");
		fornecedor2.setComentario("Horário de atendimento: 06 às 21 horas.");
		fornecedor2.setCnpj("71.774.108/0001-80");
		
		Fornecedor fornecedor3 = new Fornecedor();
		fornecedor3.setNome("Docol");
		fornecedor3.setEmail("comercialdocol@docol.com.br");
		fornecedor3.setComentario("Horário de atendimento: 10 às 20 horas.");
		fornecedor3.setCnpj("60.357.752/0001-36");
		
		fornecedores.add(fornecedor1);
		fornecedores.add(fornecedor2);
		fornecedores.add(fornecedor3);
		
		return fornecedores;
		
	}
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obterFornecedor/{id}")
    public Fornecedor obterFornecedor(@PathParam("id") int id) {
    	Fornecedor fornec = new Fornecedor();
    	
    	fornec.setId(id);
    	fornec.setNome("Nome " + id);
    	fornec.setEmail("email " + id);
    	fornec.setComentario("comentario " + id);
    	fornec.setCnpj("cnpj " + id);
    	
    	return fornec;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserirFornecedor")
    public Response inserirFornecedor(Fornecedor fornec) {
    	fornecedores.add(fornec);
    	System.out.println(fornec.toString());
    	return Response.status(Response.Status.OK).build();
    }
	
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/alterarFornecedor")
    public Response alterarFornecedor(Fornecedor fornec) {
    	System.out.println(fornec.toString());
    	return Response.status(Response.Status.OK).build();
    }
    
    @DELETE
    @Path("/excluirFornecedor/{id}")
    public Response excluirFornecedor(@PathParam("id") int id) {
    	System.out.println("Deletando o fornecedor " + id);
    	return Response.status(Response.Status.OK).build();
    }
}
