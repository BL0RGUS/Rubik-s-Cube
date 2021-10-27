package JavaFx.ThreeDModel;

import java.util.Random;

//this class generates a random scramble of length 20, it also checks for repeated and pointless moves
//for example the substring "U U'" would never appear, neither would "U U U U"

public class Scramble {

    private String scramble;

    public Scramble(int speed) throws InterruptedException {
        this.scramble = generateRandomScramble();
        System.out.println(scramble);
        String[] rotations = this.scramble.split(",");
        Animation animation = new Animation(rotations, speed);
    }

    private String generateRandomScramble(){
       String[] moves = {"L", "L'", "R", "R'", "U", "U'", "D", "D'", "F", "F'", "B", "B'"};
       String scramble = "";
       Random random = new Random();
       int length = random.nextInt(10) + 20;
       int lastIndex = -2;
       for(int i = 0; i < length; i++){
           int index = random.nextInt(12);
           if(lastIndex %2 == 0){
                while(index == lastIndex +1){
                    index = random.nextInt(12);
                }
           }else{
               while(index == lastIndex -1){
                   index = random.nextInt(12);
               }
           }

           scramble += moves[index] + ",";
           lastIndex = index;
       }
       return scramble;
    }
}
