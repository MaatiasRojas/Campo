package org.example.main.tambo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.main.tambo.entities.Silo;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioEstablecimientoJDBC;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioSiloJDBC;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class SiloController {

    @FXML private TableView<Silo> tablaSilos;
    @FXML private TableColumn<Silo, String> colIdentificador;
    @FXML private TableColumn<Silo, String> colTipoAlimento;
    @FXML private TableColumn<Silo, Double> colCapacidad;
    @FXML private TableColumn<Silo, Double> colStock;
    @FXML private TableColumn<Silo, String> colCosto;
    @FXML private TableColumn<Silo, String> colActivo;

    private final RepositorioSiloJDBC repositorioSilo = new RepositorioSiloJDBC(new RepositorioEstablecimientoJDBC());

    @FXML
    public void initialize() {
        colIdentificador.setCellValueFactory(new PropertyValueFactory<>("identificadorSilo"));
        colTipoAlimento.setCellValueFactory(new PropertyValueFactory<>("tipoAlimento"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadMaximaKg"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stockActualKg"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costoAlimentoKg"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("estaActivo"));

        cargarSilos();
    }
    private void cargarSilos() {
        List<Silo> silos = repositorioSilo.listarTodos();
        ObservableList<Silo> data = FXCollections.observableArrayList(silos);
        tablaSilos.setItems(data);
    }

    @FXML
    private void nuevoSilo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevo_silo.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Silo");
            dialogStage.initModality(Modality.APPLICATION_MODAL); // bloquea la ventana principal mientras está abierto
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait(); // espera a que se cierre el modal antes de seguir

            cargarSilos(); // refresca la tabla, tenga o no un silo nuevo
        } catch (IOException e) {
            throw new RuntimeException("Error al abrir el formulario de nuevo silo", e);
        }
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestion de campo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
