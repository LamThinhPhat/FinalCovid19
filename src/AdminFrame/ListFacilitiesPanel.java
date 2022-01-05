package AdminFrame;

import ColorFont.Constant;
import table.facility;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ListFacilitiesPanel extends JPanel {
    public ListFacilitiesPanel() {
        setBackground(Constant.my_gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel ListUserbuttonPaneNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ListUserbuttonPaneNorth.setBackground(Constant.my_gray);

        JScrollPane ListFacilitiesCenter = new JScrollPane();
        ListFacilitiesCenter.setPreferredSize(new Dimension(1180,380));
        JTable FacilityTable = new JTable();
        FacilityTable.setFont(Constant.TABLE_FONT);
        FacilityTable.getTableHeader().setFont(Constant.TABLE_HEADER);
        FacilityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableModel def = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        FacilityTable.setModel(def);
        def.addColumn("Facility ID");
        def.addColumn("Name");
        def.addColumn("Current Quantity");
        def.addColumn("Capacity");

        ArrayList<facility> ListFacility = getDB.Facility.FunctionFacility.GetAllFacilityInfo();
        for (facility i : ListFacility) {
            def.addRow(new Object[]{
                    i.getFacility_id(), i.getFacility_name(), i.getCurrent_quantity(), i.getCapacity(),
            });
        }

        ListFacilitiesCenter.setViewportView(FacilityTable);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        FacilityTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        FacilityTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        JButton EditFacility = new JButton("Edit Facility");
        EditFacility.setForeground(Constant.my_white);
        EditFacility.setBackground(new Color(77,82,77));
        EditFacility.setFont(Constant.INFO_FONT);

        JButton AddFacility = new JButton("Add facility");
        AddFacility.setForeground(Constant.my_white);
        AddFacility.setBackground(new Color(77,82,77));
        AddFacility.setFont(Constant.INFO_FONT);

        ListUserbuttonPaneNorth.add(EditFacility);
        ListUserbuttonPaneNorth.add(AddFacility);

        add(Box.createRigidArea(new Dimension(0,50)));
        add(ListFacilitiesCenter);
        add(ListUserbuttonPaneNorth);

        EditFacility.addActionListener(e->{
            int row = FacilityTable.getSelectedRow();
            if(row == -1)
            {
                JOptionPane.showMessageDialog(ListFacilitiesPanel.this, "Please pick a facility to edit", "Error",JOptionPane.ERROR_MESSAGE);
            }else {
                String facility_id = (String) FacilityTable.getValueAt(row, 0);
                facility update= getDB.Facility.FunctionFacility.GetFacilityByID(facility_id);
                new EditFacilityFrame(def,update).setVisible(true);
            }
        });

        AddFacility.addActionListener(e->{
            new AddFacilityFrame(def).setVisible(true);
        });
    }
}
