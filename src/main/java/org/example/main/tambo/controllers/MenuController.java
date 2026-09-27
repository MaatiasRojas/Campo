package org.example.main.tambo.controllers;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private void irAAnimales(ActionEvent event) {
        cambiarPantalla(event, "/fxml/animales.fxml", "Gestion de animales");
    }
    @FXML
    private void irASilos(ActionEvent event) {
        cambiarPantalla(event, "/fxml/silo.fxml", "Gestion de silos");
    }

    @FXML
    private void irATambos(ActionEvent event) {
        System.out.println("Ir a Tambos — todavía no implementado");
    }

    @FXML
    private void irAOrdenie(ActionEvent event) {
        System.out.println("Ir a Registrar Ordeñe — todavía no implementado");
    }

    private void cambiarPantalla(ActionEvent event, String rutaFxml, String titulo){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la pantalla" + rutaFxml, e);
        }
    }
}
