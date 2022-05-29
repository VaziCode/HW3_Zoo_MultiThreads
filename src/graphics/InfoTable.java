package graphics;

import animals.Animal;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class InfoTable extends JDialog{

    private static class ZooModel extends AbstractTableModel {
        private Animals data;
        private static final int COLUMN_NUM= 7;
        private final String[] columnNames = {"Name","Animal","Color","Weight","Hor.speed","Var.speed","Eat counter"};

        public ZooModel(Animals data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.getAnimalsSize();
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NUM;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < data.getAnimalsSize()){
                Animal animal = data.getAnimalByIndex(rowIndex);
                switch (columnIndex) {
                    case 0 -> {return animal.getName();
                    }
                    case 1 -> {return animal.getType();
                    }
                    case 2 -> {
                        return animal.getColor();
                    }
                    case 3 -> {
                        return animal.getWeight();
                    }
                    case 4 -> {
                        return animal.getHorSpeed();
                    }
                    case 5 -> {
                        return animal.getVerSpeed();
                    }
                    case 6 -> {
                        return animal.getEatCount();
                    }
                }
            }
            return null;
        }
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 0;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Animal animal = data.getAnimalByIndex(rowIndex);
            switch (columnIndex){
                case 0:animal.setName((String) aValue);
                case 1:animal.setCol((String) aValue);
                case 2:animal.setSize((int) aValue);
                case 3:animal.setHorSpeed((int) aValue);
                case 4:animal.setVerSpeed((int)aValue);
                case 5:animal.setEatCount((int)aValue);;

            }
            fireTableCellUpdated(rowIndex,columnIndex);
        }

    }
    private static class TotalModel extends AbstractTableModel {
        private Animals data;
        private static final int COLUMN_NUM= 7;
        public TotalModel(Animals animalsArr){
            this.data = animalsArr;
        }
        @Override
        public int getRowCount() {
            return 1;
        }
        @Override
        public int getColumnCount() {
            return COLUMN_NUM;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            switch (columnIndex) {
                case 0 -> {
                    return "Total";
                }
                case 6 -> {
                    return  String.valueOf(data.getTotalEatCount());
                }
            }
            return null;
        }
    }



    public InfoTable(Animals animalsArr) {
        setTitle("Info Table");
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        this.setModal(true);
        setBounds(600, 350, 500, 200);
        ZooModel model = new ZooModel(animalsArr);
        JTable table = new JTable(model);
        TableRowSorter<ZooModel> rowSorter = new TableRowSorter<>(model);
        TotalModel totalModel = new TotalModel(animalsArr);
        JTable footer = new JTable(totalModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.setRowSorter(rowSorter);
        table.setOpaque(false);
        table.getTableHeader().setReorderingAllowed(false);
        this.add(new JScrollPane(table));
        this.add(footer);
        this.setVisible(true);
    }

}
