import java.util.HashMap;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Parser parser = new Parser("C:\\Users\\Vitam\\OneDrive\\Рабочий стол\\chromedriver.exe");
        HashMap<String, HashMap<String, HashMap<String, String>>> dict = parser.outDict();
        theBestChoice choice = new theBestChoice(dict);
        HashMap<String, HashMap<String, HashMap<String, String>>> newDict = choice.transformDict();
        System.out.println(newDict);
        Gui g = new Gui(newDict);
    }
}