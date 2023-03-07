package br.com.core.pages;

import br.com.core.properties.PropertiesManager;
import br.com.core.setup.DriverManager;
import br.com.util.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TelaInicialPage extends DriverManager {

    private Base base;




    private By btnLogin = By.xpath("//a[contains(@href,'login')]");
    private static PropertiesManager execProperties = new PropertiesManager("src/test/resources/exec.properties");

    public TelaInicialPage(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);
    }

    public void gotoLogin() {
        base.clicarElemento(btnLogin);
    }



    public void addProduct(String nomeProduto, int quantidade) throws InterruptedException {


        String url = execProperties.getProps().getProperty("url");

        driver.get(url);

        int idx = 0;


        List<WebElement> itens = base.procurarElementos(By.xpath("//div[@class='productinfo text-center']"));
        if (itens == null) {
            Assert.fail("Nenhum produto");

        }

        for (WebElement Prod : itens) {
            String item = Prod.findElement(By.tagName("p")).getText().toLowerCase();


            if (item.contains(nomeProduto.toLowerCase())) {

                WebElement element = Prod.findElement(By.tagName("p"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                Thread.sleep(500);


                for (int x = 0; x < quantidade; x++) {
                    idx++;
                    System.out.println("|-------------------------------------------------------------------------------|");
                    System.out.println("|    ADICIONANDO UM PRODUTO: " + idx + "- " + item + "|");
//                    System.out.println("|-------------------------------------------------------------------------|");
                    Prod.findElement(By.tagName("a")).click();

                    base.esperarElemento(By.xpath("//*[@id=\"cartModal\"]/div/div/div[3]/button"), 100);
                    base.clicarElemento(By.xpath("//*[@id=\"cartModal\"]/div/div/div[3]/button"));
                }
                break;
            } else {
                continue;
            }


        }


    }
}
