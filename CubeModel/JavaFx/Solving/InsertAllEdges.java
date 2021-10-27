package JavaFx.Solving;

import JavaFx.CubeModel.RubiksCube;

//this class is apart of the solving process, it inserts the edges into the appropriate positions in the middle layer.

public class InsertAllEdges extends Solver{


    public InsertAllEdges(RubiksCube rubiksCube) {
        super(rubiksCube);
    }

    public String insertEdges(){
        String moves = "";
        moves += insertEdgesInU();
        moves+=insertMisorientedEdges();
        while(nonWhiteEdgesInU()){
           moves += insertEdgesInU();
           moves+=insertMisorientedEdges();
        }
        return moves;
    }

    private String insertMisorientedEdges() {
        String moves = new String();
        for(int i = 0; i<4; i++) {
            moves += "E D ";
            this.solvingCube.rotateMultipleMoves("E D");
            if(this.solvingCube.getDirectionOfColor(white, 0, 3) == 'A' &&
                    this.solvingCube.getColorOfDirection('F', 0, 3) != this.solvingCube.getCentres()[0].getFace()) {
                    if(this.solvingCube.getColorOfDirection('F', 0, 3) == this.solvingCube.getCentres()[1].getFace() &&
                            this.solvingCube.getColorOfDirection('R', 0, 3) == this.solvingCube.getCentres()[0].getFace()) {
                        moves += "R U R' U U R U U R' U F U' F' ";
                        this.solvingCube.rotateMultipleMoves("R U R' U U R U U R' U F U' F'");
                    }else{
                        moves += "R U R' U' F U' F' ";
                        this.solvingCube.rotateMultipleMoves("R U R' U' F U' F'");
                        moves += insertEdgesInU();
                    }
            }
        }
        if(moves.equals("E D E D E D E D ")){
            return "";
        }else{
            return moves;
        }
    }


    private String insertEdgesInU(){
        String moves = "";
        for(int i = 0; i < this.edgeIndices.length; i++){
            int index = this.edgeIndices[i];
            if(!this.solvingCube.isWhiteEdge(2, index)) {
                if (index == 1) {
                    moves += "U U ";
                    this.solvingCube.rotateMultipleMoves("U U ");
                } else if (index == 3) {
                    moves += "U ";
                    this.solvingCube.rotate("U");
                } else if (index == 7) {
                    moves += "U' ";
                    this.solvingCube.rotate("U'");
                }
                int count = 0;

                while(this.solvingCube.getColorOfDirection('F', 2, 5) != this.solvingCube.getCentres()[0].getFace()) {
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
                if(this.solvingCube.getColorOfDirection('U', 2, 5) == this.solvingCube.getCentres()[4].getFace()){
                    moves += "U' L U L' U F' U' F ";
                    this.solvingCube.rotateMultipleMoves("U' L U L' U F' U' F");
                }else if(this.solvingCube.getColorOfDirection('U', 2, 5) == this.solvingCube.getCentres()[1].getFace()){
                    moves += "U R U' R' U' F U F' ";
                    this.solvingCube.rotateMultipleMoves("U R U' R' U' F U F'");
                }
            }

        }
        return moves;
    }

    private boolean nonWhiteEdgesInU(){
        for(int i = 0; i < this.edgeIndices.length; i++){
            if(this.solvingCube.getDirectionOfColor(white, 2, this.edgeIndices[i]) == 'A'){
                return true;
            }
        }
        return false;
    }
}
