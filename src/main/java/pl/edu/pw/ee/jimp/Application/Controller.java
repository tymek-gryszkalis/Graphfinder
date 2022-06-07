package pl.edu.pw.ee.jimp.Application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import pl.edu.pw.ee.jimp.Backend.Filehandler;
import pl.edu.pw.ee.jimp.Backend.Generator;
import pl.edu.pw.ee.jimp.Backend.Graph;

public class Controller implements Initializable {
    
    @FXML
    private TextField genAdress, readAdress, xField, yField, lBordField, hBordField;
    @FXML
    private CheckBox consistentCB;
    @FXML
    private Button genButton;
    @FXML
    private Slider branchSlider;
    @FXML
    private Label sliderDesc;

    Alert error = new Alert(AlertType.ERROR), info = new Alert(AlertType.CONFIRMATION);
    String sliderDisplay;
    int sliderVal, xVal, yVal;
    double lBordVal, hBordVal;
    boolean isConsistent = false;
    Graph currentGraph;

    public void genGraph(ActionEvent event) {
        currentGraph = Generator.genGraph(xVal, yVal, lBordVal, hBordVal, sliderVal, isConsistent);
        System.out.println(currentGraph);
    }

    public void saveGraph() {
        if (currentGraph == null) {
            error.setContentText("Graph not generated!");
            error.show();
            return;
        }
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Special graph files(*.jimp)", "*.jimp"));
        chooser.setTitle("Save File");
        String filepath = chooser.showSaveDialog(new Stage()).getPath();
        Filehandler.saveGraph(currentGraph, filepath, xVal, yVal);
        info.setHeaderText(null);
        info.setContentText("Graph successfuly saved!");
        info.show();
    }

    public void openGraph() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Special graph files(*.jimp)", "*.jimp"));
        chooser.setTitle("Open File");
        String filepath = chooser.showOpenDialog(new Stage()).getPath();
        try {
            currentGraph = Filehandler.readGraph(filepath);
        } catch (Exception e) {
            error.setHeaderText(null);
            error.setContentText("Couldn't open the file!");
            error.show();
        }
        info.setContentText("Graph opened successfuly!");
        info.setHeaderText(null);
        info.show();
    }

    public void submitNums(ActionEvent event) {
        try {
            xVal = Integer.parseInt(xField.getText());
            yVal = Integer.parseInt(yField.getText());
            lBordVal = Double.parseDouble(lBordField.getText());
            hBordVal = Double.parseDouble(hBordField.getText());
        } catch (NumberFormatException e) { // check if value's not a number
            error.setHeaderText(null);
            error.setContentText("All values must be numbers!");
            error.show();
            componentsLocked(true);
            return;
        }

        // check if nums are fine
        if (xVal < 0 || yVal < 0 || lBordVal > hBordVal || lBordVal < 0 || hBordVal > 1) {
            error.setHeaderText(null);
            error.setContentText("Wrong values!");
            error.show();
            componentsLocked(true);
            return;
        }

        componentsLocked(false);
        System.out.println("Submit nums x: " + xVal + ", y: " + yVal + ", lb: " + lBordVal + ", hb: " + hBordVal);
    }

    public void consistentCheck(ActionEvent event) {
        if (consistentCB.isSelected()) {
            isConsistent = true;
        } else {
            isConsistent = false;
        }
        sliderVal = (int)branchSlider.getValue();
        sliderDesc.setText(branchDisplay(sliderVal, xVal, yVal, isConsistent));
    }

    private String branchDisplay(int val, int x, int y, boolean isConsistent) {
        int minNumOfBranches, maxNumOfBranches = (x - 1) * y + (y - 1) * x;
        if (val == 1000) {
            return "Number of connections: all";
        }
        if (isConsistent) {
            minNumOfBranches = x * y - 1;
        } else {
            minNumOfBranches = 0;
        }
        double percentage = (double)val / 1000.0;
        int translatedVal = minNumOfBranches + (int)(percentage * (double)(maxNumOfBranches - minNumOfBranches));
        return "Number of connections: " + Integer.toString(translatedVal);
    }

    private void componentsLocked(boolean b) {
        if (b) {
            branchSlider.setDisable(true);
            consistentCB.setDisable(true);
            genButton.setDisable(true);
            sliderDesc.setText("");
        } else {
            branchSlider.setDisable(false);
            consistentCB.setDisable(false);
            genButton.setDisable(false);
            sliderVal = (int)branchSlider.getValue();
            sliderDesc.setText(branchDisplay(sliderVal, xVal, yVal, isConsistent));
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // init
        componentsLocked(true);
        branchSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                // loop
                sliderVal = (int)branchSlider.getValue();
                sliderDisplay = branchDisplay(sliderVal, xVal, yVal, isConsistent);
                sliderDesc.setText(sliderDisplay);
            }   
        }); 
    }
}
