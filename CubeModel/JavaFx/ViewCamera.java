package JavaFx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

//this class creates a 3D camera that rotates the cube around so that the user can see the whole cube.
// it uses vectors to rotate the cube around wherever the user clicks in the direction of their mouse movement.

public class ViewCamera extends PerspectiveCamera{

    private final Group group;
    private double anchorX, anchorY;
    private double cameraX = 0;
    private double cameraY = 0;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);


    public ViewCamera(Group group){
        //super(fixedEyeAtCameraZero);
        this.group = group;
    }

    public void initMouseControl(Scene scene, Stage stage) {
        Rotate rotateX;
        Rotate rotateY;
        this.group.getTransforms().addAll(rotateX = new Rotate(30, 0, 0, 0,  Rotate.X_AXIS), rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS));
        rotateY.angleProperty().bind(angleY);
        rotateX.angleProperty().bind(angleX);
        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0,Rotate.Y_AXIS);
        this.getTransforms().addAll (
                pivot,
                yRotate
        );

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
            cameraX = angleX.get();
            cameraY = angleY.get();
        });
        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double movement = event.getDeltaY();
            this.group.translateZProperty().set(this.group.getTranslateZ() - movement);
        });
    }



}
