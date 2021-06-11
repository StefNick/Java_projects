package Lab6;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Rotate_Transition extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        //crearea dreptunghiului
        Rectangle rect = new Rectangle(200,100,100,200); //pozitia dreptunghiului si dimensiunile
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(3);
        RotateTransition rotate = new RotateTransition(); //Initierea clasei RotateTransition
        rotate.setAxis(Rotate.Z_AXIS); //setarea axei de rotatie
        rotate.setByAngle(360);  // setarea unghiului de rotatie
        rotate.setCycleCount(100);  //setarea contorului ciclului de rotatie
        rotate.setDuration(Duration.millis(1000));  //Setting duration of the transition
        rotate.setAutoReverse(false); //transitia va fi inversata daca parametrul este true
        rotate.setNode(rect);  //setarea dreptunghiului ca nod pentru care va fi aplicata transitia
        rotate.play(); //rularea transitiei

        //Configurarea grupei si scenei
        Group root = new Group();
        root.getChildren().add(rect);
        Scene scene = new Scene(root,500,400,Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 6");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
