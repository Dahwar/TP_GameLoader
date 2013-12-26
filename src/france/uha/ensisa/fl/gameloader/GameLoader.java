package france.uha.ensisa.fl.gameloader;

import java.io.IOException;
import static java.lang.Math.random;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Florent
 */
public class GameLoader extends Application {
    
    static final double WIDTH=800, HEIGHT=600;
    
@Override
    public void start(Stage primaryStage) {
    
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
    
        // Background effect
        Timeline animation;
        Group backgroundEffect = new Group();
        Group layer1 = new Group();
        for(int i=0; i<15;i++) {
            Circle circle = new Circle(200,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.2f));
            circle.setStrokeWidth(4f);
            layer1.getChildren().add(circle);
        }
        
        Group layer2 = new Group();
        for(int i=0; i<20;i++) {
            Circle circle = new Circle(70,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.1f));
            circle.setStrokeWidth(2f);
            layer2.getChildren().add(circle);
        }
        
        Group layer3 = new Group();
        for(int i=0; i<10;i++) {
            Circle circle = new Circle(150,Color.web("white",0.05f));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white",0.16f));
            circle.setStrokeWidth(4f);
            layer3.getChildren().add(circle);
        }

        layer1.setEffect(new BoxBlur(30,30,2));
        layer2.setEffect(new BoxBlur(2,2,2));
        layer3.setEffect(new BoxBlur(12,12,2));

        Rectangle colors = new Rectangle(WIDTH, HEIGHT,
            new LinearGradient(0f,1f,1f,0f,true,CycleMethod.NO_CYCLE,
                new Stop(0.3f,Color.web("#64c2f8")),
                new Stop(0.7f,Color.web("#3263e2")))
        );
        colors.setBlendMode(BlendMode.OVERLAY);
        colors.widthProperty().bind(scene.widthProperty());
        colors.heightProperty().bind(scene.heightProperty());
        
        Rectangle blackRect = new Rectangle(WIDTH, HEIGHT, Color.BLACK);
        blackRect.widthProperty().bind(scene.widthProperty());
        blackRect.heightProperty().bind(scene.heightProperty());
        
        Group group = new Group(
                blackRect,
                layer1, 
                layer2,
                layer3,
                colors
        );
        backgroundEffect.getChildren().add(group);

        List<Node> allCircles = new ArrayList<>();
        allCircles.addAll(layer1.getChildren());
        allCircles.addAll(layer2.getChildren());
        allCircles.addAll(layer3.getChildren());

        animation = new Timeline();
        for(Node circle: allCircles) {
            animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0s
                    new KeyValue(circle.translateXProperty(),random()*WIDTH),
                    new KeyValue(circle.translateYProperty(),random()*HEIGHT)
                ),
                new KeyFrame(new Duration(40000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(),random()*WIDTH),
                    new KeyValue(circle.translateYProperty(),random()*HEIGHT)
                )
            );
        }
        animation.setAutoReverse(true);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    
        //Windows
        
        primaryStage.setTitle("Game Loader");
        primaryStage.setScene(scene);

        BorderPane bp = new BorderPane();
        root.getChildren().add(backgroundEffect);
        root.getChildren().add(bp);
            
        AnchorPane.setBottomAnchor(bp, 0.0);
        AnchorPane.setLeftAnchor(bp, 0.0);
        AnchorPane.setRightAnchor(bp, 0.0);
        AnchorPane.setTopAnchor(bp, 0.0);
        
        ScrollPane sp = new ScrollPane();
        final BorderPane right = new BorderPane();        
        
        bp.setLeft(sp);
        bp.setCenter(right);
        
        //Resources 
        Image imagePlay = new Image(GameLoader.class.getResource("images/buttonPlayBlue.png").toString());
        final ImageView iv = new ImageView(imagePlay);
        iv.setFitWidth(40.0);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        
        Font.loadFont(getClass().getResourceAsStream("fonts/FREE.ttf"), 20);
        
        scene.getStylesheets().add(GameLoader.class.getResource("css/stylesheet.css").toString());
        
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
        
         final FadeTransition fadeTransitionOn;
         fadeTransitionOn = FadeTransitionBuilder.create()
            .duration(Duration.seconds(0.25))
            .node(right)
            .fromValue(0)
            .toValue(1)
            .cycleCount(1)
            .autoReverse(false)
            .build();
        
        // Initialisation
        Text title = new Text("GameLoader");
        Text credits = new Text("Credits : Copyright 2013 LACROIX Florent. Tous droits réservés.");
        credits.getStyleClass().add("gameDetails");
        Text description = new Text("Bienvenue dans notre Game Loader !\n"
                + "Pour accéder à nos différents jeux, il suffit d'utiliser le menu à votre gauche !"
                + "\n\nPlay & Enjoy =)");
        description.getStyleClass().add("gameDescription");
        description.setWrappingWidth(450.0);
        description.setTextAlignment(TextAlignment.CENTER);
        
        title.getStyleClass().add("gameName");
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(credits, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(credits, new Insets(0, 5, 0, 0));
        
        right.setTop(title);
        right.setBottom(credits);
        right.setCenter(description);
         
        // List all the games
        GameLister gl = new GameLister();
        final List<Game> gameList = gl.listAllGames(Paths.get("games").toFile());
        
        // Create somes buttons...
        List<Button> buttonGameList = new LinkedList<>();
        
        Iterator<Game> it = gameList.iterator();
        while(it.hasNext()){
            final Game game = it.next();
            Label buttonLabel = new Label(game.getName());
            Button btn = new Button("   " + buttonLabel.getText() + "   ");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    fadeTransitionOff.play();
                    fadeTransitionOff.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                            Text nameT = new Text(game.getName());
                            Text descriptionT = new Text(game.getDescription());
                            Text authorT = new Text("Author : " + game.getAuthor());
                            Text ageT = new Text("Age : " + game.getAge());
                            Text categoryT = new Text("Category : " + game.getCategory());
                            Text dateT = new Text(game.getDate());
                            Text versionT = new Text("Version " + game.getVersion());

                            VBox details = new VBox();
                            details.getChildren().addAll(authorT, categoryT, ageT, dateT);
                            authorT.getStyleClass().add("gameDetails");
                            ageT.getStyleClass().add("gameDetails");
                            categoryT.getStyleClass().add("gameDetails");
                            dateT.getStyleClass().add("gameDetails");

                            VBox play = new VBox();
                            Button playB = new Button("Play Now !", iv);
                            playB.setContentDisplay(ContentDisplay.TOP);
                            playB.getStyleClass().add("buttonPlay");
                            playB.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                    Process proc = Runtime.getRuntime().exec("java -jar "+game.getJARFile().getPath());
                                    } catch (IOException e) {
                                    } 
                                }
                            });
                            play.getChildren().addAll(playB, versionT);

                            versionT.getStyleClass().add("gamePlay");
                            VBox.setMargin(versionT, new Insets(5, 0, 0, 0));
                            play.setAlignment(Pos.CENTER);
                            
                            AnchorPane bottom = new AnchorPane();
                            bottom.getChildren().addAll(details, play);
                            AnchorPane.setLeftAnchor(details, 0.0);
                            AnchorPane.setRightAnchor(play, 0.0);
                            AnchorPane.setBottomAnchor(details, 0.0);

                            right.setTop(nameT);
                            right.setBottom(bottom);
                            
                            ScrollPane centerScroll = new ScrollPane();
                            centerScroll.setContent(descriptionT);
                            centerScroll.setPrefWidth(460.0);
                            centerScroll.setMaxWidth(460.0);
                            centerScroll.setMinWidth(460.0);
                            BorderPane.setMargin(centerScroll, new Insets(10.0, 0.0, 10.0, 0.0));
                            centerScroll.getStyleClass().add("centerScrollPane");
                            right.setCenter(centerScroll);

                            nameT.getStyleClass().add("gameName");

                            BorderPane.setAlignment(nameT, Pos.CENTER);
                            descriptionT.setTextAlignment(TextAlignment.CENTER);
                            descriptionT.getStyleClass().add("gameDescription");
                            descriptionT.setWrappingWidth(450.0);

                            right.setPadding(new Insets(5.0));
                            
                            fadeTransitionOn.play();
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
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(listGameBtn.getWidth() + 2.0 + 700.0);
        primaryStage.show();
        
        sp.setPrefWidth(listGameBtn.getWidth()+15.0);
        sp.setMaxWidth(listGameBtn.getWidth()+15.0);
        sp.setMinWidth(listGameBtn.getWidth()+15.0);        
    }

public static void main(String[] args) {
        launch(args);
    }
}
