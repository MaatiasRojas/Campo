package org.example.main.tambo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import org.example.main.tambo.entities.Animal;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioAnimalJDBC;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioEstablecimientoJDBC;

import java.io.IOException;
import java.util.ArrayList;

public class AnimalController {
    @FXML private TableView<Animal> tablaAnimales;
    @FXML private TableColumn<Animal, Integer> colId;
    @FXML private TableColumn<Animal, String> colEspecie;
    @FXML private TableColumn<Animal, Integer> colEdadMeses;
    @FXML private TableColumn<Animal, Double> colPeso;
    @FXML private TableColumn<Animal, String> colSexo;
    @FXML private TableColumn<Animal, String> colEstadoSalud;
    @FXML private TableColumn<Animal, Boolean> colActivo;

    private final RepositorioAnimalJDBC repositorioAnimal = new RepositorioAnimalJDBC(new RepositorioEstablecimientoJDBC());

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colEdadMeses.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getEdadEnMeses()).asObject());
        colPeso.setCellValueFactory(new PropertyValueFactory<>("pesoActual"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colEstadoSalud.setCellValueFactory(new PropertyValueFactory<>("estadoSalud"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

        cargarAnimales();
    }

    private void cargarAnimales() {
        ArrayList<Animal> animales = (ArrayList<Animal>) repositorioAnimal.listarTodos();
        System.out.println("Animales encontrados: " + animales.size());
        ObservableList<Animal> data = FXCollections.observableArrayList(animales);
        tablaAnimales.setItems(data);
    }

    @FXML
    private void nuevoAnimal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/seleccionar_tipo_animal.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo animal");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

            cargarAnimales();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
