package br.com.core.pages;

import br.com.core.properties.PropertiesManager;
import br.com.util.Base;

import br.com.core.setup.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CarrinhoPage extends DriverManager {
    private static PropertiesManager execProperties = new PropertiesManager("src/test/resources/exec.properties");

    private Base base;
    private By cart = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a");
    private By table = By.xpath("//table/tbody/tr");

    public CarrinhoPage(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);
    }


    public void ClearCart() throws InterruptedException {
        base.clicarElemento(cart);

        List<WebElement> delete = base.procurarElementos(By.className("cart_quantity_delete"));
        if (delete == null) {
            System.out.println("Carrinho vazio");
        } else {
            for (WebElement d : delete) {
                d.click();
                Thread.sleep(1000);
            }

        }


        String url = execProperties.getProps().getProperty("url");

        driver.get(url);


    }


    public void ValidateItens(String nomeProduto, int quantidade) throws InterruptedException {
        WebElement element = driver.findElement(cart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        base.clicarElemento(cart);
        base.esperarElemento(table, 500);
        List<WebElement> itens = base.procurarElementos(table);
        if (itens == null) {
            Assert.fail("Nenhum produto");
        }
        for (WebElement p : itens) {
            String item = p.findElement(By.tagName("h4")).getText().toLowerCase();
            String qt = p.findElement(By.tagName("button")).getText();
            int number = Integer.parseInt(qt);

            WebElement el = p.findElement(By.tagName("button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);


            Thread.sleep(500);

            if (item.contains(nomeProduto.toLowerCase()) && number == quantidade) {
                System.out.println("|-------------------------------------------------------------------------------|");
                System.out.println("item no carrinho: " + item + " quantidade: " + qt);
                System.out.println("******************************************************************");

                Thread.sleep(500);


            } else {
                Thread.sleep(500);

            }

        }

    }
}
