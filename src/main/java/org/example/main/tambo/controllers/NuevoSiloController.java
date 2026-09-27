package org.example.main.tambo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.main.tambo.entities.Establecimiento;
import org.example.main.tambo.entities.Silo;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioEstablecimientoJDBC;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioSiloJDBC;

import java.time.LocalDate;
import java.util.List;

public class NuevoSiloController {

    @FXML private TextField txtIdentificador;
    @FXML private TextField txtTipoAlimento;
    @FXML private TextField txtCapacidadMaxima;
    @FXML private TextField txtStockActual;
    @FXML private TextField txtCosto;
    @FXML private DatePicker dpFechaLlenado;
    @FXML private CheckBox chkActivo;
    @FXML private Label lblError;

    private final RepositorioEstablecimientoJDBC repositorioEstablecimiento = new RepositorioEstablecimientoJDBC();
    private final RepositorioSiloJDBC repositorioSilo = new RepositorioSiloJDBC(repositorioEstablecimiento);

    private boolean guardadoConExito = false;

    public boolean isGuardadoConExito() {
        return guardadoConExito;
    }

    @FXML
    private void guardar(ActionEvent event) {
        lblError.setText("");

        if (txtIdentificador.getText().isBlank() || txtTipoAlimento.getText().isBlank()){
            lblError.setText("Identificador y tipo de alimento son obligatorios");
            return;
        }
        double capacidadMaxima = 0, stockActual = 0, costo = 0;
        try {
            capacidadMaxima = Double.parseDouble(txtCapacidadMaxima.getText().replace(",","."));
            stockActual = Double.parseDouble(txtStockActual.getText().replace(",","."));
            costo = Double.parseDouble(txtCosto.getText().replace(",","."));
        } catch (NumberFormatException e) {
            lblError.setText("Capacidad, Stock y Costo deben ser numeros validos");
        }
        if (capacidadMaxima < stockActual){
            lblError.setText("El stock no puede superar la capacidad maxima");
            return;
        }

        LocalDate fechaLllenado = dpFechaLlenado.getValue() != null ? dpFechaLlenado.getValue() : LocalDate.now();

        List<Establecimiento> establecimientos = repositorioEstablecimiento.listarTodos();
        if (establecimientos.isEmpty()){
            lblError.setText("No hay ningun establecimiento cargado en la base de datos");
            return;
        }
        Establecimiento establecimiento = establecimientos.get(0);

        Silo nuevoSilo = new Silo(
                0,
                txtIdentificador.getText(),
                txtTipoAlimento.getText(),
                capacidadMaxima,
                stockActual,
                costo,
                fechaLllenado,
                establecimiento,
                chkActivo.isSelected()
        );
        repositorioSilo.guardar(nuevoSilo);

        guardadoConExito = true;
        cerrarVentana(event);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        cerrarVentana(event);
    }

    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
