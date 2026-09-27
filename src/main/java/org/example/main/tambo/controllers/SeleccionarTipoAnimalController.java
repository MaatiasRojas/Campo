package org.example.main.tambo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SeleccionarTipoAnimalController {

        private boolean seGuardoAlgunAnimal = false;

        public boolean isSeGuardoAlgunAnimal() {
            return seGuardoAlgunAnimal;
        }

        @FXML
        private void irAVaca(ActionEvent event) {
            abrirFormulario(event, "/fxml/nueva_vaca.fxml");
        }

        @FXML
        private void irAToro(ActionEvent event) {
            abrirFormulario(event, "/fxml/nuevo_toro.fxml");
        }

        @FXML
        private void irATernero(ActionEvent event) {
            abrirFormulario(event, "/fxml/nuevo_ternero.fxml");
        }

        private void abrirFormulario(ActionEvent event, String rutaFxml) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                throw new RuntimeException("Error al cargar el formulario: " + rutaFxml, e);
            }
        }
}
