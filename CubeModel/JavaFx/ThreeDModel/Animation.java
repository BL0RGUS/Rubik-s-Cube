package JavaFx.ThreeDModel;

import JavaFx.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

//this class is used to animate the cubes rotations, for example if it was given a scramble then this class
//would take all the rotation animations individually and add them to one big animation

public class Animation{

    private Timeline timeline;
    private String[] moves;

    public Animation(String[] moves, int speed){
        this.timeline = new Timeline();
        this.moves = moves;
        for (int i = 0; i < moves.length; i++) {
            int finalI = i;
            this.timeline.getKeyFrames().add(new KeyFrame(Duration.millis((speed + 150)*(i+1)),
                    e ->  Main.getR().rotate(moves[finalI].strip(), speed)));
        }
        timeline.play();
    }

    public void addKeyFrame(KeyFrame k){
        this.timeline.getKeyFrames().add(k);
    }

    public void play(){
        this.timeline.play();
    }
}
