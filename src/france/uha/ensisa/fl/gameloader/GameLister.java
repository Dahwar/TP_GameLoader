package france.uha.ensisa.fl.gameloader;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Florent
 */
public class GameLister {
    
    public GameLister() {}
    
    public List<Game> listAllGames(File gamesPath) {
        List<Game> gameList = new LinkedList<>();
        XMLReader xmlR = new XMLReader();
        
        String[] folderList=(gamesPath).list();
        String[] fileList;
        
        for(int i=0;i<folderList.length;i++){
            fileList=(Paths.get(gamesPath.toString(), folderList[i]).toFile()).list();
            Game game = new Game();
            for(int j=0;j<fileList.length;j++){
                if(fileList[j].endsWith(".xml")){
                    game.setAll(xmlR.convertXMLFiletoHashMap(Paths.get(gamesPath.toString(), folderList[i], fileList[j]).toFile()));
                    game.setXMLFile(Paths.get(gamesPath.toString(), folderList[i], fileList[j]).toFile());
                }
                if(fileList[j].endsWith(".jar")){
                    game.setJARFile(Paths.get(gamesPath.toString(), folderList[i], fileList[j]).toFile());
                }
            }
            if(game.getXMLFile()!=null && game.getJARFile()!=null)
                gameList.add(game);
        }
        return gameList;
    }
}
