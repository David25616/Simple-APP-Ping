package com.byborgip.excercises.gui;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class NumericTextField extends TextField {

    final TextFormatter<Integer> FORMATTER = new TextFormatter<>(change -> {
        if (change.getControlNewText().matches("\\d*")) {
            return change;
        }
        return null;
    });

    public NumericTextField(String value){
        super(value);
        this.setTextFormatter(FORMATTER);
    }

    public NumericTextField(){
        this.setTextFormatter(FORMATTER);
    }
}
