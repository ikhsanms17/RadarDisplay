package com.example.radarapp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import static javafx.fxml.FXMLLoader.load;

public class RadarApp extends Application {

    private static final double RADAR_BEAM_LENGTH = 250.0;
    private static final double RADAR_BEAM_ANGLE_STEP = 5.0;

    private double beamAngle = 0.0;

    private static final int WIDTH = 600;   // Width of the radar display
    private static final int HEIGHT = 600;  // Height of the radar display

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RadarView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Radar Pendeteksi Benda");
        primaryStage.show();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create radar circle
        Circle radarCircle = new Circle(WIDTH / 2, HEIGHT / 2, WIDTH / 2 - 50);
        radarCircle.setStroke(Color.GREEN);
        radarCircle.setStrokeWidth(2.0);
        radarCircle.setFill(null);
        root.getChildrenUnmodifiable().add(radarCircle);

        // Create radar lines
        for (int angle = 0; angle < 360; angle += 4) {
            double startX = WIDTH / 2;
            double startY = HEIGHT / 2;
            double endX = WIDTH / 2 + (WIDTH / 2 - 50) * Math.sin(Math.toRadians(angle));
            double endY = HEIGHT / 2 + (HEIGHT / 2 - 50) * Math.cos(Math.toRadians(angle));

            Line radarLine = new Line(startX, startY, endX, endY);
            radarLine.setStroke(Color.GREEN);
            radarLine.setStrokeWidth(1);

            root.getChildrenUnmodifiable().add(radarLine);
        }

        Line radarBeam = new Line();
        radarBeam.setStroke(Color.GREEN);
        radarBeam.setStrokeWidth(2.0);
        root.getChildrenUnmodifiable().add(radarBeam);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), event -> {
                    beamAngle += RADAR_BEAM_ANGLE_STEP;
                    if (beamAngle >= 360.0) {
                        beamAngle = 0.0;
                    }
                    double endX = WIDTH / 2 + Math.cos(Math.toRadians(beamAngle)) * RADAR_BEAM_LENGTH;
                    double endY = HEIGHT / 2 + Math.sin(Math.toRadians(beamAngle)) * RADAR_BEAM_LENGTH;
                    radarBeam.setStartX(WIDTH / 2);
                    radarBeam.setStartY(HEIGHT / 2);
                    radarBeam.setEndX(endX);
                    radarBeam.setEndY(endY);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}