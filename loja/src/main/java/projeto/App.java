package projeto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login - Loja de Eletrônicos");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 0);

        TextField emailInput = new TextField();
        emailInput.setPromptText("email@example.com");
        GridPane.setConstraints(emailInput, 1, 0);

        Label passwordLabel = new Label("Senha:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Senha");
        GridPane.setConstraints(passwordInput, 1, 1);

        Label moduleLabel = new Label("Selecione o Módulo:");
        GridPane.setConstraints(moduleLabel, 0, 2);

        ComboBox<String> moduleComboBox = new ComboBox<>();
        moduleComboBox.getItems().addAll("Selecione o Módulo", "Compras", "Estoque", "Gerenciamento");
        moduleComboBox.setValue("Selecione o Módulo");
        GridPane.setConstraints(moduleComboBox, 1, 2);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 3);

        Label errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: red;");
        GridPane.setConstraints(errorMessage, 1, 4);

        loginButton.setOnAction(e -> {
            String email = emailInput.getText();
            String password = passwordInput.getText();
            String selectedModule = moduleComboBox.getValue();

            if (email.isEmpty() || password.isEmpty() || selectedModule.equals("Selecione o Módulo")) {
                errorMessage.setText("Por favor, preencha todos os campos e selecione um módulo.");
                return;
            }

            int atIndex = email.indexOf('@');
            if (atIndex <= 0 || atIndex == email.length() - 1) {
                errorMessage.setText("Login inválido");
                return;
            }

            String domain = email.substring(atIndex);
            switch (domain) {
                case "@gestor.com":
                    errorMessage.setText("Acesso permitido ao módulo: " + selectedModule);
                    primaryStage.close();
                    break;
                case "@funcionario.com":
                    if (selectedModule.equals("Compras") || selectedModule.equals("Estoque")) {
                        errorMessage.setText("Acesso permitido ao módulo: " + selectedModule);
                        primaryStage.close();
                    } else {
                        errorMessage.setText("Login inválido");
                    }
                    break;
                case "@cliente.com":
                    if (selectedModule.equals("Compras")) {
                        errorMessage.setText("Acesso permitido ao módulo: Compras");
                        primaryStage.close();
                    } else {
                        errorMessage.setText("Login inválido");
                    }
                    break;
                default:
                    errorMessage.setText("Login inválido");
                    break;
            }
        });

        grid.getChildren().addAll(emailLabel, emailInput, passwordLabel, passwordInput, moduleLabel, moduleComboBox, loginButton, errorMessage);

        Scene scene = new Scene(grid, 550, 225);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(550);
        primaryStage.setMinHeight(225);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}