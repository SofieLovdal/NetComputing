package restserver;

import java.util.ArrayList;
import dataservermodule.DataServer;

/**
 * @author Elisa Oostwal
 * 
 * Contains the functions required for the REST server requests.
 * Currently only contains a function for obtaining all garbage cans,
 * since this is the only required functionality thus far.
 */
public class GarbageDao {
	
	DataServer dataServer;
	
	/**
	 * @return a list of all garbage cans.
	 * Obtains the most up-to-date list from the data server.
	 */
	public ArrayList<garbagecanmodule.GarbageCan> getAllGarbageCans() {
		return dataServer.getGarbageCans();
	}
	
	/**
	 * Construct a new GarbageDao and link a data server to it.
	 */
	public GarbageDao() {
	   this.dataServer = new DataServer();
	   new Thread(this.dataServer).start();
    }
   
}