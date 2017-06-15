/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.fx.esckit.controller;

import java.time.LocalDate;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.idealclover.java.fw.fx.esckit.core.FXMLController;
import net.idealclover.java.fw.fx.esckit.core.FXMLRouteController;
import net.idealclover.java.fw.fx.esckit.service.ITransService;
import net.idealclover.java.fw.fx.esckit.vo.DocTableVo;
import net.idealclover.java.fw.fx.esckit.vo.SysparaVo;
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

    @Autowired
    private ITransService service;

    private Stage stage;

    private final String title = "ETL Tool for KB Platform";

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    MenuItem importMi;

    @FXML
    MenuItem exportMi;

    @FXML
    DatePicker btimeDp;

    @FXML
    DatePicker etimeDp;

    @FXML
    TableView docTv;

    @FXML
    TableColumn checkboxTc;

    @FXML
    protected void initialize() {
        stage.setResizable(false);
        stage.setTitle(this.title);
        // 设置菜单项状态
        try {
            SysparaVo vo = service.getWebState();
            String webState = vo.getValue();
            if ("1".equals(webState)) {
                importMi.setDisable(false);
                exportMi.setDisable(true);
            } else if ("0".equals(webState)) {
                importMi.setDisable(true);
                exportMi.setDisable(false);
            }
        } catch (Exception e) {
            importMi.setDisable(true);
            exportMi.setDisable(true);
        }
        // 默认查询
        // 设置默认查询条件
        LocalDate today = LocalDate.now();
        LocalDate lastMonthDay = today.plusDays(-30);
        btimeDp.setValue(lastMonthDay);
        etimeDp.setValue(today);
        // 设置表格格式
        docTv.setEditable(true);
        ObservableList<TableColumn> observableList = docTv.getColumns();
        observableList.get(0).setCellFactory(CheckBoxTableCell.forTableColumn(observableList.get(0)));
        observableList.get(0).setEditable(true);
        observableList.get(0).setCellValueFactory(new PropertyValueFactory("checkbox"));
        observableList.get(1).setCellValueFactory(new PropertyValueFactory("docDomain"));

       // query();
       ObservableList<DocTableVo> list = FXCollections.observableArrayList();
        DocTableVo vo1 = new DocTableVo();
        vo1.setCheckbox(true);
        vo1.setDocDomain("知识库");
        DocTableVo vo2 = new DocTableVo();
        vo2.setCheckbox(true);
        vo2.setDocDomain("三标体系");
        list.add(vo1);
        list.add(vo2);
        docTv.setItems(list);
        
        
    }

    @FXML
    public void query() {
        
        ObservableList<DocTableVo> list1 = docTv.getItems();
        for (DocTableVo vo : list1) {
            if (vo.getCheckbox()) {
                
                System.out.println(vo.getDocDomain());
            }
        }
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
