package gherkin.stepdefs;

import br.com.core.pages.CarrinhoPage;
import br.com.core.pages.LoginPage;
import br.com.core.pages.TelaInicialPage;
import br.com.core.setup.DriverManager;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.testng.Assert;

public class CadastroSteps extends DriverManager {


    private TelaInicialPage tela_inicial;
    private LoginPage login_page;

    private CarrinhoPage carrinho_page;


    public CadastroSteps() {
        tela_inicial = new TelaInicialPage(driver);
        login_page = new LoginPage(driver);
        carrinho_page = new CarrinhoPage(driver);
    }


    @Dado("^que eu efetue o login com o email \"([^\"]*)\"$")
    public void queEuEfetueOLoginComOEmail(String email) throws Throwable {
        tela_inicial.gotoLogin();
        login_page.login(email);
        login_page.submitLogin();
        carrinho_page.ClearCart();


    }

    @Quando("^verifico se estou na pagina inicial$")
    public void verificoSeEstouNaPaginaInicial() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
    }

    @E("^adiciono \"([^\"]*)\" \"([^\"]*)\" itens ao carrinho$")
    public void adicionoItensAoCarrinho(String nomeProduto, int quantidade) throws Throwable {
        tela_inicial.addProduct(nomeProduto, quantidade);
        carrinho_page.ValidateItens(nomeProduto, quantidade);


    }


}
