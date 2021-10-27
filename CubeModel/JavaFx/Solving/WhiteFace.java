package JavaFx.Solving;

import JavaFx.CubeModel.RubiksCube;

//this class uses the sunflower created in step 1 in order to make the white face, first it makes the white cross, then inserts the corners.

public class WhiteFace extends Solver {



    public WhiteFace(RubiksCube rubiksCube) {
        super(rubiksCube);
    }

    public String makeWhiteFace(){
        String moves = "";
        moves += makeYellowCross();
        moves += insertCornersInU();
        moves += insertMisorientedCorners();
        while(yellowCornerInU()){
            moves += insertCornersInU();
            moves += insertMisorientedCorners();
        }
        return moves;
    }

    private String makeYellowCross(){
        String moves = "";
        while(numYellowEdgesOriented() != 0){
            for(int i = 0; i < this.edgeIndices.length; i++){
                if(this.solvingCube.isYellowEdge(2, this.edgeIndices[i])){
                    int color = this.solvingCube.getNonYellowColor(2, this.edgeIndices[i]);
                    char vert = this.solvingCube.getVerticalFace(2, this.edgeIndices[i]);
                    if(color == this.solvingCube.getCentres()[0].getFace() && vert == 'F' || color == this.solvingCube.getCentres()[1].getFace() && vert == 'R' || color == this.solvingCube.getCentres()[3].getFace() && vert == 'B' || color == this.solvingCube.getCentres()[4].getFace() && vert == 'L'){
                        moves += vert + " " + vert + " ";
                        this.solvingCube.rotateMultipleMoves(vert + " " + vert);
                    }
                }
            }
            moves += "U ";
            this.solvingCube.rotate("U");
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

    private String insertMisorientedCorners() {
        String moves = new String();
        for(int i = 0; i<4; i++) {
            moves += "E D ";
            this.solvingCube.rotateMultipleMoves("E D");
            if(!cornerInserted(5, 2)) {
                if(this.solvingCube.isYellowCorner(5, 2)) {
                    if(!cornerInserted(5, 2)) {
                        //Use R U R' U' to get corner to U layer, then insert it in appropriate slot
                        moves+= "R U R' U' ";
                        this.solvingCube.rotateMultipleMoves("R U R' U'");
                        moves+=insertCornersInU();
                    }
                }
            }
        }
        if(moves.equals("E D E D E D E D ")){
            return "";
        }else{
            return moves;
        }
    }


    private boolean cornerInserted(int face, int index){
        return(this.solvingCube.getColorOfDirection('D', face, index) == yellow &&
                this.solvingCube.getColorOfDirection('F', face, index) == this.solvingCube.getCentres()[0].getFace() &&
                this.solvingCube.getColorOfDirection('R', face, index) == this.solvingCube.getCentres()[1].getFace());
    }

    private boolean yellowCornerInU(){
        for(int i = 0; i < this.cornerIndices.length; i++){
            if(this.solvingCube.isYellowCorner(2, this.cornerIndices[i])){
                return true;
            }
        }
        return false;
    }

    private String insertCornersInU(){
        String moves = "";
        for(int i = 0; i < this.cornerIndices.length; i++){
            int index = this.cornerIndices[i];
            if(this.solvingCube.isYellowCorner(2, index)) {
                if (index == 0) {
                    moves += "U U ";
                    this.solvingCube.rotateMultipleMoves("U U ");
                } else if (index == 2) {
                    moves += "U ";
                    this.solvingCube.rotate("U");
                } else if (index == 6) {
                    moves += "U' ";
                    this.solvingCube.rotate("U'");
                }
                int count = 0;

                while (!yellowCornerPrepared(2, 4)) {
                    this.solvingCube.rotateMultipleMoves("E D");
                    count++;
                }

                if (count == 1) {
                    moves += "E D ";
                } else if (count == 2) {
                    moves += "E E D D ";
                } else if (count == 3) {
                    moves += "E' D' ";
                }
                int numOfTries = 0;
                cornerInserted(5, 2);
                while (!(cornerInserted(5, 2))) {
                    this.solvingCube.rotateMultipleMoves("R U R' U'");
                    numOfTries++;
                }
                if(numOfTries == 5) { //5 sexy moves can be condensed into "U R U' R'"
                    moves += "U R U' R' ";
                }
                else {
                    for(int k = 0; k<numOfTries; k++) {
                        moves += "R U R' U' ";
                    }
                }
                i = 0;
            }

        }
        return moves;
    }

    private boolean yellowCornerPrepared(int face, int index){
        char yellowDir = this.solvingCube.getDirectionOfColor(yellow, 2, index);
        if(yellowDir == 'U') {
            return(this.solvingCube.getColorOfDirection('F', face, index) == this.solvingCube.getCentres()[1].getWholeFace()
            && this.solvingCube.getColorOfDirection('R', face, index) == this.solvingCube.getCentres()[0].getWholeFace());
        }else{
            return (this.solvingCube.getColorOfDirection('F', face, index) == this.solvingCube.getCentres()[0].getWholeFace()
                    || this.solvingCube.getColorOfDirection('R', face, index) == this.solvingCube.getCentres()[1].getWholeFace());
        }
    }
}
