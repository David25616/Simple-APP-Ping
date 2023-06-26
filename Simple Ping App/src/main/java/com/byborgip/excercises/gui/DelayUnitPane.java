package com.byborgip.excercises.gui;

import com.byborgip.excercises.config.DelayConfig;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

public class DelayUnitPane extends HBox {
    private NumericTextField delay;
    private ComboBox<TimeUnit> unit;

    public DelayUnitPane(){
        super(10);
        delay = new NumericTextField();
        unit = new ComboBox<>();
        unit.setItems(FXCollections.observableArrayList(
                TimeUnit.values()
        ));
        this.getChildren().addAll(delay,unit);
        unit.getStyleClass().add("combo-box-config");
        delay.getStyleClass().add("text-field");
    }

    public DelayConfig getSelection(){
        if(delay.getText()==null || unit.getValue()==null)
            return null;
        return new DelayConfig(Integer.valueOf(delay.getText()),unit.getValue());
    }

    public void setSelection(DelayConfig delayConfig) {
        if(delayConfig!=null){
            this.delay.setText(String.valueOf(delayConfig.getDelay()));
            this.unit.getSelectionModel().select(delayConfig.getUnit());
        }else{
            this.delay.setText(StringUtils.EMPTY);
            this.unit.getSelectionModel().select(null);
        }
    }
}
