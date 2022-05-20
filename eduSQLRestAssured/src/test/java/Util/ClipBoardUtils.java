package Util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class ClipBoardUtils {
    public static void set(String string){
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
}
