/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.idealclover.java.fw.fx.esckit.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.idealclover.java.fw.fx.esckit.core.Constant;
import net.idealclover.java.fw.fx.esckit.core.FXMLController;
import net.idealclover.java.fw.fx.esckit.core.FXMLRouteController;
import net.idealclover.java.fw.fx.esckit.service.ITransService;
import net.idealclover.java.fw.fx.esckit.vo.DocSerializableVo;
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
        observableList.get(1).setCellValueFactory(new PropertyValueFactory("docDomainName"));
        observableList.get(2).setCellValueFactory(new PropertyValueFactory("docTypeName"));
        observableList.get(3).setCellValueFactory(new PropertyValueFactory("title"));
        observableList.get(4).setCellValueFactory(new PropertyValueFactory("author"));
        observableList.get(5).setCellValueFactory(new PropertyValueFactory("keyword"));
        observableList.get(6).setCellValueFactory(new PropertyValueFactory("summary"));
        observableList.get(7).setCellValueFactory(new PropertyValueFactory("filename"));
        observableList.get(8).setCellValueFactory(new PropertyValueFactory("filetype"));
        observableList.get(9).setCellValueFactory(new PropertyValueFactory("filesize"));
        observableList.get(9).setStyle("-fx-alignment:CENTER-RIGHT;");
        observableList.get(10).setCellValueFactory(new PropertyValueFactory("oldname"));
        observableList.get(11).setCellValueFactory(new PropertyValueFactory("relapath"));
        observableList.get(12).setCellValueFactory(new PropertyValueFactory("oper"));
        observableList.get(13).setCellValueFactory(new PropertyValueFactory("optime"));

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
    }

    @FXML
    public void export() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Transport Files", "*.tra"));
        File file = fileChooser.showSaveDialog(stage);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("文件创建失败！");
                alert.setContentText(e.getMessage());
                alert.initOwner(stage);
                alert.show();
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
                throw new RuntimeException();
            }
        }

        // 准备数据
        ObservableList<DocTableVo> items = docTv.getItems();
        List<DocSerializableVo> dsvolist = new ArrayList<DocSerializableVo>();
        for (DocTableVo vo : items) {
            if (vo.getCheckbox()) {
                DocSerializableVo dsvo = new DocSerializableVo();
                dsvo.setId(vo.getId());
                dsvo.setOper(vo.getOper());
                dsvo.setOpdept(vo.getOpdept());
                dsvo.setOptime(Constant.dffull.parse(vo.getOptime()));
                dsvo.setDocDomain(vo.getDocDomain());
                dsvo.setDocType(vo.getDocType());
                dsvo.setTitle(vo.getTitle());
                dsvo.setKeyword(vo.getKeyword());
                dsvo.setAuthor(vo.getAuthor());
                dsvo.setSummary(vo.getSummary());
                dsvo.setFileId(vo.getFileId());
                dsvo.setFilename(vo.getFilename());
                dsvo.setFiletype(vo.getFiletype());
                dsvo.setFilesize(vo.getFilesize());
                dsvo.setOldname(vo.getOldname());
                dsvo.setRelapath(vo.getRelapath());
                dsvolist.add(dsvo);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(file);
                CheckedOutputStream csum = new CheckedOutputStream(fos, new Adler32());
                ZipOutputStream zos = new ZipOutputStream(csum);
                BufferedOutputStream out = new BufferedOutputStream(zos);
                ObjectOutputStream oos = new ObjectOutputStream(out)) {

            zos.setComment("TableData");
            zos.putNextEntry(new ZipEntry("TableData/A0"));
            oos.writeObject(dsvolist);
            oos.flush();
            zos.closeEntry();
            
            for (DocSerializableVo dsvof : dsvolist) {
                BufferedReader in = new BufferedReader(new FileReader("D:\\escfile\\upload\\".concat(dsvof.getRelapath())));
                zos.putNextEntry(new ZipEntry("FileData\\".concat(dsvof.getRelapath())));
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                out.flush();
                zos.closeEntry();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("数据导出成功！");
            alert.initOwner(stage);
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("文件更新失败！");
            alert.setContentText(e.getMessage());
            alert.initOwner(stage);
            alert.show();
            // 失败了就把文件给删了
            if (file.exists()) {
                file.delete();
            }
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException();
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
