package com.Admavin.project.Todospage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TodoPage {
    WebDriver driver;
    
    private By todoInput = By.className("new-todo");
    private By todoItems = By.cssSelector(".todo-list li");
    private By todoCheckboxes = By.cssSelector(".todo-list li input.toggle");
    private By deleteButtons = By.className("destroy");

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openApp(String url) {
        driver.get(url);
    }

    public void addTask(String taskName) {
        driver.findElement(todoInput).sendKeys(taskName + Keys.ENTER);
    }

    public List<WebElement> getTasks() {
        return driver.findElements(todoItems);
    }

    public void markTaskCompleted(int index) {
        List<WebElement> checkboxes = driver.findElements(todoCheckboxes);
        checkboxes.get(index).click();
    }

    public boolean isTaskCompleted(int index) {
        return getTasks().get(index).getAttribute("class").contains("completed");
    }

    public void deleteTask(int index) {
        List<WebElement> tasks = getTasks();
        WebElement task = tasks.get(index);

        // Local WebDriverWait
        WebDriverWait localWait = new WebDriverWait(driver, 30); // wait up to 10 seconds
        WebElement deleteButton = localWait.until(ExpectedConditions.elementToBeClickable(
            task.findElement(deleteButtons)
        ));

        deleteButton.click();
    }

    
    public String getTaskName(int index) {
        return getTasks().get(index).getText();
    }
}
