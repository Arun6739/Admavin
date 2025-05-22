package com.Admavin.project.Todostest;

import com.Admavin.project.Todospage.TodoPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TodoTest {
    WebDriver driver;
    TodoPage todoPage;

    @BeforeClass
    public void setup() {
    	
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\pcs-pc\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        todoPage = new TodoPage(driver);
        todoPage.openApp("https://todomvc.com/examples/react/dist/");
    }

    @Test(priority = 1)
    public void addThreeTasks() {
        todoPage.addTask("Task 1");
        todoPage.addTask("Task 2");
        todoPage.addTask("Task 3");

        Assert.assertEquals(todoPage.getTasks().size(), 3, "3 tasks should be added");
    }

    @Test(priority = 2)
    public void markSecondTaskCompleted() {
        todoPage.markTaskCompleted(1);
        Assert.assertTrue(todoPage.isTaskCompleted(1), "Second task should be marked completed");
    }

    @Test(priority = 3)
    public void deleteFirstTask() {
        todoPage.deleteTask(0);
        
        Assert.assertEquals(todoPage.getTasks().size(), 2, "After deletion, 2 tasks should remain");
        Assert.assertNotEquals(todoPage.getTaskName(1), "Task 1", "First task should be deleted");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) 
    	{
           driver.quit();
        }
    }
}
