package restserver;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import garbagecanmodule.GarbageCan;

/**
 * @author Elisa Oostwal
 * 
 * Implements the "main" program started by the apache tomcat server.
 * Enables HTML requests. Currently only implements GET (all garbage cans).
 */

@Path("/GarbageService")
public class GarbageService {
	
   GarbageDao garbageDao = new GarbageDao();

   @GET
   @Path("/garbagecans")
   @Produces(MediaType.APPLICATION_XML)
   public ArrayList<GarbageCan> getGarbagecans(){
      return garbageDao.getAllGarbageCans();
   }

}