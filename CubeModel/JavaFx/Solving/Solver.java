package JavaFx.Solving;

import JavaFx.CubeModel.RubiksCube;

//this is the main solver class which all the other solving classes inherit from
//it has methods and variables that all classes will make use of.

abstract class Solver {


    protected final int red = 0;
    protected final int green = 1;
    protected final int blue = 2;
    protected final int yellow = 3;
    protected final int orange = 4;
    protected final int white = 5;
    protected RubiksCube solvingCube;
    protected String moves = "";
    protected int[] edgeIndices = {1, 3, 5, 7};
    protected int[] cornerIndices = {0, 2, 4, 6};




    public Solver(RubiksCube rubiksCube){
       solvingCube = new RubiksCube(rubiksCube.getCube(), rubiksCube.getCentres());
    }
    public RubiksCube getCube(){
        return this.solvingCube;
    }


    public String optimizeMoves(String moves) {
        for(int i = 0; i<moves.length(); i++) {
            String move = moves.substring(i, i+1);
            if(!move.equals(" ") && !move.equals("'") && !move.equals("2")) { //Only check if there is a meaningful turn/rotation
                if(i <= moves.length()-3) {
                    if(moves.substring(i+1, i+2).compareTo("2") == 0) { //Double turn
                        if(i <= moves.length()-4 && moves.charAt(i+3) == moves.charAt(i)) {
                            if(i <= moves.length()-5) {
                                if(moves.substring(i+4, i+5).compareTo("2") == 0) {
                                    //Ex. "U2 U2" gets negated
                                    moves = moves.substring(0, i) + moves.substring(i+5);
                                    i--;
                                } else if(moves.substring(i+4, i+5).compareTo("'") == 0) {
                                    //Ex. "U2 U'" --> "U"
                                    moves = moves.substring(0, i) + moves.substring(i, i+1)
                                            + moves.substring(i+5);
                                    i--;
                                } else {
                                    //Ex. "U2 U" --> "U'"
                                    moves = moves.substring(0, i) + moves.substring(i, i+1) + "'"
                                            + moves.substring(i+4);
                                    i--;
                                }
                            } else {
                                //Ex. "U2 U" --> "U'"
                                moves = moves.substring(0, i) + moves.substring(i, i+1) + "'"
                                        + moves.substring(i+4);
                                i--;
                            }
                        }
                    }
                    else if(moves.substring(i+1,i+2).compareTo("'") == 0) { //Clockwise turn
                        if(i <= moves.length()-4 && moves.charAt(i+3) == moves.charAt(i)) {
                            if(i <= moves.length()-5) {
                                if(moves.substring(i+4, i+5).compareTo("2") == 0) {
                                    //Ex. "U' U2" --> "U"
                                    moves = moves.substring(0, i) + moves.substring(i, i+1)
                                            + moves.substring(i+5);
                                    i--;
                                } else if(moves.substring(i+4, i+5).compareTo("'") == 0) {
                                    //Ex. "U' U'" --> "U2"
                                    moves = moves.substring(0, i) + moves.substring(i, i+1) + "2"
                                            + moves.substring(i+5);
                                    i--;
                                } else {
                                    //Ex. "U' U" gets negated
                                    moves = moves.substring(0, i) + moves.substring(i+5);
                                    i--;
                                }
                            } else {
                                //Ex. "U' U" gets negated
                                moves = moves.substring(0, i) + moves.substring(i+5);
                                i--;
                            }
                        }
                    }
                    else { //Clockwise turn
                        if(i <= moves.length()-3 && moves.charAt(i+2) == moves.charAt(i)) {
                            if(i <= moves.length()-4) {
                                if(moves.substring(i+3, i+4).compareTo("2") == 0) {
                                    //Ex. "U U2" --> "U' "
                                    moves = moves.substring(0, i) + moves.substring(i, i+1) + "'"
                                            + moves.substring(i+4);
                                    i--;
                                } else if(moves.substring(i+3, i+4).compareTo("'") == 0) {
                                    //Ex. "U U'" gets negated
                                    moves = moves.substring(0, i) + moves.substring(i+5);
                                    i--;
                                } else {
                                    //Ex. "U U" --> "U2"
                                    moves = new String(moves.substring(0, i) + moves.substring(i, i+1) + "2"
                                            + moves.substring(i+3));
                                    i--;
                                }
                            } else {
                                //Ex. "U U" --> "U2"
                                moves = new String(moves.substring(0, i) + moves.substring(i, i+1) + "2"
                                        + moves.substring(i+3));
                                i--;
                            }
                        }

                    }
                }
            }
        }

        return moves;
    }



}
