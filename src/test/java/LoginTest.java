import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "drives\\chromedriver.exe");
    }

    @BeforeEach
    public void beforEach(){
        this.browser = new ChromeDriver();
        browser.navigate().to(URL_LOGIN);
    }
    @AfterEach
    public void afterEach(){
        this.browser.quit();
    }
    @Test
    public void deveraEfetuarLoginComDadosValidos() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("submit")).submit();
        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
        Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));

    }

    @Test
    public void naodeveraEfetuarLoginComDadosInalidos() {
        browser.findElement(By.id("username")).sendKeys("123qweqwe");
        browser.findElement(By.id("password")).sendKeys("qweqewqwe");
        browser.findElement(By.id("submit")).submit();
        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
    }
    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));
    }


}

