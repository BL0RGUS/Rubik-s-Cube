package JavaFx.ThreeDModel;

import JavaFx.CubeModel.Face;
import JavaFx.UI.SmartGroup;
import javafx.geometry.Point3D;
import javafx.scene.paint.PhongMaterial;

//the model 3D class dynamically generates a rubik's cube of size n using the cubie class.

public class Model3D {

    private static Face red = new Face(0);
    private static Face green = new Face(1);
    private static Face blue = new Face(2);
    private static Face yellow = new Face(3);
    private static Face orange = new Face(4);
    private static Face white = new Face(5);
    private static Face grey = new Face(6);

    private static int n;

    private static SmartGroup group = new SmartGroup();
    public static final PhongMaterial mat = new PhongMaterial();

    //front , right, top, back, left, bottom

    public static Cubie[][][] cubieArray;

    public Model3D(int n){
        cubieArray = new Cubie[n][n][n];
        this.n = n;
        int c = ((n-1)*100)/(n-1);
        int X;
        int Y;
        int Z = -((n-1)*100)/2;;
        for(int z = 0; z < n; z++) {
            if(z!= 0) {
                Z +=c;
            }
            Y = -((n-1)*100)/2;
            for (int y = 0; y < n; y++) {
                if(y!= 0){
                    Y += c;
                }
                X = -((n-1)*100)/2;
                for (int x = 0; x < n; x++) {
                    //if((z == 0)||(z == n-1)|| (x == 0) ||(x == n-1) || (y == 0) || (y == n-1)) {
                        Point3D point = new Point3D(X, Y, Z);
                        cubieArray[z][y][x] = new Cubie(point, new Face[]{grey, grey, grey, grey, grey, grey});

                    //}
                    X += c;
                }
            }
        }

        for(int z = 0; z < n; z++) {
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    //right and left sides
                    if (x == 0) {
                        Face[] faces = cubieArray[z][y][x].getFaces();
                        faces[4] = red;
                        cubieArray[z][y][x].setFaces(faces);
                    } else if (x == n - 1) {
                        Face[] faces = cubieArray[z][y][x].getFaces();
                        faces[1] = green;
                        cubieArray[z][y][x].setFaces(faces);
                    }
                    //top and bottom sides
                    if (y == 0) {
                        Face[] faces = cubieArray[z][y][x].getFaces();
                        faces[2] = white;
                        cubieArray[z][y][x].setFaces(faces);
                    } else if (y == n - 1) {
                        Face[] faces = cubieArray[z][y][x].getFaces();
                        faces[5] = yellow;
                        cubieArray[z][y][x].setFaces(faces);
                    }
                    //front and back sides
                    if (z == 0) {
                        Face[] faces = cubieArray[z][y][x].getFaces();
                        faces[0] = blue;
                        cubieArray[z][y][x].setFaces(faces);
                    } else if (z == n - 1) {
                        Face[] faces = cubieArray[z][y][x].getFaces();
                        faces[3] = orange;
                        cubieArray[z][y][x].setFaces(faces);
                    }
                    //if((z == 0)||(z == n-1)|| (x == 0) ||(x == n-1) || (y == 0) || (y == n-1)) {
                        cubieArray[z][y][x].drawCube();
                    //}
                }
            }
        }
    }


    public static SmartGroup getCube() {
        return group;
    }

    public static Cubie[][][] getCubieArray(){
        return cubieArray;
    }

    public static void setCubieArray(Cubie[][][] array){
        for(int y = 0; y < array.length; y++){
            for(int x = 0; x < array.length; x++){
                System.arraycopy(cubieArray[y][x], 0, array[y][x], 0, 3);
            }
        }
    }
}