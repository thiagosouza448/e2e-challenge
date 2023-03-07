package br.com.core.pages;

import br.com.core.properties.PropertiesManager;
import br.com.core.report.ExtentReports;
import br.com.core.setup.DriverManager;
import br.com.util.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends DriverManager {

    private Base base;

    private By inputEmail = By.xpath("//input[@data-qa=\"login-email\"]");
    private By inputSenha = By.xpath("//input[@data-qa=\"login-password\"]");
    private By btnLogin = By.xpath("//button[@data-qa=\"login-button\"]");


    private By btnAcessar = By.xpath("//button[@data-qa=\"login-button\"]");



    private static PropertiesManager execProperties = new PropertiesManager("src/test/resources/exec.properties");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        base = new Base(driver);
    }

    public void BtnLogin() {
        base.clicarElemento(btnAcessar);
        ExtentReports.appendToReport(driver);
    }


    public void login(String email) {
        String senha = execProperties.getProps().getProperty("senha");

        base.preencherTexto(inputEmail, email);
        if (senha.isEmpty()) {
            base.preencherTexto(inputSenha, "190896");
        } else {
            base.preencherTexto(inputSenha, senha);
        }
        ExtentReports.appendToReport(driver);
    }

    public void submitLogin() {
        base.clicarElemento(btnLogin);
        ExtentReports.appendToReport(driver);
//        driver.findElement(btnConfirmaCadastro).click();
    }

}
