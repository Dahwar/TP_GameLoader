package france.uha.ensisa.fl.gameloader;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Florent
 */
public class GameLoader extends Application {
    
@Override
    public void start(Stage primaryStage) {
  
        //Resources 
        Image imageOk = new Image(GameLoader.class.getResource("images/ok.png").toString());
        final ImageView iv = new ImageView(imageOk);
        iv.setFitWidth(40.0);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
    
        //Windows
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 800, 600);

        BorderPane bp = new BorderPane();
        root.getChildren().add(bp);
        
        AnchorPane.setBottomAnchor(bp, 0.0);
        AnchorPane.setLeftAnchor(bp, 0.0);
        AnchorPane.setRightAnchor(bp, 0.0);
        AnchorPane.setTopAnchor(bp, 0.0);
        
        ScrollPane sp = new ScrollPane();
        final BorderPane right = new BorderPane();        
        
        bp.setLeft(sp);
        bp.setCenter(right);
        
        // Animations
        final FadeTransition fadeTransitionOff;
        fadeTransitionOff = FadeTransitionBuilder.create()
            .duration(Duration.seconds(0.25))
            .node(right)
            .fromValue(1)
            .toValue(0)
            .cycleCount(1)
            .autoReverse(false)
            .build();

        FadeTransition essai;
        
         final FadeTransition fadeTransitionOn;
         fadeTransitionOn = FadeTransitionBuilder.create()
            .duration(Duration.seconds(0.25))
            .node(right)
            .fromValue(0)
            .toValue(1)
            .cycleCount(1)
            .autoReverse(false)
            .build();
         
        // List all the games
        GameLister gl = new GameLister();
        final List<Game> gameList = gl.listAllGames(Paths.get("games").toFile());
        
        // Create somes buttons...
        List<Button> buttonGameList = new LinkedList<>();
        
        Iterator<Game> it = gameList.iterator();
        while(it.hasNext()){
            final Game game = it.next();
            Button btn = new Button("   " + game.getName() + "   ");
            btn.setStyle("-fx-font: 15 arial; -fx-base: #2870FF; -fx-label-padding: 5;");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    fadeTransitionOff.play();
                    fadeTransitionOff.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            fadeTransitionOn.play();

                            Text nameT = new Text(game.getName());
                            Text descriptionT = new Text(game.getDescription());
                            Text authorT = new Text("Author : " + game.getAuthor());
                            Text ageT = new Text("Age : " + game.getAge());
                            Text categoryT = new Text("Category : " + game.getCategory());
                            Text dateT = new Text(game.getDate());
                            Text versionT = new Text("Version " + game.getVersion());

                            VBox details = new VBox();
                            details.getChildren().addAll(authorT, categoryT, ageT, dateT);

                            VBox play = new VBox();
                            Button playB = new Button("Play Now !", iv);
                            playB.setContentDisplay(ContentDisplay.TOP);
                            play.getChildren().addAll(playB, versionT);

                            AnchorPane bottom = new AnchorPane();
                            bottom.getChildren().addAll(details, play);
                            AnchorPane.setLeftAnchor(details, 0.0);
                            AnchorPane.setRightAnchor(play, 0.0);

                            right.setTop(nameT);
                            right.setBottom(bottom);
                            right.setCenter(descriptionT);

                            nameT.setStyle("-fx-font: 100 arial;");
                            nameT.setFill(Color.BLACK);

                            BorderPane.setAlignment(nameT, Pos.CENTER);
                            descriptionT.setTextAlignment(TextAlignment.CENTER);

                            descriptionT.setWrappingWidth(450.0);

                            right.setPadding(new Insets(5.0));
                        }
                    });
                }
            });
            buttonGameList.add(btn);
        }
        
        // Display this buttons...
        VBox listGameBtn = new VBox();
        Iterator<Button> it2 = buttonGameList.iterator();
        while(it2.hasNext()){
            Button btn = it2.next();
            btn.setMaxWidth(Double.MAX_VALUE);
            VBox.setMargin(btn, new Insets(8, 8, 8, 8));
            listGameBtn.getChildren().add(btn);
        }
        sp.setContent(listGameBtn);
        
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Game Loader");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(listGameBtn.getWidth() + 2.0 + 550.0);
        
        sp.setPrefWidth(listGameBtn.getWidth()+2.0);
        sp.setMaxWidth(listGameBtn.getWidth()+2.0);
        sp.setMinWidth(listGameBtn.getWidth()+2.0);        
    }

public static void main(String[] args) {
        launch(args);
    }
}
