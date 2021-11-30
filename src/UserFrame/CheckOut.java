package UserFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CheckOut extends JPanel{
    private JLabel lbHeader;
    private JPanel headerPanel;

    CheckOut() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10,10,10,10));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        lbHeader = new JLabel("CHECK OUT");
        lbHeader.setFont(Constant.HEADER_FONT);
        headerPanel.add(lbHeader);

        add(headerPanel, BorderLayout.NORTH);
    }
}
