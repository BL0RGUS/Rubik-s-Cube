package JavaFx.Solving;

import JavaFx.CubeModel.RubiksCube;

//this class is the first stage in the solving process, it creates a yellow cross on the top of the cube.

public class Sunflower extends Solver{


    public Sunflower(RubiksCube rubiksCube) {
        super(rubiksCube);
    }

    //front , right, top, back, left, bottom

    public String makeSunflower(){
        String moves = "";
        if(numYellowEdgesOriented() < 5) {
            for (int index : this.edgeIndices) {
                if (this.solvingCube.getDirectionOfColor(yellow, 5, index) == 'D') {
                    moves += prepareSlot(2, index);
                    char turnToMake = this.solvingCube.getVerticalFace(5, index);
                    moves += turnToMake + " " + turnToMake + " ";
                    this.solvingCube.rotateMultipleMoves(turnToMake + " " + turnToMake);
                } else if (this.solvingCube.isYellowEdge(5, index)) {
                    char vert = this.solvingCube.getVerticalFace(5, index);
                    moves += prepareSlot(2, index);
                    if (vert == 'F') {
                        moves += "F' U L ";
                        this.solvingCube.rotateMultipleMoves("F' U L ");
                    } else if (vert == 'B') {
                        moves += "B' U' L' ";
                        this.solvingCube.rotateMultipleMoves("B' U' L' ");
                    } else if (vert == 'R') {
                        moves += "R U F ";
                        this.solvingCube.rotateMultipleMoves("R U F ");
                    } else if (vert == 'L') {
                        moves += "L U' F' ";
                        this.solvingCube.rotateMultipleMoves("L U' F' ");
                    }
                }
            }
        }

        if(numYellowEdgesOriented() < 5) {
            int[][] edgesInE = {{0, 7}, {0, 3}, {3, 7}, {3, 3}};
            for (int i = 0; i < edgesInE.length; i++) {
                int face = edgesInE[i][0];
                int index = edgesInE[i][1];
                if (this.solvingCube.isYellowEdge(face, index)) {
                    char vert = this.solvingCube.getDirectionOfColor(yellow, face, index);
                    if (face == 0) {
                        if (vert == 'F') {
                            if (index == 7) {
                                moves += prepareSlot(2, 7) + "L ";
                                this.solvingCube.rotate("L");
                            } else if (index == 3) {
                                moves += prepareSlot(2, 3) + "R ";
                                this.solvingCube.rotate("R");
                            }
                        } else if (vert == 'R') {
                            moves += prepareSlot(2, 1) + "F ";
                            this.solvingCube.rotate("F");
                        } else if (vert == 'L') {
                            moves += prepareSlot(2, 1) + "F' ";
                            this.solvingCube.rotate("F'");
                        }
                    } else if (face == 3) {
                        if (vert == 'B') {
                            if (index == 7) {
                                moves += prepareSlot(2, 3) + "R' ";
                                this.solvingCube.rotate("R'");
                            } else if (index == 3) {
                                moves += prepareSlot(2, 7) + "L' ";
                                this.solvingCube.rotate("L'");
                            }
                        } else if (vert == 'R') {
                            moves += prepareSlot(2, 5) + "B ";
                            this.solvingCube.rotate("B");
                        } else if (vert == 'L') {
                            moves += prepareSlot(2, 5) + "B' ";
                            this.solvingCube.rotate("B'");
                        }
                    }
                }
            }
        }
        if(numYellowEdgesOriented() < 5) {
            for (int i = 0; i < this.edgeIndices.length; i++) {
                if (this.solvingCube.isYellowEdge(2, this.edgeIndices[i])) {
                    char vert = this.solvingCube.getDirectionOfColor(yellow, 2, this.edgeIndices[i]);
                    if (vert == 'F') {
                        moves += "F U L ";
                        this.solvingCube.rotateMultipleMoves("F U L ");
                    } else if (vert == 'B') {
                        moves += "B U' L' ";
                        this.solvingCube.rotateMultipleMoves("B U' L' ");
                    } else if (vert == 'R') {
                        moves += "R U' B ";
                        this.solvingCube.rotateMultipleMoves("R U' B ");
                    } else if (vert == 'L') {
                        moves += "L U B' ";
                        this.solvingCube.rotateMultipleMoves("L U B' ");
                    }
                }
            }
        }
        if(numYellowEdgesOriented() < 4){
          moves += makeSunflower();
        }
        return moves;
    }

    private int numYellowEdgesOriented(){
        int count = 0;
        String topFace = this.solvingCube.toBinary(this.solvingCube.getCube()[2].getWholeFace(), 64);
        for(int i = 0; i < this.edgeIndices.length; i++){
            int stickerValue = Integer.parseInt(topFace.substring((this.edgeIndices[i])*8, (this.edgeIndices[i]+1)*8), 2);
            if(stickerValue == yellow){
                count++;
            }
        }
        return count;
    }

    private String prepareSlot(int face, int index){
        if(index == 5){
            index = 1;
        }else if(index == 1) {
            index = 5;
        }
        String moves = "";
        while(this.solvingCube.isYellowEdge(face, index)){
            this.solvingCube.rotate("U");
            moves += "U" + " ";
        }
        return moves;
    }


}
