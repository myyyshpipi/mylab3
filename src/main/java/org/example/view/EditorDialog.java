package org.example.view;

import org.example.model.Monstr;
import org.example.model.Potion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Отображение информации о чудовище с возможностью редактирования
class EditorDialog extends JDialog {
    private final Monstr monstr;

    EditorDialog(JFrame parent, Monstr monstr) {
        super(parent, "Редактирование: " + monstr.getName(), true);
        this.monstr = monstr;
        setBounds(100, 100, 800, 600);
        //setLocationRelativeTo(parent);
        initEditorDialog();
    }

    void initEditorDialog() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Основные поля Monstr
        addField(panel, "Имя:", monstr.getName(), monstr::setName);
        addField(panel, "Полное описание:", monstr.getFull_info(), monstr::setFull_info);
        addField(panel, "Описание:", monstr.getDescription(), monstr::setDescription);
        addField(panel, "Место обитания:", monstr.getHabitat(), monstr::setHabitat);
        addField(panel, "Первое упоминание:", monstr.getFirst_mention(), monstr::setFirst_mention);
        addField(panel, "Иммунитеты:", monstr.getImmunities(), monstr::setImmunities);
        addField(panel, "Физические характеристики:", monstr.getPhysical_characteristics(), monstr::setPhysical_characteristics);
        addField(panel, "Дополнительная информация:", monstr.getAdditional_info(), monstr::setAdditional_info);

        // Поля Potion
        Potion potion = monstr.getPotion() != null ? monstr.getPotion() : new Potion();
        addField(panel, "Ингредиенты зелья:", potion.getIngredients(), v -> potion.setIngredients(v));
        addField(panel, "Время приготовления:", potion.getPreparation_time(), v -> potion.setPreparation_time(v));
        addField(panel, "Сила зелья:", potion.getStrength(), v -> potion.setStrength(v));
        monstr.setPotion(potion);

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> dispose());
        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, String label, String value, java.util.function.Consumer<String> setter) {
        panel.add(new JLabel(label));
        JTextField field = new JTextField(value != null ? value : "", 300);
        field.setHorizontalAlignment(JTextField.LEFT);
        //JTextArea field = new JTextArea(value != null ? value : "", 4, 60);
        field.addActionListener(e -> setter.accept(field.getText()));
        panel.add(field);
    }

}
