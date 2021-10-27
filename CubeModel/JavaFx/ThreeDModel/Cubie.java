package JavaFx.ThreeDModel;

import JavaFx.CubeModel.Face;
import JavaFx.Main;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

//this class is used to create a cubie object which make up the rubiks cube.
//it can change the color of its faces by altering this.face.
//the indexes in this.face represent the side of the cubie the color will go in the following order:
//0:front. 1:right, 2:top, 3:back, 4:left, 5:bottom.

public class Cubie {
    //positions of colours in image
    public static final float X_RED     = 0.5f / 7f;
    public static final float X_GREEN   = 1.5f / 7f;
    public static final float X_BLUE    = 2.5f / 7f;
    public static final float X_YELLOW  = 3.5f / 7f;
    public static final float X_ORANGE  = 4.5f / 7f;
    public static final float X_WHITE   = 5.5f / 7f;
    public static final float X_GRAY    = 6.5f / 7f;

    private final int sideLength = 90;
    PhongMaterial mat = new PhongMaterial();
    private MeshView cube;
    private int[] index;
    private Face[] faces;
    private TriangleMesh meshCube = new TriangleMesh();
    private Point3D position;


    public Cubie(Point3D position, Face[] faces){
        this.faces = faces;
        this.position = position;
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("/JavaFx/resources/pallete.png")));


    }

    public void drawCube(){
        meshCube.getTexCoords().addAll(
                X_RED, 0.5f,
                X_GREEN, 0.5f,
                X_BLUE, 0.5f,
                X_YELLOW, 0.5f,
                X_ORANGE, 0.5f,
                X_WHITE, 0.5f,
                X_GRAY, 0.5f
        );
        meshCube.getPoints().addAll(
                sideLength/2,  sideLength/2,  sideLength/2,  //point0
                sideLength/2, -sideLength/2,  sideLength/2,  //point1
                sideLength/2,  sideLength/2, -sideLength/2,  //point2
                sideLength/2, -sideLength/2, -sideLength/2,  //point3
                -sideLength/2,  sideLength/2,  sideLength/2, //point4
                -sideLength/2, -sideLength/2,  sideLength/2, //point5
                -sideLength/2,  sideLength/2, -sideLength/2, //point6
                -sideLength/2, -sideLength/2, -sideLength/2  //point7
        );
        //textures
        meshCube.getFaces().addAll(

                2,faces[0].getFace(),3,faces[0].getFace(),6,faces[0].getFace(),      // F
                3,faces[0].getFace(),7,faces[0].getFace(),6,faces[0].getFace(),

                0,faces[1].getFace(),1,faces[1].getFace(),2,faces[1].getFace(),      // R
                2,faces[1].getFace(),1,faces[1].getFace(),3,faces[1].getFace(),

                1,faces[2].getFace(),5,faces[2].getFace(),3,faces[2].getFace(),      // U
                5,faces[2].getFace(),7,faces[2].getFace(),3,faces[2].getFace(),

                0,faces[3].getFace(),4,faces[3].getFace(),1,faces[3].getFace(),      // B
                4,faces[3].getFace(),5,faces[3].getFace(),1,faces[3].getFace(),

                4,faces[4].getFace(),6,faces[4].getFace(),5,faces[4].getFace(),      // L
                6,faces[4].getFace(),7,faces[4].getFace(),5,faces[4].getFace(),

                0,faces[5].getFace(),2,faces[5].getFace(),4,faces[5].getFace(),      // D
                2,faces[5].getFace(),6,faces[5].getFace(),4,faces[5].getFace()

        );

        this.cube = new MeshView(meshCube);
        this.cube.setDrawMode(DrawMode.FILL);
        this.cube.setMaterial(mat);
        this.cube.setTranslateX(position.getX());
        this.cube.setTranslateY(position.getY());
        this.cube.setTranslateZ(position.getZ());
        Main.getMainCubeGroup().getChildren().add(this.cube);
    }


    public int getSideLength() {
        return this.sideLength;
    }

    public MeshView getCube() {return this.cube;}

    public Point3D getPosition() {
        return position;
    }

    public double getPositionX(){return (position.getX()/100)+1;}

    public double getPositionY(){return (position.getY()/100)+1;}

    public double getPositionZ(){return (position.getZ()/100)+1;}


    public int[] getIndex(){
        return this.index;
    }

    public void setIndex(int[] index1){this.index= index1; }

    public Face[] getFaces(){
        return faces;
    }

    public void setFaces(Face[] newFaces){
        System.arraycopy(newFaces, 0, faces, 0, 6);
    }


}
