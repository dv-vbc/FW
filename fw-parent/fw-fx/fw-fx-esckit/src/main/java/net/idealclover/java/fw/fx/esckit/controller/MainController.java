/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.fx.esckit.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import net.idealclover.java.fw.fx.esckit.vo.DocVo;
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
        observableList.get(2).setCellValueFactory(new PropertyValueFactory("docType"));
        observableList.get(3).setCellValueFactory(new PropertyValueFactory("title"));
        observableList.get(4).setCellValueFactory(new PropertyValueFactory("author"));
        observableList.get(5).setCellValueFactory(new PropertyValueFactory("keyword"));
        observableList.get(6).setCellValueFactory(new PropertyValueFactory("summary"));
        observableList.get(7).setCellValueFactory(new PropertyValueFactory("filename"));
        observableList.get(8).setCellValueFactory(new PropertyValueFactory("filetype"));
        observableList.get(9).setCellValueFactory(new PropertyValueFactory("filesize"));
        observableList.get(10).setCellValueFactory(new PropertyValueFactory("oldname"));
        observableList.get(11).setCellValueFactory(new PropertyValueFactory("relapath"));
        observableList.get(12).setCellValueFactory(new PropertyValueFactory("oper"));
        observableList.get(13).setCellValueFactory(new PropertyValueFactory("opertime"));

        query();
    }

    @FXML
    public void query() {

        //设置查询条件
        DocVo vo = new DocVo();
        LocalDate btime = btimeDp.getValue();
        String btimeStr = btime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        vo.setBtime(btimeStr);
        LocalDate etime = etimeDp.getValue();
        String etimeStr = etime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        vo.setEtime(etimeStr);

        // 执行查询
        List<DocTableVo> data;
        try {
            data = service.listDoc4Tv(vo);
        } catch (Exception e) {
            e.printStackTrace();
            data = new ArrayList<>();
        }

        // 填充数据
        ObservableList<DocTableVo> list = FXCollections.observableArrayList();
        list.clear();
        for (DocTableVo dtVo : data) {
            dtVo.setCheckbox(true);
            list.add(dtVo);
        }
        docTv.setItems(list);

        
        ObservableList<DocTableVo> list1 = docTv.getItems();
        for (DocTableVo vo1 : list1) {
            if (vo1.getCheckbox()) {

                System.out.println(vo1.getDocDomain());
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
