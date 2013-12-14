package france.uha.ensisa.fl.gameloader;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Florent
 */
public class Game {
    private String name;
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
        this.name = hm.get("name");
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
        this.name = hm.get("name");
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
