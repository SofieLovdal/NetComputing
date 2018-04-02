package garbagecanmodule;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * @author Sofie Lovdal
 * 
 * Class required for parsing the xml file obtained from the REST server
 */
@XmlRootElement(name="garbageCans")
@XmlAccessorType (XmlAccessType.FIELD)
public class GarbageCanList {
    
    @XmlElement(name="garbagecan")
    private ArrayList<GarbageCan> list = null;
    
    public GarbageCanList() {}
    
    public ArrayList<GarbageCan> getGarbageCanList() {
        return this.list;
    }
    
    public void setGarbageCanList(ArrayList<GarbageCan> garbageCans) {
        this.list=garbageCans;
    }
    
}