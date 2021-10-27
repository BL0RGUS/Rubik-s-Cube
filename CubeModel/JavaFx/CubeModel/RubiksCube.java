package JavaFx.CubeModel;

import JavaFx.ThreeDModel.Cubie;
import javafx.geometry.Point3D;


//this class is the main rubiks cube data structure it represents the cube as an array of 6 64 bit integers each representing a face.
// A face can represented by 8 8 bit numbers, the centre cubie in any face is constant as it doesn't change.
//For example the face:
/*
    WGW
    BBY
    RGB
 */
//could be represented as the binary 00000101 00000001 00000101 00000010 00000010 00000011 00000000 00000001 00000010
//this is the decimal number 92307187545132761346.
//this data structure is considerably quicker than a 3d array and face comparisons are much easier
//the built in rotate left and right for binary numbers also makes rotation easier without haveing to worry about transformation matrices.
public class RubiksCube {

    private Face[] cube = new Face[6];
    private Face[] centres = new Face[6];
    private final int red = 0;
    private final int green = 1;
    private final int blue = 2;
    private final int yellow = 3;
    private final int orange = 4;
    private final int white = 5;
    private char[] directions = {'F', 'R', 'U','B', 'L', 'D'};

    //front , right, top, back, left, bottom

    public RubiksCube(Face[] cube, Face[] centres){
        for(int i = 0; i < cube.length; i++){
            this.cube[i] = new Face(0);
            this.cube[i].setFace(cube[i].getWholeFace());
        }
        for(int a = 0; a < centres.length; a++){
            this.centres[a] = new Face(0);
            this.centres[a].setFace(centres[a].getWholeFace());
        }
    }

    public RubiksCube(){
        this.cube[0] = generateFace(blue);
        this.cube[1] = generateFace(green);
        this.cube[2] = generateFace(white);
        this.cube[3] = generateFace(orange);
        this.cube[4] = generateFace(red);
        this.cube[5] = generateFace(yellow);
        this.centres[0] = new Face(blue);
        this.centres[1] = new Face(green);
        this.centres[2] = new Face(white);
        this.centres[3] = new Face(orange);
        this.centres[4] = new Face(red);
        this.centres[5] = new Face(yellow);
    }

    private Face generateFace(int sticker){
        String binary = "";
        for(int x = 0; x < 8; x++){
            binary += toBinary(sticker, 8);
        }
        return new Face(Long.parseLong(binary, 2));
    }

    //front , right, top, back, left, bottom

    public void rotate(String rot){
        switch (rot) {
            case "L'":
                cube[4].setFace(Long.rotateRight(this.cube[4].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{0, 0, 56, 48}, {5, 0, 56, 48}, {3, 32, 24, 16}, {2, 0, 56, 48}},0);
                break;
            case "L":
                cube[4].setFace(Long.rotateLeft(this.cube[4].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{0, 0, 56, 48}, {5, 0, 56, 48}, {3, 32, 24, 16}, {2, 0, 56, 48}},1);
                break;
            case "L2":
                cube[4].setFace(Long.rotateLeft(this.cube[4].getWholeFace(), 32));
                updateDisplacedFaces(new int[][] {{0, 0, 56, 48}, {5, 0, 56, 48}, {3, 32, 24, 16}, {2, 0, 56, 48}},1);
                updateDisplacedFaces(new int[][] {{0, 0, 56, 48}, {5, 0, 56, 48}, {3, 32, 24, 16}, {2, 0, 56, 48}},1);
                break;
            case "M'":
               rotateCentres(new int[][] {{0, 8, -8, 40}, {5, 8, -8, 40}, {3, 40, -8, 8}, {2, 8, -8, 40}}, 0);
                break;
            case "M":
                rotateCentres(new int[][] {{0, 8, -8, 40}, {5, 8, -8, 40}, {3, 40, -8, 8}, {2, 8, -8, 40}}, 1);
                break;
            case "R'":
                cube[1].setFace(Long.rotateLeft(this.cube[1].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{0, 16, 24, 32}, {5, 16, 24, 32}, {3, 48, 56, 0}, {2, 16, 24, 32}},0);
                break;
            case "R":
                cube[1].setFace(Long.rotateRight(this.cube[1].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{0, 16, 24, 32}, {5, 16, 24, 32}, {3, 48, 56, 0}, {2, 16, 24, 32}},1);
                break;
            case "R2":
                cube[1].setFace(Long.rotateRight(this.cube[1].getWholeFace(), 32));
                updateDisplacedFaces(new int[][] {{0, 16, 24, 32}, {5, 16, 24, 32}, {3, 48, 56, 0}, {2, 16, 24, 32}},1);
                updateDisplacedFaces(new int[][] {{0, 16, 24, 32}, {5, 16, 24, 32}, {3, 48, 56, 0}, {2, 16, 24, 32}},1);
                break;
            case "U'":
                cube[2].setFace(Long.rotateLeft(this.cube[2].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{0, 0, 8, 16}, {1, 0, 8, 16}, {3, 0, 8, 16}, {4, 0, 8, 16}},0);
                break;
            case "U":
                cube[2].setFace(Long.rotateRight(this.cube[2].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{0, 0, 8, 16}, {1, 0, 8, 16}, {3, 0, 8, 16}, {4, 0, 8, 16}},1);
                break;
            case "U2":
                cube[2].setFace(Long.rotateRight(this.cube[2].getWholeFace(), 32));
                updateDisplacedFaces(new int[][] {{0, 0, 8, 16}, {1, 0, 8, 16}, {3, 0, 8, 16}, {4, 0, 8, 16}},1);
                updateDisplacedFaces(new int[][] {{0, 0, 8, 16}, {1, 0, 8, 16}, {3, 0, 8, 16}, {4, 0, 8, 16}},1);
                break;
            case "E'":
                rotateCentres(new int[][] {{0, 56, -8, 24}, {1, 56, -8, 24}, {3, 56, -8, 24}, {4, 56, -8, 24}}, 0);
                break;
            case "E":
                rotateCentres(new int[][] {{0, 56, -8, 24}, {1, 56, -8, 24}, {3, 56, -8, 24}, {4, 56, -8, 24}}, 1);
                break;
            case "E2":
                rotateCentres(new int[][] {{0, 56, -8, 24}, {1, 56, -8, 24}, {3, 56, -8, 24}, {4, 56, -8, 24}}, 0);
                rotateCentres(new int[][] {{0, 56, -8, 24}, {1, 56, -8, 24}, {3, 56, -8, 24}, {4, 56, -8, 24}}, 0);
                break;
            case "D'":
                cube[5].setFace(Long.rotateRight(this.cube[5].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{0, 48, 40, 32}, {1, 48, 40, 32}, {3, 48, 40, 32}, {4, 48, 40, 32}},0);
                break;
            case "D":
                cube[5].setFace(Long.rotateLeft(this.cube[5].getWholeFace(), 16));
                updateDisplacedFaces(new int[][]{{0, 48, 40, 32}, {1, 48, 40, 32}, {3, 48, 40, 32}, {4, 48, 40, 32}},1);
                break;
            case "D2":
                cube[5].setFace(Long.rotateLeft(this.cube[5].getWholeFace(), 32));
                updateDisplacedFaces(new int[][]{{0, 48, 40, 32}, {1, 48, 40, 32}, {3, 48, 40, 32}, {4, 48, 40, 32}},1);
                updateDisplacedFaces(new int[][]{{0, 48, 40, 32}, {1, 48, 40, 32}, {3, 48, 40, 32}, {4, 48, 40, 32}},1);
                break;
            case "F'":
                cube[0].setFace(Long.rotateRight(this.cube[0].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{2, 48, 40, 32}, {1, 0, 56, 48}, {5, 16, 8, 0}, {4, 32, 24, 16}},0);
                break;
            case "F":
                cube[0].setFace(Long.rotateLeft(this.cube[0].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{2, 48, 40, 32}, {1, 0, 56, 48}, {5, 16, 8, 0}, {4, 32, 24, 16}},1);
                break;
            case "F2":
                cube[0].setFace(Long.rotateLeft(this.cube[0].getWholeFace(), 32));
                updateDisplacedFaces(new int[][] {{2, 48, 40, 32}, {1, 0, 56, 48}, {5, 16, 8, 0}, {4, 32, 24, 16}},1);
                updateDisplacedFaces(new int[][] {{2, 48, 40, 32}, {1, 0, 56, 48}, {5, 16, 8, 0}, {4, 32, 24, 16}},1);
                break;
            case "S'":
                rotateCentres(new int[][] {{2, 56, -8, 24}, {1, 8, -8, 40}, {5, 24, -8, 56}, {4, 40, -8, 8}}, 0);
                break;
            case "S":
                rotateCentres(new int[][] {{2, 56, -8, 24}, {1, 8, -8, 40}, {5, 24, -8, 56}, {4, 40, -8, 8}}, 1);
                break;
            case "B'":
                cube[3].setFace(Long.rotateLeft(this.cube[3].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{2, 0, 8, 16}, {1, 16, 24, 32}, {5, 32, 40, 48}, {4, 48, 56, 0}},0);
                break;
            case "B":
                cube[3].setFace(Long.rotateRight(this.cube[3].getWholeFace(), 16));
                updateDisplacedFaces(new int[][] {{2, 0, 8, 16}, {1, 16, 24, 32}, {5, 32, 40, 48}, {4, 48, 56, 0}},1);
                break;
            case "B2":
                cube[3].setFace(Long.rotateRight(this.cube[3].getWholeFace(), 32));
                updateDisplacedFaces(new int[][] {{2, 0, 8, 16}, {1, 16, 24, 32}, {5, 32, 40, 48}, {4, 48, 56, 0}},1);
                updateDisplacedFaces(new int[][] {{2, 0, 8, 16}, {1, 16, 24, 32}, {5, 32, 40, 48}, {4, 48, 56, 0}},1);
                break;
        }

    }

    public void rotateMultipleMoves(String rotations){
        String[] rots = rotations.split(" ");
        for(int i = 0; i < rots.length; i++){
            rotate(rots[i]);
        }
    }

    public Cubie[][][] convertToCubeArray(){
        Cubie[][][] cubeArray = new Cubie[3][3][3];
        int[] indexes = {0, 8, 16, 56, 24, 48, 40, 32};
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        int e = 0;
        int g = 0;
        for(int z = 0; z < cubeArray.length; z++){
            for(int y = 0; y < cubeArray.length; y++){
                for(int x = 0; x < cubeArray.length; x++){
                    Face[] f =  new Face[]{new Face(6),new Face(6),new Face(6),new Face(6),new Face(6),new Face(6)};
                    Point3D position = new Point3D((x-1)*100, (y-1)*100, (z-1)*100);
                    cubeArray[z][y][x] = new Cubie(position, f);
                }
            }
        }
        //front
        for(int y = 0; y < cubeArray.length; y++){
            for(int x = 0; x < cubeArray.length; x++){
                String bin = toBinary(this.cube[0].getWholeFace(), 64);
                Face[] f = cubeArray[0][y][x].getFaces();
                if(x == 1 && y == 1){
                    f[0] = centres[0];
                }else{
                    f[0] = new Face(Long.parseLong(bin.substring(indexes[a], indexes[a]+8), 2));
                    a += 1;
                }
                cubeArray[0][y][x].setFaces(f);
            }
        }
        //back
        for(int y = 0; y <cubeArray.length; y++){
            for(int x = 2; x >= 0; x--){
                String bin = toBinary(this.cube[3].getWholeFace(), 64);
                Face[] f = cubeArray[2][y][x].getFaces();
                if(x == 1 && y == 1){
                    f[3] = centres[3];;
                }else {
                    f[3] = new Face(Long.parseLong(bin.substring(indexes[b], indexes[b]+8), 2));
                    b += 1;
                }
                cubeArray[2][y][x].setFaces(f);
            }
        }
        //left
        for(int y = 0; y < cubeArray.length; y++){
            for(int z = 2; z >= 0; z--){
                String bin = toBinary(this.cube[4].getWholeFace(), 64);
                Face[] f = cubeArray[z][y][0].getFaces();
                if(z == 1 && y == 1){
                    f[4] = centres[4];
                }else{
                    f[4] = new Face(Long.parseLong(bin.substring(indexes[c], indexes[c]+8), 2));
                    c += 1;
                }
                cubeArray[z][y][0].setFaces(f);
            }
        }
        //Right
        for(int y = 0; y < cubeArray.length; y++){
            for(int z = 0; z < cubeArray.length; z++){
                String bin = toBinary(this.cube[1].getWholeFace(), 64);
                Face[] f = cubeArray[z][y][2].getFaces();
                if(z == 1 && y == 1){
                    f[1] = centres[1];
                }else{
                    f[1] = new Face(Long.parseLong(bin.substring(indexes[d], indexes[d]+8), 2));
                    d += 1;
                }
                cubeArray[z][y][2].setFaces(f);
            }
        }
        //Top
        for(int z = 2; z >= 0; z--){
            for(int x = 0; x < cubeArray.length; x++){
                String bin = toBinary(this.cube[2].getWholeFace(), 64);
                Face[] f = cubeArray[z][0][x].getFaces();
                if(z == 1 && x == 1){
                    f[2] = centres[2];
                }else{
                    f[2] = new Face(Long.parseLong(bin.substring(indexes[e], indexes[e]+8), 2));
                    e += 1;
                }
                cubeArray[z][0][x].setFaces(f);
            }
        }
        //Bottom
        for(int z = 0; z < cubeArray.length; z++){
            for(int x = 0; x < cubeArray.length; x++){
                String bin = toBinary(this.cube[5].getWholeFace(), 64);
                Face[] f = cubeArray[z][2][x].getFaces();
                if(z == 1 && x == 1){
                    f[5] = centres[5];
                }else{
                    f[5] = new Face(Long.parseLong(bin.substring(indexes[g], indexes[g]+8), 2));
                    g += 1;
                }
                cubeArray[z][2][x].setFaces(f);
            }
        }
        return cubeArray;
    }


    public void printRubiksCube(){
        for(int i = 0; i < this.cube.length; i++){
            String s = toBinary(this.cube[i].getWholeFace(), 64);
            System.out.println();
            System.out.println();
            String[] colors = {"R", "G", "B", "Y", "O", "W"};
            int[] indexes = {0, 8, 16, 56, 24, 48, 40, 32};
            for(int a = 0; a < indexes.length; a++){
                int n = Integer.parseInt(s.substring(indexes[a], indexes[a]+8), 2);
                System.out.print(" " + colors[n] + " ");
                if(a == 3){
                    System.out.print(" " + colors[centres[i].getFace()] + " ");
                }
                if(a == 2 || a == 4 || a == 7){
                    System.out.println();
                }

            }
            System.out.println();
        }
    }


    private void rotateCentres(int[][] displacedFaces, int direction){
        String affectedStickers = "";
        for(int i = 0; i < displacedFaces.length; i++){
            String bin = toBinary(this.cube[displacedFaces[i][0]].getWholeFace(), 64);
            for(int a = 1; a < displacedFaces[i].length; a++){
                if(a == 2){
                    affectedStickers += toBinary(this.centres[displacedFaces[i][0]].getWholeFace(), 8);
                }else {
                    affectedStickers += bin.substring(displacedFaces[i][a], displacedFaces[i][a] + 8);
                }

            }
        }
        int n;
        if(direction == 1) {
            n = 24;
        }else{
            n = affectedStickers.length() - (24);
        }
        for(int i = 0; i < displacedFaces.length; i++){
            String bin =  toBinary(this.cube[displacedFaces[i][0]].getWholeFace(), 64);
            String s = "";
            this.centres[displacedFaces[i][0]].setFace(Long.parseLong(affectedStickers.substring(n+8, n+16), 2));
            for(int a = 0; a < bin.length(); a += 8){
                if(a == displacedFaces[i][1]) {
                    s += affectedStickers.substring(n, n + 8);
                }else if(a == displacedFaces[i][3]) {
                    s += affectedStickers.substring(n+16, n+24);

                }else{ s += bin.substring(a, a + 8); }
            }
            n = (n+24)%affectedStickers.length();

            this.cube[displacedFaces[i][0]].setFace(Long.parseLong(s, 2));
        }

    }

    private void updateDisplacedFaces(int[][] displacedFaces, int direction){
        String affectedStickers = "";
        for(int i = 0; i < displacedFaces.length; i++){
            String bin = toBinary(this.cube[displacedFaces[i][0]].getWholeFace(), 64);
            for(int a = 1; a < displacedFaces[i].length; a++){
                affectedStickers += bin.substring(displacedFaces[i][a], displacedFaces[i][a]+8);
            }
        }
        int n;
        if(direction == 1) {
            n = 24;
        }else{
            n = affectedStickers.length() - (24);
        }
        for(int i = 0; i < displacedFaces.length; i++){
            String bin =  toBinary(this.cube[displacedFaces[i][0]].getWholeFace(), 64);
            String s = "";
            for(int a = 0; a < bin.length(); a += 8){
                if(a == displacedFaces[i][1]){
                    s += affectedStickers.substring(n, n+8);

                }else if(a == displacedFaces[i][2]) {
                    s += affectedStickers.substring(n+8, n+16);
                }else if(a == displacedFaces[i][3]) {
                s += affectedStickers.substring(n+16, n+24);
                }else{ s += bin.substring(a, a + 8); }
            }
            n = (n+24)%affectedStickers.length();

           this.cube[displacedFaces[i][0]].setFace(Long.parseLong(s, 2));
        }

    }


    public String toBinary(long x, int len){
        if (len > 0) {
            return String.format("%" + len + "s",
                    Long.toBinaryString(x)).replaceAll(" ", "0");
        }

        return null;
    }

    private Cubie getCubie(int face, int index){
        int z = 0;
        int x = 0;
        int y = 0;
        Cubie[][][] array = this.convertToCubeArray();
        //front
        if(face == 0){
            z = 0;
            if(index == 0 || index == 1 || index == 2){y = 0;
            }else if(index == 3|| index == 7){y = 1;
            }else if(index == 4 || index == 5 || index == 6){ y = 2;}

            if(index == 0 || index == 7 || index == 6){x = 0;
            }else if(index == 1 || index == 5){x = 1;
            }else if(index == 2 || index == 3 || index == 4){ x = 2;}
        }
        //right
        if(face == 1){
            x = 2;
            if(index == 0 || index == 1 || index == 2){y = 0;
            }else if(index == 3|| index == 7){y = 1;
            }else if(index == 4 || index == 5 || index == 6){ y = 2;}

            if(index == 0 || index == 7 || index == 6){z = 0;
            }else if(index == 1 || index == 5){z = 1;
            }else if(index == 2 || index == 3 || index == 4){ z = 2;}
        }
        //top
        if(face == 2){
            y = 0;
            if(index == 0 || index == 1 || index == 2){z = 2;
            }else if(index == 3|| index == 7){z = 1;
            }else if(index == 4 || index == 5 || index == 6){ z = 0;}

            if(index == 0 || index == 7 || index == 6){x = 0;
            }else if(index == 1 || index == 5){x = 1;
            }else if(index == 2 || index == 3 || index == 4){ x = 2;}
        }
        //back
        if(face == 3){
            z = 2;
            if(index == 0 || index == 1 || index == 2){y = 0;
            }else if(index == 3|| index == 7){y = 1;
            }else if(index == 4 || index == 5 || index == 6){ y = 2;}

            if(index == 0 || index == 7 || index == 6){x = 2;
            }else if(index == 1 || index == 5){x = 1;
            }else if(index == 2 || index == 3 || index == 4){ x = 0;}
        }
        //left
        if(face == 4){
            x = 0;
            if(index == 0 || index == 1 || index == 2){y = 0;
            }else if(index == 3|| index == 7){y = 1;
            }else if(index == 4 || index == 5 || index == 6){ y = 2;}

            if(index == 0 || index == 7 || index == 6){z = 2;
            }else if(index == 1 || index == 5){z = 1;
            }else if(index == 2 || index == 3 || index == 4){ z = 0;}
        }
        //bottom
        if(face == 5){
            y = 2;
            if(index == 0 || index == 1 || index == 2){z = 0;
            }else if(index == 3|| index == 7){z = 1;
            }else if(index == 4 || index == 5 || index == 6){ z = 2;}

            if(index == 0 || index == 7 || index == 6){x = 0;
            }else if(index == 1 || index == 5){x = 1;
            }else if(index == 2 || index == 3 || index == 4){ x = 2;}
        }

        return array[z][y][x];
    }

    public char getDirectionOfColor(int color, int face, int index){
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() == color){
                return directions[i];
            }
        }
        return 'A';
    }

    public int getNonYellowColor(int face, int index){
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() != 6 && faces[i].getFace() != yellow){
                return faces[i].getFace();
            }
        }
        return 6;
    }

    public int getColorOfDirection(char direction, int face, int index){
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < directions.length; i++){
            if(directions[i] == direction){
                return faces[i].getFace();
            }
        }
        return 6;
    }

    public boolean isCornerCubie(int face, int index){
        int count = 0;
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() != 6){
                count++;
            }
        }
        return count == 3;
    }

    public boolean isWhiteCorner(int face, int index){
        int count = 0;
        boolean hasWhiteSticker = false;
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() != 6){
                count++;
            }
            if(faces[i].getFace() == white){
                hasWhiteSticker = true;
            }
        }
        return count == 3 && hasWhiteSticker;
    }

    public boolean isYellowCorner(int face, int index){
        int count = 0;
        boolean hasWhiteSticker = false;
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() != 6){
                count++;
            }
            if(faces[i].getFace() == yellow){
                hasWhiteSticker = true;
            }
        }
        return count == 3 && hasWhiteSticker;
    }


    public boolean isWhiteEdge(int face, int index){
        int count = 0;
        boolean hasWhiteSticker = false;
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() != 6){
                count++;
            }
            if(faces[i].getFace() == white){
                hasWhiteSticker = true;
            }
        }
        return count == 2 && hasWhiteSticker;
    }

    public boolean isYellowEdge(int face, int index){
        int count = 0;
        boolean hasYellowSticker = false;
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() != 6){
                count++;
            }
            if(faces[i].getFace() == yellow){
                hasYellowSticker = true;
            }
        }
        return count == 2 && hasYellowSticker;
    }

    public boolean isEdgeCubie(int face, int index){
        int count = 0;
        Face[] faces = getCubie(face, index).getFaces();
        for(int i = 0; i < faces.length; i++){
            if(faces[i].getFace() != 6){
                count++;
            }
        }
        return count == 2;
    }
    //for any edge piece in U or D find whether its in F, B, L or R.
    public char getVerticalFace(int face, int index) {
        Cubie c = getCubie(face, index);
        if (isEdgeCubie(face, index)) {
            if (c.getPositionX() == 0) {
                return 'L';
            } else if (c.getPositionX() == 1) {
                if (c.getPositionZ() == 0) {
                    return 'F';
                } else {
                    return 'B';
                }
            } else {
                return 'R';
            }
        }
        return 'A';
    }

    public Face[] getCube(){
        return this.cube;
    }

    public Face[] getCentres(){
        return this.centres;
    }
}
