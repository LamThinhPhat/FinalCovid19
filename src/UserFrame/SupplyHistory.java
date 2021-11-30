package UserFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SupplyHistory extends JPanel {
    private JLabel lbHeader;
    private JPanel headerPanel;

    SupplyHistory() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10,10,10,10));

        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        lbHeader = new JLabel("SUPPLY HISTORY");
        lbHeader.setFont(Constant.HEADER_FONT);
        headerPanel.add(lbHeader);

        add(headerPanel, BorderLayout.NORTH);
    }
}
