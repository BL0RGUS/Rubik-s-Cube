package JavaFx.UI;

import javafx.scene.Group;

public class SmartGroup extends Group{



    public void centre(int width, int height){
        this.translateXProperty().set(width / 2);
        this.translateYProperty().set(height / 2);
    }

}
