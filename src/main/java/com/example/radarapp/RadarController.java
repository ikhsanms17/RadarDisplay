package com.example.radarapp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RadarController {

    private static final double RADAR_CENTER_X = 300.0;
    private static final double RADAR_CENTER_Y = 250.0;
    private static final double RADAR_BEAM_LENGTH = 180.0;
    private static final double RADAR_BEAM_ANGLE_STEP = 5.0;

    private double beamAngle = 0.0;

    @FXML
    private Button handleStartButton;

    @FXML
    private Button handleStopButton;

    @FXML
    private Label statusLabel;

    @FXML
    private Circle radarCircle;

    @FXML
    private Line radarBeam;

    @FXML
    private ActionEvent actionEvent;

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.05), event -> {
                    beamAngle += RADAR_BEAM_ANGLE_STEP;
                    if (beamAngle >= 360.0) {
                        beamAngle = 0.0;
                    }
                    double endX = RADAR_CENTER_X + Math.cos(Math.toRadians(beamAngle)) * RADAR_BEAM_LENGTH;
                    double endY = RADAR_CENTER_Y + Math.sin(Math.toRadians(beamAngle)) * RADAR_BEAM_LENGTH;
                    radarBeam.setStartX(RADAR_CENTER_X);
                    radarBeam.setStartY(RADAR_CENTER_Y);
                    radarBeam.setEndX(endX);
                    radarBeam.setEndY(endY);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Metode yang dipanggil saat tombol "Start" diklik
    public void handleStartButton(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
        // Logika untuk memulai radar
        statusLabel.setText("Radar aktif");
    }

    // Metode yang dipanggil saat tombol "Stop" diklik
    public void handleStopButton(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
        // Logika untuk menghentikan radar
        statusLabel.setText("Radar tidak aktif");
    }
}

