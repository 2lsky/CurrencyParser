import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Parser {
    private ChromeDriver driver;
    private final ChromeOptions chromeOptions;

    public Parser(String pathToDriver) {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        //chromePrefs.put("profile.default_content_settings.popups", 0);
        //options.addArguments("--headless");
        //options.setExperimentalOption("prefs", chromePrefs);
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        this.driver = null;
        this.chromeOptions = options;
    }

    private void driverConnect(String site) {
        try {
            if (this.driver == null) {
                this.driver = new ChromeDriver(this.chromeOptions);
            }
            this.driver.navigate().to(site);
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Connection to " + site);
        } catch (WebDriverException e) {
            this.driver = null;
            System.out.println("No connection");
            System.exit(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void driverClose() {
        if (this.driver != null) {
            this.driver.close();
            this.driver = null;
        }
    }
    private String findWithPattern(String pathToElement, String patternStr){
        String text = this.driver.findElement(By.xpath(pathToElement)).getAttribute("innerHTML");
        System.out.println(text);
        return text.replace(",", ".");
    }
    private HashMap<String, HashMap<String, String>> parseSBER(){
        this.driverConnect("http://www.sberbank.ru/ru/quotes/currencies?tab=sbol");
        String pathToSellValueD = "/html/body/div[1]/div[2]/div[2]/div[3]/div[2]/div/div/div[4]/div[2]/div/div/div/div[1]/div/div/div[3]/table/tbody/tr[2]/td[3]/div/div[1]";
        String pathToBuyValueD = "/html/body/div[1]/div[2]/div[2]/div[3]/div[2]/div/div/div[4]/div[2]/div/div/div/div[1]/div/div/div[3]/table/tbody/tr[2]/td[4]/div/div[1]";
        String pathToSellValueE = "/html/body/div[1]/div[2]/div[2]/div[3]/div[2]/div/div/div[4]/div[2]/div/div/div/div[1]/div/div/div[3]/table/tbody/tr[4]/td[3]/div/div[1]";
        String pathToBuyValueE = "/html/body/div[1]/div[2]/div[2]/div[3]/div[2]/div/div/div[4]/div[2]/div/div/div/div[1]/div/div/div[3]/table/tbody/tr[4]/td[4]/div/div[1]";
        String pattern = ">[\\d,]+</div";
        HashMap<String, String> d = new HashMap<>();
        HashMap<String, String> e = new HashMap<>();
        HashMap<String, HashMap<String, String>> outDict = new HashMap<>();
        String SellValueD = this.findWithPattern(pathToSellValueD, pattern);
        String BuyValueD = this.findWithPattern(pathToBuyValueD, pattern);
        String SellValueE = this.findWithPattern(pathToSellValueE, pattern);
        String BuyValueE = this.findWithPattern(pathToBuyValueE, pattern);
        d.put("Купить", BuyValueD);
        d.put("Продать", SellValueD);
        e.put("Купить", BuyValueE);
        e.put("Продать", SellValueE);
        outDict.put("Доллар", d);
        outDict.put("Евро", e);
        return outDict;
    }
    private HashMap<String, HashMap<String, String>> parseTink(){
        this.driverConnect("https://www.tinkoff.ru/about/exchange/");
        String pathToSellValueD = "/html/body/div[1]/div/div[2]/div/div/div[3]/div[2]/div[2]";
        String pathToBuyValueD = "/html/body/div[1]/div/div[2]/div/div/div[3]/div[2]/div[3]";
        String pathToSellValueE = "/html/body/div[1]/div/div[2]/div/div/div[3]/div[3]/div[2]";
        String pathToBuyValueE = "/html/body/div[1]/div/div[2]/div/div/div[3]/div[3]/div[3]";
        String pattern = ">[\\d,]+</div";
        HashMap<String, String> d = new HashMap<>();
        HashMap<String, String> e = new HashMap<>();
        HashMap<String, HashMap<String, String>> outDict = new HashMap<>();
        String SellValueD = this.findWithPattern(pathToSellValueD, pattern);
        String BuyValueD = this.findWithPattern(pathToBuyValueD, pattern);
        String SellValueE = this.findWithPattern(pathToSellValueE, pattern);
        String BuyValueE = this.findWithPattern(pathToBuyValueE, pattern);
        d.put("Купить", BuyValueD);
        d.put("Продать", SellValueD);
        e.put("Купить", BuyValueE);
        e.put("Продать", SellValueE);
        outDict.put("Доллар", d);
        outDict.put("Евро", e);
        return outDict;
    }
    private HashMap<String, HashMap<String, String>> parseOtkr(){
        this.driverConnect("https://www.open.ru/exchange-person?utm_source=yandex&utm_medium=cpc&utm_campaign=ag1_sea_rb_exchange_core_yandex_brand_highcr_search|cid:75391597&utm_term=%D0%B1%D0%B0%D0%BD%D0%BA%20%D0%BE%D1%82%D0%BA%D1%80%D1%8B%D1%82%D0%B8%D0%B5%20%D0%BA%D1%83%D1%80%D1%81%20%D0%B2%D0%B0%D0%BB%D1%8E%D1%82|phid:39419702614&utm_content=gr:4943804179|b:12295649062|st:search|s:none|p:1|pt:premium|dt:desktop|re:39419702614|drf:no&_openstat=ZGlyZWN0LnlhbmRleC5ydTs3NTM5MTU5NzsxMjI5NTY0OTA2Mjt5YW5kZXgucnU6cHJlbWl1bQ&yclid=11535344075518509055");
        String pathToSellValueD = "/html/body/div[2]/section/div[2]/div/div[1]/div[2]/div[3]/table/tbody/tr[1]/td[2]";
        String pathToBuyValueD = "//html/body/div[2]/section/div[2]/div/div[1]/div[2]/div[3]/table/tbody/tr[1]/td[3]";
        String pathToSellValueE = "/html/body/div[2]/section/div[2]/div/div[1]/div[2]/div[3]/table/tbody/tr[2]/td[2]";
        String pathToBuyValueE = "/html/body/div[2]/section/div[2]/div/div[1]/div[2]/div[3]/table/tbody/tr[2]/td[3]";
        String pattern = ">[\\d,]+</td";
        HashMap<String, String> d = new HashMap<>();
        HashMap<String, String> e = new HashMap<>();
        HashMap<String, HashMap<String, String>> outDict = new HashMap<>();
        String SellValueD = this.findWithPattern(pathToSellValueD, pattern);
        String BuyValueD = this.findWithPattern(pathToBuyValueD, pattern);
        String SellValueE = this.findWithPattern(pathToSellValueE, pattern);
        String BuyValueE = this.findWithPattern(pathToBuyValueE, pattern);
        d.put("Купить", BuyValueD);
        d.put("Продать", SellValueD);
        e.put("Купить", BuyValueE);
        e.put("Продать", SellValueE);
        outDict.put("Доллар", d);
        outDict.put("Евро", e);
        return outDict;
    }
    public HashMap<String, HashMap<String, HashMap<String, String>>> outDict() throws InterruptedException {
        HashMap<String, HashMap<String, HashMap<String, String>>> out = new HashMap<>();
        out.put("Тинькофф", this.parseTink());
        out.put("Открытие", this.parseOtkr());
        try {
            out.put("Сбербанк", this.parseSBER());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return out;
    }
}
