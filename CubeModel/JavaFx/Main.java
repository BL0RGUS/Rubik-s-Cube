package JavaFx;

import JavaFx.CubeModel.RubiksCube;
import JavaFx.Solving.InsertAllEdges;
import JavaFx.Solving.Sunflower;
import JavaFx.Solving.WhiteFace;
import JavaFx.Solving.YellowFace;
import JavaFx.ThreeDModel.Animation;
import JavaFx.ThreeDModel.Model3D;
import JavaFx.ThreeDModel.Rotation;
import JavaFx.ThreeDModel.Scramble;
import JavaFx.UI.SmartGroup;
import javafx.application.Application;;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

//this is the main class, it sets the stage and the calls all the classes.

public class Main extends Application {

    private static int WIDTH = 1400;
    private static int HEIGHT = 800;


    private static final SmartGroup root = new SmartGroup();
    private static final SmartGroup MainCube = new SmartGroup();
    private static Model3D rubiksCube3D;
    private static RubiksCube rubiksCube;
    private static int n = 3;
    private static Rotation r;

    @Override
    public void start(Stage primaryStage) {
        //group is a collection of objects
        //ViewCamera has included method which has basic 3d camera
        ViewCamera camera = new ViewCamera(MainCube);
        //creates a cube and adds it to the main cube group.
        rubiksCube = new RubiksCube();
        rubiksCube3D = new Model3D(n);
        root.getChildren().addAll(new AmbientLight(Color.WHITE), MainCube);


        // buttons
         r = new Rotation(rubiksCube3D, rubiksCube);
        buttons(primaryStage, root);

        //sets scene
        Scene scene = new Scene(root, WIDTH, HEIGHT, true);
        scene.setFill(Color.CORNFLOWERBLUE);
        scene.setCamera(camera);
        primaryStage.setTitle("Rubik's Cube");
        primaryStage.setScene(scene);
        root.translateXProperty().set(WIDTH/2);
        root.translateYProperty().set(HEIGHT/2);
        camera.initMouseControl(scene, primaryStage);
        primaryStage.show();

    }

    public static void buttons(Stage stage, SmartGroup root){
        AtomicBoolean toggled = new AtomicBoolean(false);
        Button L = new Button("L");
        Button M = new Button("M");
        Button R = new Button("R");
        HBox row2  = new HBox(L, M, R);
        row2.setAlignment(Pos.CENTER);

        Button U = new Button("U");
        Button E = new Button("E");
        Button D = new Button("D" );
        HBox row1  = new HBox(U, E, D);
        row1.setAlignment(Pos.CENTER);

        Button F = new Button("F");
        Button S = new Button("S");
        Button B = new Button("B");
        HBox row3  = new HBox(F, S, B);
        row3.setAlignment(Pos.CENTER);

        Button toggle = new Button("Toggle");
        toggle.setStyle("-fx-font-size: 1.5em; ");
        Button scramble = new Button("Scramble");
        Button solve = new Button("Solve");
        Slider speed = new Slider(200, 5000, 350);
        Label l = new Label("Speed: ");
        HBox row6 = new HBox(l, speed);
        R.setOnAction((event) -> {
            r.rotate(R.getText(), (int) speed.getValue());
        });
        M.setOnAction((event) -> {
            r.rotate(M.getText(), (int) speed.getValue());
        });
        L.setOnAction((event) -> {
            r.rotate(L.getText(), (int) speed.getValue());
        });
        U.setOnAction((event) -> {
            r.rotate(U.getText(), (int) speed.getValue());
        });
        E.setOnAction((event) -> {
            r.rotate(E.getText(), (int) speed.getValue());
        });
        D.setOnAction((event) -> {
            r.rotate(D.getText(), (int) speed.getValue());
        });
        F.setOnAction((event) -> {
            r.rotate(F.getText(), (int) speed.getValue());
        });
        S.setOnAction((event) -> {
            r.rotate(S.getText(), (int) speed.getValue());
        });
        B.setOnAction((event) -> {
            r.rotate(B.getText(), (int) speed.getValue());
        });

        toggle.setOnAction((event) -> {
           if(toggled.get()) {
               R.setText("R");
               M.setText("M");
               L.setText("L");
               U.setText("U");
               E.setText("E");
               D.setText("D");
               F.setText("F");
               S.setText("S");
               B.setText("B");
               toggled.set(false);
           }else{
               R.setText("R'");
               M.setText("M'");
               L.setText("L'");
               U.setText("U'");
               E.setText("E'");
               D.setText("D'");
               F.setText("F'");
               S.setText("S'");
               B.setText("B'");
               toggled.set(true);
           }

        });
        scramble.setOnAction((event) -> {
            try {
                Scramble s =  new Scramble((int) speed.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        solve.setOnAction((event) -> {
            String moves = "";
            Sunflower s = new Sunflower(rubiksCube);
            moves += s.makeSunflower();
            WhiteFace w = new WhiteFace(s.getCube());
            moves += w.makeWhiteFace();
            InsertAllEdges i = new InsertAllEdges(w.getCube());
            moves += i.insertEdges();
            YellowFace y = new YellowFace(i.getCube());
            moves += y.makeYellowCross();
            moves += y.orientLastLayer();
            moves += y.permuteLastLayer();
            moves = y.optimizeMoves(moves);
            System.out.println("Optimised solution: " + moves);
            System.out.println("Length: " + moves.split(" ").length);
            Animation animation = new Animation(moves.split(" "), (int) speed.getValue());

        });
        VBox buttons = new VBox(row1, row2, row3, toggle, scramble, solve, row6);
        buttons.setTranslateX(500);
        buttons.setTranslateY(-400);
        buttons.setAlignment(Pos.CENTER);
        root.getChildren().add(buttons);
    }

    public static SmartGroup getMainCubeGroup() {
        return MainCube;
    }

    public static Model3D getRubiksCube(){
        return rubiksCube3D;
    }

    public static int getCubeSize(){
        return n;
    }

    public static void main(String[] args) {launch(args);}

    public static Rotation getR(){
        return r;
    }

}
