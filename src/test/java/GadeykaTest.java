import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GadeykaTest {

    /* TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR" */

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );

        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );

        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFranceChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );

        parisFranceChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_01
        /*  1.  Открыть базовую ссылку
            2.  Нажать на пункт меню Guide
            3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
                и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap*/
    @Test
    public void testCurrentUrl_IsOnGuidePage_WhenClickOnGuideMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String expectedResult = "https://openweathermap.org/guide";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement menuGuide = driver.findElement(
                By.xpath("//*[@id='desktop-menu']/ul/li[1]")
        );
        menuGuide.click();

        String actualResult = driver.getCurrentUrl();

        //assert
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testCurrentTitle_WhenIsOnGuidePage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement menuGuide = driver.findElement(
                By.xpath("//*[@id='desktop-menu']/ul/li[1]")
        );
        menuGuide.click();

        String actualResult = driver.getTitle();

        //assert
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    // TC_11_02
         /* 1.  Открыть базовую ссылку
            2.  Нажать на единицы измерения Imperial: °F, mph
            3.  Подтвердить, что температура для города показана в Фарингейтах */
    @Test
    public void testImperialUnitsIsShownOnMainPageWhenClickOnSwitchButton() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement buttonClick = driver.findElement(
                By.xpath("//div[@class='controls']//div[3]")
        );
        buttonClick.click();

        WebElement textImperialUnit = driver.findElement(
                By.xpath("//span[@class='heading']")
        );
        String actualResult = textImperialUnit.getText();

        //assert
        Assert.assertEquals(actualResult.substring(actualResult.length() - 1), expectedResult);
        driver.quit();
    }

    // TC_11_03
            /*1.  Открыть базовую ссылку
            2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies
             which are essential for the site to work.  We also use non-essential cookies
              to help us improve our services. Any data collected is anonymised.
             You can allow all cookies or manage them individually.”
            3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”*/
    @Test
    public void testPanelWithTextBelowThePageOnMainPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work."
                + " We also use non-essential cookies to help us improve our services."
                + " Any data collected is anonymised. You can allow all cookies or manage them individually.";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement panelClassName = driver.findElement(By.className("stick-footer-panel__description"));
        String actualResult = panelClassName.getText();

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    @Test
    public void testTwoButtonsAreOnPanelOnMainPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String expectedResultButtonAllowAll = "Allow all";
        String expectedResultButtonManageCookies = "Manage cookies";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__btn-container']/button")
        );
        String actualResultButtonAllowAll = buttonAllowAll.getText();

        WebElement buttonButtonManageCookies = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__btn-container']/a")
        );
        String actualResultButtonManageCookies = buttonButtonManageCookies.getText();

        //assert
        Assert.assertEquals(actualResultButtonAllowAll, expectedResultButtonAllowAll);
        Assert.assertEquals(actualResultButtonManageCookies, expectedResultButtonManageCookies);
        driver.quit();
    }

    // TC_11_04
    /* 1.  Открыть базовую ссылку
        2.  Подтвердить, что в меню Support есть 3 подменю
         с названиями “FAQ”, “How to start” и “Ask a question”*/
    @Test
    public void testSubMenuNamesInSupportMenuWhenClickOnIt() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskQuestion = "Ask a question";

        //act
        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);

        WebElement supportButton = driver.findElement(
                By.id("support-dropdown")
        );
        supportButton.click();

        WebElement faqButton = driver.findElement(
                By.linkText("FAQ")
        );
        WebElement howToStartButton = driver.findElement(
                By.linkText("How to start")
        );
        WebElement askQuestionButton = driver.findElement(
                By.linkText("Ask a question")
        );

        String actualResult1 = faqButton.getText();
        String actualResult2 = howToStartButton.getText();
        String actualResult3 = askQuestionButton.getText();

        //assert
        Assert.assertEquals(actualResult1, expectedResultFAQ);
        Assert.assertEquals(actualResult2, expectedResultHowToStart);
        Assert.assertEquals(actualResult3, expectedResultAskQuestion);

        driver.quit();
    }

    // TC_11_05
    /*1. Открыть базовую ссылку
        2. Нажать пункт меню Support → Ask a question
        3. Заполнить поля Email, Subject, Message
        4. Не подтвердив CAPTCHA, нажать кнопку Submit
        5. Подтвердить, что пользователю будет показана ошибка
         “reCAPTCHA verification failed, please try again.”*/
    @Test
    public void testCaptchaReturnErrorTextOnMemberPageWhenClickSubmit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String message = "Do you have information about the weather on the Мars?";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        //act
        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);

        WebElement supportButton = driver.findElement(
                By.id("support-dropdown")
        );
        supportButton.click();

        WebElement supportDropDrownButton = driver.findElement(
                By.linkText("Ask a question")
        );
        supportDropDrownButton.click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        WebElement inputEmailField = driver.findElement(
                By.id("question_form_email")
        );
        inputEmailField.click();
        inputEmailField.sendKeys("myemail@icloud.com");

        Select selectSubject = new Select(driver.findElement(
                By.id("question_form_subject")
        ));
        Thread.sleep(1000);
        selectSubject.selectByValue("Data Issue");

        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.click();
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(By.name("commit"));
        submitButton.click();

        WebElement captchaText = driver.findElement(
                By.xpath("//div[@class='help-block']")
        );

        String actualResult = captchaText.getText();

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    // TC_11_06
    /*      1.  Открыть базовую ссылку
            2.  Нажать пункт меню Support → Ask a question
            3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
            4. Оставить пустым поле Email
            5. Заполнить поля  Subject, Message
            6. Подтвердить CAPTCHA
            7. Нажать кнопку Submit
            8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank””*/
    @Test
    public void testEmailReturnErrorTextOnMemberPageWhenClickSubmit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";

        String message = "Do you have information about the weather on the Мars?";
        String expectedResult = "can't be blank";

        //act
        driver.get(url);
        driver.manage().window().maximize();

        Thread.sleep(5000);

        WebElement supportButton = driver.findElement(
                By.id("support-dropdown")
        );
        supportButton.click();

        WebElement supportDropDrownButton = driver.findElement(
                By.linkText("Ask a question")
        );
        supportDropDrownButton.click();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        Select selectSubject = new Select(driver.findElement(
                By.id("question_form_subject")
        ));
        Thread.sleep(1000);
        selectSubject.selectByIndex(2);

        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.click();
        messageField.sendKeys(message);

        driver.switchTo().frame(0);

        WebElement captchaCheckbox = driver.findElement(
                By.id("recaptcha-anchor")
        );
        captchaCheckbox.click();

        Thread.sleep(15000);

        driver.switchTo().defaultContent();

        WebElement submitButton = driver.findElement(By.name("commit"));
        submitButton.click();

        WebElement eMailText = driver.findElement(
                By.xpath("//span[@class='help-block']")
        );

        String actualResult = eMailText.getText();

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    // TC_11_07
    /*      1.  Открыть базовую ссылку
            2.  Нажать на единицы измерения Imperial: °F, mph
            3.  Нажать на единицы измерения Metric: °C, m/s
            4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С*/
    @Test
    public void testFahrenheitToCelsiusOnMainPageWhenClickOnSwitcher() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        boolean expectedResult = true;

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement imperialSwitch = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[3]")
        );
        imperialSwitch.click();

        Thread.sleep(2000);
        WebElement imperialData = driver.findElement(
                By.xpath("//span[@class='heading']")
        );
        String fahrenheitData = imperialData.getText();

        Thread.sleep(2000);
        WebElement metricSwitch = driver.findElement(
                By.xpath("//div[@class='switch-container']/div[2]")
        );
        metricSwitch.click();

        Thread.sleep(2000);
        WebElement metricData = driver.findElement(
                By.xpath("//span[@class='heading']")
        );
        String celsiusData = metricData.getText();

        boolean actualResult = !(fahrenheitData.substring(fahrenheitData.length() - 1)
                .equals(celsiusData.substring(celsiusData.length() - 1)));

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    // TC_11_08
    /*      1.  Открыть базовую ссылку
            2.  Нажать на лого компании
            3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась*/
    @Test
    public void testCurrentUrlOnMainPageWhenClickOnLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        boolean expectedResult = true;

        //act
        driver.get(url);
        Thread.sleep(5000);

        String currentUrlBeforeClick = driver.getCurrentUrl();

        WebElement clickOnLogo = driver.findElement(
                By.xpath("//a[@href='/']")
        );
        clickOnLogo.click();

        Thread.sleep(2000);

        String currentUrlAfterClick = driver.getCurrentUrl();

        boolean actualResult = (currentUrlBeforeClick.equals(currentUrlAfterClick));

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    // TC_11_09
    /*      1.  Открыть базовую ссылку
            2.  В строке поиска в навигационной панели набрать “Rome”
            3.  Нажать клавишу Enter
            4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
            5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”*/
    @Test
    public void testSearchFieldWithTextOnMainPageWhenPressEnter() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        //arrange
        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        String wordName = "find";
        boolean expectedResult = true;

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//ul[@id='first-level-nav']//input[@type='text']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        searchCityField.sendKeys(Keys.RETURN);

        Thread.sleep(1000);

        String currentUrl = driver.getCurrentUrl();

        boolean actualResult = currentUrl.contains(cityName) && currentUrl.contains(wordName);

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }

    // TC_11_10_notReady
    /*      1.  Открыть базовую ссылку
            2.  Нажать на пункт меню API
            3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок*/
    @Test
    public void testVisibleOrangeButtonsOnApiPageWhenClickOnApiMenuButton() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        int expectedResult = 30;

        //arrange
        String url = "https://openweathermap.org/";

        //act
        driver.get(url);
        Thread.sleep(5000);

        WebElement apiMenuLing = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//a[@href='/api']")
        );
        apiMenuLing.click();

        Thread.sleep(1000);

        List<WebElement> buttons = driver.findElements(
                By.xpath("//a[@type='button' and contains(@class, 'orange')"
                        + " or contains (@class, 'btn-orange')]")
        );

        int actualResult = buttons.size();

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }


    //TC_11_01 with implicitlyWait
    @Test
    public void test1Openweathermap_justGoToGuide_gdiksanov() {
        System.setProperty("webdriver.chrome.driver", "/Applications/WebDrivers/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get(url);

        driver.manage().window().maximize();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[@class='owm-loader-container']/div")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//div[@id='desktop-menu']//a[@href='/guide']")).click();

        String currentUrl = driver.getCurrentUrl();

        String currentTitle = driver.getTitle();

        Assert.assertEquals(currentUrl, "https://openweathermap.org/guide");

        Assert.assertEquals(currentTitle, "OpenWeatherMap API guide - OpenWeatherMap");

        driver.quit();
    }

}
