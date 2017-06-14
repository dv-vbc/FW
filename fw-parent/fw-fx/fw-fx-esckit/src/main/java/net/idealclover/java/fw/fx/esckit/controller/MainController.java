/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.fx.esckit.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import net.idealclover.java.fw.fx.esckit.core.FXMLController;
import net.idealclover.java.fw.fx.esckit.core.FXMLRouteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author DragonFly
 */
@Controller("mainController")
public class MainController implements FXMLController {

    @Autowired
    private FXMLRouteController fxmlRouteController;

    private Stage stage;

    private final String title = "ETL Tool for KB Platform";

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    protected void initialize(){
        stage.setResizable(false);
        stage.setTitle(this.title);
    }
    
    @FXML
    public void exit() {
        System.exit(0);
    }
    
    @FXML
    public void about() {
        fxmlRouteController.showDialog("about");
    }

}
