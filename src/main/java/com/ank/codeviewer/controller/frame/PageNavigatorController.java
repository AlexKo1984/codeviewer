package com.ank.codeviewer.controller.frame;

import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class PageNavigatorController extends BasePaneController implements Initializable {
    /**
     * Объект для представления в комбо боксе
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class Page{
        private String title;
        private int number;

        @Override
        public String toString() {
            return title;
        }
    }

    public ComboBox<Page> cbPageNumbers;
    public HBox hboxPageNavigator;
    /**
     * Текущий номер страницы
     */
    private final SimpleObjectProperty<Integer> currentPage;

    public PageNavigatorController() {
        super();
        currentPage = new SimpleObjectProperty<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTotalPage(0);
        setCurrentPage(0);
        cbPageNumbers.valueProperty().addListener(this::onChangeValuePageNumbers);
    }
    @FXML
    public void onPreviousPageButtonClick(ActionEvent actionEvent) {
        setCurrentPage(getCurrentPage() - 1);
    }
    @FXML
    public void onNextPageButtonClick(ActionEvent actionEvent) {
        setCurrentPage(getCurrentPage() + 1);
    }

    private Page createPage(int numberPage){
        return new Page("Страница " + (numberPage + 1), numberPage);
    }

    /**
     * Заполним список выбора страницами
     */
    private void fillPageList(int totalPage) {
        cbPageNumbers.getItems().clear();

        for (int i = 0; i < totalPage; i++)
            cbPageNumbers.getItems().add(createPage(i));
    }
    private void onChangeValuePageNumbers(Observable observable) {
        notifyChangeCurrentPage();
    }

    public SimpleObjectProperty<Integer> currentPageProperty() {
        return currentPage;
    }
    public Integer getCurrentPage() {
        if (cbPageNumbers.getValue() == null)
            return -1;
        else
            return cbPageNumbers.getValue().getNumber();
    }

    public void setCurrentPage(int currentPage) {
        if (0 <= currentPage && currentPage < getTotalPage()){
            cbPageNumbers.setValue(createPage(currentPage));
        }else {
            //Всегда есть хотя бы 1на страница
            cbPageNumbers.setValue(createPage(0));
        }

        notifyChangeCurrentPage();
    }
    public int getTotalPage() {
        //Всегда есть хотя бы 1на страница
        return cbPageNumbers.getItems().size() == 0 ? 1 : cbPageNumbers.getItems().size();
    }
    public void setTotalPage(int totalPage) {
        if (totalPage != getTotalPage()){
            //Всегда есть хотя бы 1на страница
            int amount = totalPage == 0 ? 1 : totalPage;
            fillPageList(amount);
        }
    }
    private void notifyChangeCurrentPage(){
        this.currentPage.set(getCurrentPage());
    }
}
