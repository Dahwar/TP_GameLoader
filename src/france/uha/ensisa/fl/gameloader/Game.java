package france.uha.ensisa.fl.gameloader;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Florent
 */
public class Game {
    private String name;
    private String namefull;
    private String description;
    private String author;
    private String version;
    private String date;
    private String age;
    private String category;
    
    private File XMLfile = null;
    private File JARfile = null;
    
    public Game() {}
    
    public Game(HashMap<String, String> hm) {
        if(hm.get("name").length() > 10)
            this.name = hm.get("name").substring(0,10).concat("...");
        else
            this.name = hm.get("name");
        this.namefull = hm.get("name");
        this.description = hm.get("description");
        this.author = hm.get("author");
        this.version = hm.get("version");
        this.date = hm.get("date");
        this.age = hm.get("age");
        this.category = hm.get("category");
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNameFull() {
        return this.namefull;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getAge() {
        return this.age;
    }
    
    public String getCategory() {
        return this.category;
    }
    
    public File getXMLFile() {
        return this.XMLfile;
    }
    
    public File getJARFile() {
        return this.JARfile;
    }
    
    public void setAll(HashMap<String, String> hm) {
      
        if(hm.get("name").length() > 9)
            this.name = hm.get("name").substring(0,9).concat("...");
        else
            this.name = hm.get("name");
        this.namefull = hm.get("name");
        this.description = hm.get("description");
        this.author = hm.get("author");
        this.version = hm.get("version");
        this.date = hm.get("date");
        this.age = hm.get("age");
        this.category = hm.get("category");
    }
    
    public void setXMLFile(File XMLfile) {
        this.XMLfile = XMLfile;
    }
    
    public void setJARFile(File JARfile) {
        this.JARfile = JARfile;
    } 
}
