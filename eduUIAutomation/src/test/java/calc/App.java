package calc;

import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.Application;
import mmarquee.automation.controls.Window;
import utils.Config;

public class App {

    private static Application app = null;

    private static void andRest() {
        // Must be a better way of doing this????
        try {
            Thread.sleep(1000);
        } catch (Throwable ex) {
            // interrupted
        }
    }
    private static void initApp() {
        andRest();
        try {
            UIAutomation automation = UIAutomation.getInstance();
            app = automation.launchOrAttach(Config.get("APP_PATH"));
        } catch (Throwable ex) {
            System.out.println("Failed to find app" + ex);
        }
        app.waitForInputIdle(5000);
        andRest();
    }

    private static Application getApp() {
        if (app == null) initApp();
        return app;
    }

    public static Window getWindow() {
        try {
            return getApp().getWindow(Config.get("APP_TITLE"));
        } catch (AutomationException e) {
            System.out.println("Не получилось получить Window");
        }
        return null;
    }

    public static void quitApp() {
        app.quit(Config.get("APP_TITLE"));
        app =null;
    }
}
