package france.uha.ensisa.fl.gameloader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Florent
 */
public class XMLReader {
    public XMLReader() {}
    
    public HashMap<String,String> convertXMLFiletoHashMap(File file) {
        
        Document document;
        Element root;
        SAXBuilder sxb = new SAXBuilder();
        HashMap<String, String> hm = new HashMap();
     
        try {
            document = sxb.build(file);
            root = document.getRootElement();
            List gameDescription = root.getChildren("informations");
            if(gameDescription.size()==1){
                Iterator it = gameDescription.iterator();
                while(it.hasNext())
                {
                   Element current = (Element)it.next();
                   hm.put("name", current.getChild("name").getText());
                   hm.put("description", current.getChild("description").getText());
                   hm.put("author", current.getChild("author").getText());
                   hm.put("version", current.getChild("version").getText());
                   hm.put("date", current.getChild("date").getText());
                   hm.put("age", current.getChild("age").getText());
                   hm.put("category", current.getChild("category").getText());
                }
            }
            return hm;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
