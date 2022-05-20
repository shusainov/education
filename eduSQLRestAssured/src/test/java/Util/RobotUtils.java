package Util;

import org.testng.Reporter;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotUtils {
    private static Robot robot;

    private static Robot getRobot() {
        if (robot == null) {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                Reporter.log("Cannot create Robot" + e, true);
            }
        }
        return robot;
    }

    public static void pressEnter(){
        getRobot().keyPress(KeyEvent.VK_ENTER);
        getRobot().keyRelease(KeyEvent.VK_ENTER);
        Reporter.log("Enter key pressed",true);
    }

    public static void pressCtrlV(){
        getRobot().keyPress(KeyEvent.VK_CONTROL);
        getRobot().keyPress(KeyEvent.VK_V);
        getRobot().keyRelease(KeyEvent.VK_V);
        getRobot().keyRelease(KeyEvent.VK_CONTROL);
        Reporter.log("Ctrl+V pressed",true);
    }
}
