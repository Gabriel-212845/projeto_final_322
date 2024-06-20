package projeto;

import java.io.IOException;
import java.util.List;

import class01.Caixa;
import class01.Gerente;
import class01.Pessoas;
import class01.lerArquivo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @SuppressWarnings("exports")
    @Override
    public void start(Stage primaryStage) {

        List<Gerente> gerentes = lerArquivo.ler();
        Stage stage3 = new Stage();
        SceneLoja(gerentes.get(0).getCaixasContratados().get(0), gerentes.get(0).getCaixasContratados().get(0).getClientes().get(0), stage3);



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

            Parent root1;

            String domain = email.substring(atIndex);
            switch (domain) {
                case "@gestor.com":
                    errorMessage.setText("Acesso permitido ao módulo: " + selectedModule);


                    /////////////////////////
                    Gerente gerente = new Gerente("Arnaldo", "123456", 550, 0.1);
                    gerente.contratarCaixa(new Caixa("Jorge", "55555", 200, 950));
                    gerente.contratarCaixa(new Caixa("Fábio", "57777", 200, 950));
                    Stage stage2 = new Stage();
                    SceneGerenciamento(gerente, stage2);
                    /////////////////////////


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

                        /////////////////////////
                        try {
                            root1 = FXMLLoader.load(getClass().getResource("primary.fxml"));
                            Scene scene1 = new Scene(root1);
                            Stage stage1 = new Stage();
	                        stage1.setScene(scene1);
	                        stage1.show();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
	                    /////////////////////////
                        
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


    private void SceneGerenciamento(Gerente gerente, Stage stage){
        Parent root;
        try {
            GerenciamentoController.gerente = gerente;
            root = FXMLLoader.load(getClass().getResource("Gerenciamento.fxml"));
            Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private void SceneLoja(Caixa caixa, Pessoas cliente, Stage stage){
        Parent root;
        try {
            ComprarController.caixa = caixa;
            ComprarController.cliente = cliente;
            root = FXMLLoader.load(getClass().getResource("Comprar.fxml"));
            Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}