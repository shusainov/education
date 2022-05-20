package tests;

import Util.MyRandom;
import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static SQL.TestTable.selectIdLikeFromTable;
import static SQL.TestTable.updateTest;
import static Util.SQLConnection.closeConnection;

public class TestSimulation {
    private ArrayList<ArrayList<String>> requestStore = new ArrayList<>();

    @BeforeTest
    public void prepare(){
        requestStore = selectIdLikeFromTable(MyRandom.getDoubleNumber(9));
    }

    @Test
    public void simulation() {
        Assert.assertFalse(false, "Вай вай неправильное значение!");
    }

    @AfterMethod
    public void writeResult(ITestResult result) {
        for (ArrayList<String> row : requestStore) {
            Reporter.log("Update test " + row.get(0), true);
            updateTest(Integer.parseInt(row.get(0)),
                    result.getName(),
                    result.getStatus(),
                    result.getMethod().getQualifiedName(),
                    result.getStartMillis(),
                    result.getEndMillis(),
                    AqualityServices.getBrowser().getBrowserName().toString());
        }
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    @AfterTest
    public void closeTest() {
        for (ArrayList<String> row : requestStore) {
            Reporter.log("Restore test " + row.get(0), true);
            updateTest(row);
        }
    }
}
