package ColorFont;

import java.awt.*;

public class Constant {
    static public Color my_gray = new Color(61,64,62);
    static public Color my_white = new Color(235,235,235);
    static public String SYSTEM_FONT = "Serif";
    static public int  SYSTEM_FONT_SIZE = 22;
    static public int SYSTEM_HEADER_SIZE = 35;
    static public Font INFO_FONT = new Font(Constant.SYSTEM_FONT, Font.PLAIN, Constant.SYSTEM_FONT_SIZE);
    static public Font LABEL_FONT = new Font(Constant.SYSTEM_FONT, Font.BOLD, Constant.SYSTEM_FONT_SIZE);
    static public Font HEADER_FONT = new Font(Constant.SYSTEM_FONT, Font.BOLD, Constant.SYSTEM_HEADER_SIZE);
    static public Font TABLE_FONT = new Font(Constant.SYSTEM_FONT, Font.PLAIN, 15);
    static public Font TABLE_HEADER = new Font(Constant.SYSTEM_FONT, Font.BOLD, 18);
}
