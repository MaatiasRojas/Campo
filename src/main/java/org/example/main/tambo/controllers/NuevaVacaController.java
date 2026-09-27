package org.example.main.tambo.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import org.example.main.tambo.entities.Establecimiento;
import org.example.main.tambo.entities.Vaca;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioAnimalJDBC;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioEstablecimientoJDBC;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class NuevaVacaController {
    @FXML private TextField txtEspecie;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private TextField txtPesoActual;
    @FXML private TextField txtEstadoSalud;
    @FXML private ComboBox<String> cmbEstadoReproductivo;
    @FXML private ComboBox<String> cmbEstadoLactancia;
    @FXML private Label lblError;

    private final RepositorioEstablecimientoJDBC repositorioEstablecimiento = new RepositorioEstablecimientoJDBC();
    private final RepositorioAnimalJDBC repositorioAnimal = new RepositorioAnimalJDBC(repositorioEstablecimiento);

    @Getter
    private boolean guardadoConExito = false;

    @FXML
    private void initialize(){
        cmbEstadoReproductivo.setItems(FXCollections.observableArrayList(
                "Vacía", "Preñada", "Servicio"));
        cmbEstadoLactancia.setItems(FXCollections.observableArrayList(
                "En lactancia", "Seca"));
    }

    @FXML
    private void guardar(ActionEvent event){
        lblError.setText("");
        if (txtEspecie.getText().isBlank() || dpFechaNacimiento.getValue() == null){
            lblError.setText("Por favor ingrese un valor");
            return;
        }
        double pesoActual;
        try {
            pesoActual = Double.parseDouble(txtPesoActual.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            lblError.setText("El peso debe ser un número válido.");
            return;
        }

        if (cmbEstadoReproductivo.getValue() == null || cmbEstadoLactancia.getValue() == null) {
            lblError.setText("Elegí un Estado Reproductivo y de Lactancia.");
            return;
        }

        List<Establecimiento> establecimientos = repositorioEstablecimiento.listarTodos();
        if (establecimientos.isEmpty()) {
            lblError.setText("No hay ningún Establecimiento cargado en la base de datos.");
            return;
        }
        Establecimiento establecimiento = establecimientos.get(0);

        Vaca nuevaVaca = new Vaca(
                0,
                txtEspecie.getText(),
                dpFechaNacimiento.getValue(),
                pesoActual,
                "Hembra",
                txtEstadoSalud.getText(),
                true,
                establecimiento,
                cmbEstadoReproductivo.getValue(),
                cmbEstadoLactancia.getValue(),
                null, // tamboActual: todavía no asignada a ningún tambo
                new ArrayList<>() // registroOrdenie: arranca vacío
        );

        repositorioAnimal.guardar(nuevaVaca);

        guardadoConExito = true;
        cerrarVentana(event);
    }

    @FXML
    private void cancelar(ActionEvent event){
        cerrarVentana(event);
    }

    private void cerrarVentana(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
