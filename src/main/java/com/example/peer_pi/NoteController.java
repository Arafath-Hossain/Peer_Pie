package com.example.peer_pi;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckTreeView;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class NoteController extends Controller implements Initializable{

    @FXML
    private MFXButton Done;
    @FXML
    private MFXProgressBar myProgressBar;
    @FXML
    private MFXLegacyListView<String> myList;
    private ArrayList<String> notes = new ArrayList<>();
    @FXML
    private MFXTextField myText;
    @FXML
    private MFXButton myBtn;
    @FXML
    private MFXTextField myTarget;
    @FXML
    private TextArea myTextArea;
    @FXML
    private MFXButton myTargetBtn;
    double target=0.0;
    double progress=0.0;
    @FXML
    private Text progressText;

    public NoteController() throws FileNotFoundException {
    }

    @FXML
    private void SaveBtn(ActionEvent event) throws Exception {
        String text = myText.getText();
        if(!text.isEmpty()) {
            notes.add(text);
            myList.getItems().add(text);
            myText.clear();

            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Prototype 3\\To-Do.txt",true));
            for(String data: notes){
                writer.write(data);
                writer.write("++");
                writer.flush();
            }
            writer.close();
        }
    }
    public ArrayList<String> readDataFromFile(String fileName) {
        ArrayList<String> dataList = new ArrayList<>();
        try {
            FileReader reader = new FileReader("D:\\Prototype 3\\To-Do.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dataItems = line.split("\\+\\+");
                for (String dataItem : dataItems) {
                    dataList.add(dataItem);
                }
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
    @FXML
    private void StickySaveBtn(ActionEvent event) throws IOException {
        String text = myTextArea.getText();
        if(!text.isEmpty()) {
            notes.add(text);
            myList.getItems().add(text);
            myTextArea.clear();
        }
    }
    @FXML
    private void handleDeleteNoteButton(ActionEvent event) throws IOException {
        progress = progress+((10/target)/10);
        myProgressBar.setProgress(progress);
        int selectedIndex = myList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            myList.getItems().remove(selectedIndex);
            notes.addAll((Collection<? extends String>) myList);
            System.out.println(notes.toString());
            FileWriter writer = new FileWriter("D:\\Java\\Prototypes\\Prototype 1\\To-Do.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for(String data: myList.getItems()){
//                bufferedWriter.write(data);
//                bufferedWriter.write("++");
//                bufferedWriter.flush();
                System.out.println(data);
            }
            writer.close();
//            ArrayList<String> dataList = new ArrayList<>();
//            try {
//                FileReader reader = new FileReader("D:\\Java\\Prototypes\\Prototype 1\\To-Do.txt");
//                BufferedReader bufferedReader = new BufferedReader(reader);
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    String[] dataItems = line.split("\\+\\+");
//                    for (String dataItem : dataItems) {
//                        dataList.add(dataItem);
//                    }
//                }
//                bufferedReader.close();
//                if (selectedIndex >= 0 && selectedIndex < dataList.size()) {
//                    dataList.remove(selectedIndex);
//                } else {
//                    System.out.println("Invalid index. No changes made.");
//                    throw new IOException();
//                }
//                System.out.println(dataList);
//                FileWriter writer = new FileWriter("D:\\Java\\Prototypes\\Prototype 1\\To-Do.txt",true);
//                BufferedWriter bufferedWriter = new BufferedWriter(writer);
//                for (String data : dataList) {
//                    bufferedWriter.write(data);
//                    bufferedWriter.newLine();
//                }
//                bufferedWriter.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        if (progress == 1){
            progressText.setText("Congratulations! Achievement Complete");
        }
    }
    @FXML
    private void handleDeleteStickyButton(ActionEvent event) throws IOException {
        int selectedIndex = myList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            notes.remove(selectedIndex);
            myList.getItems().remove(selectedIndex);
        }
    }
    @FXML
    private void setTarget(ActionEvent actionEvent) throws IOException {
        target = Double.parseDouble(myTarget.getText());
//        myTarget.clear();
        progressText.setText("Today's target is "+Math.round(target));
//        FileWriter writer = new FileWriter("D:\\Java\\Prototypes\\Prototype 1\\Target.txt");
//        BufferedWriter bufferedWriter = new BufferedWriter(writer);
//        bufferedWriter.write((int) target);
//        bufferedWriter.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = readDataFromFile("To-Do.txt");
        myList.getItems().addAll(list);
    }
}
