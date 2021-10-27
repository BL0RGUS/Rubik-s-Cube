package JavaFx.ThreeDModel;

import JavaFx.CubeModel.Face;
import JavaFx.CubeModel.RubiksCube;
import JavaFx.Main;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

//this class handles the animation of the Rotation of the cube
//it animates the 3D model of the cube to rotate in the correct way before rotating the main RubiksCube data structure.
//the 3D model is then updated based on the RubiksCube data structure when the animation is finshed.

public class Rotation {
    private int n;
    private Cubie[][][] cube;
    private Model3D rubikCube3D;
    private RubiksCube rubiksCube;
    private int p;
    //The cube array has the current layout of the cube.
    //The beginning layout has all of the first index making up the front layer from top right to bottom left
    //the second index has the centre layer top right to bottom left
    //the last index has the back face top right to bottom left
    //The string rot contains the type of rotation being applied using rubiks cube notation which can be seen below
    //Upper case letters detail a rotation cw and X' is acw
    // F is the front layer
    // S is the layer between front and back, it stands for Standing
    // B is the back layer
    // R is the right layer
    // M is the middle layer between right and left
    // L is the left layer
    // U is the top layer (up)
    // E is the layer between the top and bottom layers (equator)
    // D is the bottom layer

    public Rotation(Model3D rubikCube3D, RubiksCube rubiksCube){
        this.rubikCube3D = rubikCube3D;
        this.rubiksCube = rubiksCube;
        this.p = 0;
        this.cube = rubikCube3D.getCubieArray();
        this.n = cube.length;

    }
    public void rotate(String rot, int speed){

        //could add matrices here!!!!;
        int a = 0;
        Timeline t = new Timeline();
        Point3D local;
        Rotate r;
       rubiksCube.rotate(rot);
       for(int y = 2; y >= 0; y--) {
            for (int x = 0; x < 3; x++) {
                //if (((x == 0) || (x == n - 1) || (y == 0) || (y == n - 1)) && ((a == 0) || (a == n - 1)) || (x == 0) || (x == n - 1)) {
                switch (rot) {
                        case "L":
                            local = this.cube[y][x][0].getCube().parentToLocal(new Point3D(-100, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][0].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "L'":
                            local = this.cube[y][x][0].getCube().parentToLocal(new Point3D(-100, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][0].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "L2":
                            local = this.cube[y][x][0].getCube().parentToLocal(new Point3D(-100, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][0].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 180)));
                        break;
                        case "M":
                            local = this.cube[y][x][1].getCube().parentToLocal(new Point3D(0, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][1].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "M'":
                            local = this.cube[y][x][1].getCube().parentToLocal(new Point3D(0, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][1].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "R":
                            local = this.cube[y][x][2].getCube().parentToLocal(new Point3D(100, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][2].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "R'":
                            local = this.cube[y][x][2].getCube().parentToLocal(new Point3D(100, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][2].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "R2":
                            local = this.cube[y][x][2].getCube().parentToLocal(new Point3D(100, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.X_AXIS);
                            this.cube[y][x][2].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -180)));
                            break;
                        case "U":
                            local = this.cube[y][0][x].getCube().parentToLocal(new Point3D(0, 100,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][0][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "U'":
                            local = this.cube[y][0][x].getCube().parentToLocal(new Point3D(0, 100,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][0][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "U2":
                            local = this.cube[y][0][x].getCube().parentToLocal(new Point3D(0, 100,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][0][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 180)));
                            break;
                        case "E":
                            local = this.cube[y][1][x].getCube().parentToLocal(new Point3D(0, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][1][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "E'":
                            local = this.cube[y][1][x].getCube().parentToLocal(new Point3D(0, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][1][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "E2":
                            local = this.cube[y][1][x].getCube().parentToLocal(new Point3D(0, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][1][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 180)));
                            break;
                        case "D":
                            local = this.cube[y][2][x].getCube().parentToLocal(new Point3D(0, -100,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][2][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "D'":
                            local = this.cube[y][2][x].getCube().parentToLocal(new Point3D(0, -100,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][2][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "D2":
                            local = this.cube[y][2][x].getCube().parentToLocal(new Point3D(0, -100,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Y_AXIS);
                            this.cube[y][2][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 180)));
                            break;
                        case "F":
                            local = this.cube[0][y][x].getCube().parentToLocal(new Point3D(0, 0,-100));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            this.cube[0][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "F'":
                            local = this.cube[0][y][x].getCube().parentToLocal(new Point3D(0, 0,-100));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            this.cube[0][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "F2":
                            local = this.cube[0][y][x].getCube().parentToLocal(new Point3D(0, 0,-100));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            this.cube[0][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -180)));
                            break;
                        case "S":
                            local = this.cube[1][y][x].getCube().parentToLocal(new Point3D(0, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            this.cube[1][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "S'":
                            local = this.cube[1][y][x].getCube().parentToLocal(new Point3D(0, 0,0));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            this.cube[1][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "B":
                            local = this.cube[2][y][x].getCube().parentToLocal(new Point3D(0, 0,100));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            this.cube[2][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -90)));
                            break;
                        case "B'":
                            local = cube[2][y][x].getCube().parentToLocal(new Point3D(0, 0,100));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            cube[2][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), 90)));
                            break;
                        case "B2":
                            local = this.cube[2][y][x].getCube().parentToLocal(new Point3D(0, 0,100));
                            r = new Rotate(0, local.getX(), local.getY(), local.getZ(), Rotate.Z_AXIS);
                            this.cube[2][y][x].getCube().getTransforms().add(r);
                            t.getKeyFrames().addAll(new KeyFrame(Duration.millis(speed), new KeyValue(r.angleProperty(), -180)));
                            break;
                    }
            }
            a++;
        }

       t.play();
       t.setOnFinished((e) -> {
           this.cube = rubiksCube.convertToCubeArray();
           Main.getMainCubeGroup().getChildren().clear();
           Main.getMainCubeGroup().getChildren().add(new AmbientLight(Color.WHITE));
           for (int z = 0; z < n; z++) {
               for (int y = 0; y < n; y++) {
                   //if ((z == 0) || (z == n - 1) || (x == 0) || (x == n - 1) || (y == 0) || (y == n - 1)) {
                   for (int x = 0; x < n; x++) {
                       this.cube[z][y][x].drawCube();
                   }
               }
           }
       });
    }



    public void printArray(Cubie[][][] array){
        int x = array.length;
        for(int i = 0; i < x; i++){
            for(int a = 0; a < x; a++){
                for(int b = 0; b < x; b++){
                    if((i == 0)||(i == n-1)|| (a == 0) ||(a== n-1) || (b == 0) || (b == n-1)) {
                        Face[] f = array[i][a][b].getFaces();
                        for(int r = 0; r < f.length; r++){
                            System.out.print(" " + f[r].getFace() + " ");
                        }
                        System.out.print("||");
                    }
                }
                System.out.print("          ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public  Cubie[][][] getCubeArray(){
        return this.cube;
    }

}
