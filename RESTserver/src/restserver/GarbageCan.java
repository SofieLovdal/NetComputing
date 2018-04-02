package restserver;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "garbagecan")

/**
 * @author Elisa Oostwal
 * 
 * Implements a dummy class GarbageCan which is passed on by the REST server.
 *
 */

public class GarbageCan implements Serializable {

   private static final long serialVersionUID = 1L;
   
   private int ID;
   private String location;
   private Status status;

   public GarbageCan(){}

   public GarbageCan(int ID, String location) {
      this.ID = ID;
      this.location = location;
      this.status = new Status();
   }

   public int getID() {
      return ID;
   }
   
   @XmlElement
   public void setID(int ID) {
      this.ID = ID;
   }
   
   public String getLocation() {
      return location;
   }
   
   @XmlElement
      public void setLocation(String location) {
      this.location = location;
   }
   
   public Status getStatus() {
      return status;
   }
   
   @XmlElement
   public void setStatus(Status status) {
      this.status = status;
   }	

   @Override
   public boolean equals(Object object){
      if (object == null) {
         return false;
      } else if(!(object instanceof GarbageCan)) {
         return false;
      } else {
         GarbageCan user = (GarbageCan)object;
         if (ID == user.getID() && location.equals(user.getLocation())
            && status.equals(user.getStatus()) ){
            return true;
         }			
      }
      return false;
   }	
   
}