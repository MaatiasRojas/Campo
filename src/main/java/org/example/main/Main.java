package org.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.main.tambo.config.ConexionDB;
import org.example.main.tambo.entities.Establecimiento;
import org.example.main.tambo.repositories.interfaces.jdbc.RepositorioEstablecimientoJDBC;

import java.util.ArrayList;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        ConexionDB.inicializarEsquema();
        crearEstablecimientoPorDefectoSiNoExiste();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/principal.fxml"));
        Parent root = loader.load();

        stage.setTitle("Gestion de campo");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void crearEstablecimientoPorDefectoSiNoExiste(){
        RepositorioEstablecimientoJDBC repositorioEstablecimiento = new RepositorioEstablecimientoJDBC();

        if(repositorioEstablecimiento.listarTodos().isEmpty()){
            Establecimiento establecimiento = new Establecimiento(
                    0,
                    "Mi establecimiento",
                    "Razon social S.A",
                    "COD-0001",
                    100.0,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
            repositorioEstablecimiento.guardar(establecimiento);
            System.out.println("Establecimiento creado y  guardado");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}