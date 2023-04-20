import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DetailManagerGUI extends JFrame {
    private DetailManager _detailManager;

    private JTextField typeField;
    private JTextField materialField;
    private JTextField weightField;
    private JTextField quantityField;
    private JTextField sizeField;
    private JTextField formField;
    public DetailManagerGUI(DetailManager detailManager) {
        setTitle("Detail Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(470, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        _detailManager = detailManager;



        JTable table = new JTable();
        panel.add(new JScrollPane(table));

        DefaultTableModel model = new DefaultTableModel(new String[]{"Тип", "Материал", "Вес", "Размер", "Форма", "Модель"}, 0);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        inputPanel.setLayout(new GridLayout(2, 3));
        inputPanel.add(new JLabel("Тип:"));
        typeField = new JTextField();
        inputPanel.add(typeField);
        inputPanel.add(new JLabel("Материал:"));
        materialField = new JTextField();
        inputPanel.add(materialField);
        inputPanel.add(new JLabel("Вес:"));
        weightField = new JTextField();
        inputPanel.add(weightField);
        inputPanel.add(new JLabel("Модель:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Размер:"));
        sizeField = new JTextField();
        inputPanel.add(sizeField);
        inputPanel.add(new JLabel("Форма:"));
        formField = new JTextField();
        inputPanel.add(formField);
        panel.add(inputPanel);


        JPanel buttonPanel = new JPanel();

        panel.add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {
            try {
                String type = typeField.getText();
                String material = materialField.getText();
                double weight = Double.parseDouble(weightField.getText());
                String mod = quantityField.getText();
                double size = Double.parseDouble(sizeField.getText());
                String form = formField.getText();
                Detail detail;
                if (type.equals("Болт")) {
                    detail = new Bolt(form, material, weight, size, mod);
                } else if (type.equals("Гайка")) {
                    detail = new Nut(form, material, weight, size, mod);
                } else if (type.equals("Винт")) {
                    detail = new Screw(form, material, weight, size, mod, 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Неизвестный тип детали: " + type, "Ошибка", JOptionPane.ERROR_MESSAGE);
                    detail = null;
                }
                _detailManager.addDetail(detail);
                String[] row = {detail.getDetailType(), detail.getMaterial(), String.valueOf(detail.getWeight()),
                             String.valueOf(detail.getSize()), detail.getForm(), detail.getThreadSize()};
                model.addRow(row);
            }
            catch (Exception exception){
                JOptionPane.showMessageDialog(null, "Неверно заполнена форма: " + exception, "Ошибка", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(addButton);

        List<Detail> details = _detailManager.getDetails();
        for (Detail detail : details) {
            String[] row = {detail.getDetailType(), detail.getMaterial(), String.valueOf(detail.getWeight()),
                            String.valueOf(detail.getSize()), detail.getForm(), detail.getThreadSize()};
            model.addRow(row);
        }
        table.setModel(model);


        panel.add(buttonPanel, BorderLayout.SOUTH);

        JButton deleteButton = new JButton("Удалить");
        buttonPanel.add(deleteButton);


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    Detail detail = details.get(row);
                    model.removeRow(row);
                    details.remove(row);

                    System.out.println("Данные удалены: " + detail );
                } else {
                    // Если строка не была выбрана, выведите сообщение об ошибке или предупреждение
                    JOptionPane.showMessageDialog(null, "Выберите строку для удаления.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table.getModel().addTableModelListener(e -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    DefaultTableModel model1 = (DefaultTableModel) e.getSource();
                    Object data = model1.getValueAt(row, column);

                    Detail detail = details.get(row);

                    Detail newDetail = null;
                    switch (column) {
                        case 1:
                            newDetail = detail.Clone();
                            newDetail.setMaterial((String)data);
                            break;
                        case 2:
                            newDetail = detail.Clone();
                            newDetail.setWeight(Double.parseDouble((String)data));
                            break;
                        case 3:
                            newDetail = detail.Clone();
                            newDetail.setThreadSize((String) data);
                            break;
                        case 4:
                            newDetail = detail.Clone();
                            newDetail.setSize(Double.parseDouble((String)data));
                            break;
                    }

                    int confirmation = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите изменить деталь?",
                            "Подтверждение изменений", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        if (newDetail != null) {
                            _detailManager.updateDetail(detail, newDetail);

                            details.set(row, newDetail);
                            System.out.println("Данные изменены: " + detail);
                        }
                    } else {
                        JOptionPane.getRootFrame().dispose();
                    }
                }
                catch (ArrayIndexOutOfBoundsException exception){
                    assert true;
                }

            });
        });


        setVisible(true);


    }

}