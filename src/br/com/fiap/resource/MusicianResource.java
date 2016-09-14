package br.com.fiap.resource;

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

import br.com.fiap.dao.MusicianDAO;
import br.com.fiap.dao.impl.MusicianDAOImpl;
import br.com.fiap.entity.Musician;
import br.com.fiap.exceptions.DBCommitException;
import br.com.fiap.exceptions.IdNotFoundException;
import br.com.fiap.singleton.EMFactorySingleton;

import com.google.gson.Gson;

@Path("/musician")
public class MusicianResource {

	private MusicianDAO dao;
	
	public MusicianResource() {
		dao = new MusicianDAOImpl(EMFactorySingleton.getInstance());
	}
	
	
	/**Cadastrar de músico - Verbo: POST **/
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(String json){
		Musician musician = new Gson().fromJson(json, Musician.class);
		try {
			dao.insert(musician);
			return Response.status(201).build();
		} catch (DBCommitException e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}
	
	/**Buscar músico por id - Verbo: GET **/
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String find(@PathParam("id") int codigo){
		Musician musician = dao.findById(codigo);
		return new Gson().toJson(musician);
	}
	
	/**Buscar todos os músico cadastrados- Verbo: GET **/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String list(){
		List<Musician> musicians = dao.list();
		return new Gson().toJson(musicians);
	}
	
	/**Deletar um músico pelo id - Verbo: DELETE **/
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		try {
			dao.delete(id);
			return Response.status(200).entity("Removed").build();
		} catch (DBCommitException | IdNotFoundException e) {
			e.printStackTrace();
		}
		return Response.status(500).entity("Error").build();
		
	}
	
	/**Alterar informações de um músico - Verbo: PUT **/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alter(String json){
		Musician musician = new Gson().fromJson(json, Musician.class);
		try {
			dao.update(musician);
			return Response.status(200).build();
		} catch (DBCommitException e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	
	
}






