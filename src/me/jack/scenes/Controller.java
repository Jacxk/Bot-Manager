package me.jack.scenes;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.jack.Main;
import me.jack.PasteUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class Controller {

    public Button button;
    public TextField botName;
    public TextField botToken;
    public Label title;
    public Label errorMessage;
    private static JDA botClient;

    public TextArea dialogPane;
    public TextArea chatField;

    public void onChat() {
        if (chatField.getText().isEmpty()) {
            System.out.println(PasteUtils.post(dialogPane.getText()));
            StringSelection selection = new StringSelection(PasteUtils.post(dialogPane.getText()));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            return;
        }

        MessageChannel textChannel = botClient.getGuildById("310873632052215808").getTextChannelById("408462950035357697");
        textChannel.sendMessage(chatField.getText()).complete();
    }

    public void onClick() {
        if (!botToken.getText().isEmpty()) login();
        else errorMessage.setText("Error: Please enter the Bot Token...");
    }

    private void login() {
        try {
            botClient = new JDABuilder(AccountType.BOT).setToken(botToken.getText()).buildBlocking();

            errorMessage.setText("Successfully logged in... BOT ONLINE");
            errorMessage.setStyle("-fx-text-fill: green");

            Main.loginMenu.close();
            openMainMenu();
        } catch (LoginException | InterruptedException e) {
            errorMessage.setText("Error: Invalid Token...");
            errorMessage.setStyle("-fx-text-fill: red");
        }
    }

    private void openMainMenu() {
        Stage mainMenu = new Stage();

        mainMenu.setTitle("Bot Manager");
        mainMenu.setOnCloseRequest(event -> {
            System.out.println("Shooting down the bot...!");
            botClient.shutdownNow();
            System.out.println("BOT OFFLINE!");
        });

        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            mainMenu.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainMenu.show();
    }

    /*private void addTabs() {
        for (TextChannel channel : botClient.getGuildById("310873632052215808").getTextChannels()) {
            System.out.println(channel.getName());
            tabPane.getTabs().add(new Tab(channel.getName()));
        }
    }*/
}
