    package JavaFx.Solving;

    import JavaFx.CubeModel.RubiksCube;
    import java.util.ArrayList;
    import java.util.List;

    //this is the last step in solving the cube, once the edges are inserted it only has to finish the white face on top.

    public class YellowFace extends Solver{


        public YellowFace(RubiksCube rubiksCube) {
            super(rubiksCube);
        }

        public String makeYellowCross(){
            String moves = "";
            String orientation = getOrientationOfEdges();
            if(orientation.equals("Dot")){
                moves += "F' R U R' U' F U U F' U R U' R' F ";
                this.solvingCube.rotateMultipleMoves("F' R U R' U' F U U F' U R U' R' F");
            }else if(orientation.equals("Bar")){
                while(this.solvingCube.getDirectionOfColor(white, 2, 7) != 'U' || this.solvingCube.getDirectionOfColor(white, 2, 3) != 'U'){
                    this.solvingCube.rotate("U");
                    moves += "U ";
                }
                moves += "F' R U R' U' F ";
                this.solvingCube.rotateMultipleMoves( "F' R U R' U' F ");

            }else if(orientation.equals("L")){
                while(this.solvingCube.getDirectionOfColor(white, 2, 7) != 'U' || this.solvingCube.getDirectionOfColor(white, 2, 1) != 'U'){
                    this.solvingCube.rotate("U");
                    moves += "U ";
                }
                moves += "F' U R U' R' F ";
                this.solvingCube.rotateMultipleMoves( "F' U R U' R' F");
            }

            return moves;
        }

        public String orientLastLayer(){
            String moves = "";
            int numOriented = numCornersOriented();
            while(numOriented != 4){
                if(numOriented == 0){
                    while(this.solvingCube.getDirectionOfColor(white, 0, 0) != 'L'){
                        moves += "U ";
                        this.solvingCube.rotate("U");
                    }
                    moves += "R U R' U R U U R' ";
                    this.solvingCube.rotateMultipleMoves("R U R' U R U U R'");
                }else if(numOriented == 1){
                    while(this.solvingCube.getDirectionOfColor(white, 0, 0) != 'U'){
                        moves += "U ";
                        this.solvingCube.rotate("U");
                    }
                    moves += "R U R' U R U U R' ";
                    this.solvingCube.rotateMultipleMoves("R U R' U R U U R' ");
                }else if(numOriented == 2){
                    while(this.solvingCube.getDirectionOfColor(white, 0, 0) != 'F'){
                        moves += "U ";
                        this.solvingCube.rotate("U");
                    }
                    moves += "R U R' U R U U R' ";
                    this.solvingCube.rotateMultipleMoves("R U R' U R U U R' ");
                }
                numOriented = numCornersOriented();
            }
            return moves;
        }


        public String permuteLastLayer(){
            String moves = "";
            int headlights = numOfHeadlights();
            if(headlights == 0){
                this.solvingCube.rotateMultipleMoves("R' F' R' B' B' R F R' B' B' R R");
                moves += "R' F' R' B' B' R F R' B' B' R R ";
                headlights = 1;
            }
            if(headlights == 1){
                while(this.solvingCube.getColorOfDirection('B', 3, 0) != this.solvingCube.getColorOfDirection('B', 3, 2)){
                    moves += "U ";
                    this.solvingCube.rotate("U");
                }
                moves +="R' F' R' B' B' R F R' B' B' R R ";
                this.solvingCube.rotateMultipleMoves("R' F' R' B' B' R F R' B' B' R R");
            }

            int numEdgesSolved = 0;
            for(int i = 0; i < 4; i++){
                this.solvingCube.rotateMultipleMoves("U E");
                if(this.solvingCube.getColorOfDirection('F', 0, 0) == this.solvingCube.getColorOfDirection('F', 0, 1)){
                    numEdgesSolved++;
                }
            }
            if(numEdgesSolved == 0){
                moves += "R R U R U R' U' R' U' R' U R' ";
                this.solvingCube.rotateMultipleMoves("R R U R U R' U' R' U' R' U R'");
                numEdgesSolved = 1;
            }
            if(numEdgesSolved == 1){
                while(this.solvingCube.getColorOfDirection('B', 3, 2) != this.solvingCube.getColorOfDirection('B', 3, 1)){
                    moves += "U ";
                    this.solvingCube.rotate("U");
                }
                if(this.solvingCube.getColorOfDirection('F', 0, 1) == this.solvingCube.getColorOfDirection('L', 0, 0)){
                    moves += "R R U R U R' U' R' U' R' U R' ";
                    this.solvingCube.rotateMultipleMoves("R R U R U R' U' R' U' R' U R'");
                }else{
                    moves += "R U' R U R U R U' R' U' R R ";
                    this.solvingCube.rotateMultipleMoves("R U' R U R U R U' R' U' R R");
                }
            }
            while(this.solvingCube.getColorOfDirection('F', 0, 1) != this.solvingCube.getCentres()[0].getFace()){
                this.solvingCube.rotate("U");
                moves += "U ";
            }
            return moves;
        }

        private int numOfHeadlights(){
            int count = 0;
            for(int i = 0; i < 4; i++){
                this.solvingCube.rotate("U");
                if(this.solvingCube.getColorOfDirection('F', 0, 0) == this.solvingCube.getColorOfDirection('F', 0, 2)){
                    count++;
                }
            }
            return count;
        }

        private int numCornersOriented(){
            int count = 0;
            for(int i = 0; i < this.cornerIndices.length; i++){
                int index = this.cornerIndices[i];
                if(this.solvingCube.getDirectionOfColor(white, 2, index) == 'U'){
                    count++;
                }
            }
            return count;
        }

        private String getOrientationOfEdges(){
            String orientation = "";
            List<Integer> edges = new ArrayList<Integer>();
            for(int i = 0; i < this.edgeIndices.length; i++){
                int index = this.edgeIndices[i];
                if(this.solvingCube.getDirectionOfColor(white, 2, index) == 'U'){
                    edges.add(index);
                }
            }

            if(edges.size() == 4){
                return "Cross";
            }else if(edges.size() == 0){
                return "Dot";
            }else {
                if(edges.get(1) - edges.get(0) == 4){
                    return "Bar";
                }else{
                    return "L";
                }
            }
        }

    }
