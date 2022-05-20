package userinterface.forms;

import Util.ClipBoardUtils;
import Util.Config;
import Util.RobotUtils;
import Util.TestRandom;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class AvatarForm extends Form {

    public AvatarForm() {
        super(By.xpath("//div[@class='avatar-and-interests__form']"), "Avatar and interest form");
    }

    private final List<IButton> interestButtons = getElementFactory().findElements(By.xpath("//input[@type='checkbox' and not(@id = 'interest_unselectall')]//following-sibling::span"), ElementType.BUTTON);
    private final IButton unselectAllButton = getElementFactory().getButton(By.xpath("//input[@id = 'interest_unselectall']//following-sibling::span"), "Unselect all");
    private final ILink uploadLink = getElementFactory().getLink(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Upload link");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//button[text()='Next']"), "Next Button");

    public void uncheckUnselectAll() {
        unselectAllButton.click();
    }

    public void set3RandomInterest() {
        Iterator<Integer> randomIterator = TestRandom.getRandomUniqueNumbers(0, interestButtons.size() - 1);
        interestButtons.get(randomIterator.next()).click();
        interestButtons.get(randomIterator.next()).click();
        interestButtons.get(randomIterator.next()).click();
    }

    public void clickUploadPhoto() {
        uploadLink.click();
    }

    public void uploadPhoto() {
        Path filePath = Paths.get(Config.getConfig("/AvatarFilePath"));
        ClipBoardUtils.set(filePath.toAbsolutePath().toString());
        RobotUtils.pressCtrlV();
        RobotUtils.pressEnter();
    }

    public void clickNext() {
        nextButton.click();
    }
}
