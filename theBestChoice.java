
import java.util.*;

public class theBestChoice {
    private HashMap<String, HashMap<String, HashMap<String, String>>> bankInfo;
    public theBestChoice(HashMap<String, HashMap<String, HashMap<String, String>>> bankInfo) {
        this.bankInfo = bankInfo;
    }
    private List<String> uniqueCurrs(){
        List <String> currs = new ArrayList<>();
        for (String bankName: this.bankInfo.keySet()){
            currs.addAll(this.bankInfo.get(bankName).keySet());
        }
        Set<String> uniqueCurrs = new HashSet<String>(currs);
        return uniqueCurrs.stream().toList();
    }
    private HashMap<String, HashMap<String, String>> selectChoice(){
        String[] unCurrs = this.uniqueCurrs().toArray(new String[0]);
        HashMap<String, HashMap<String, String>> choice = new HashMap<>();
        for (String currName: unCurrs){
            HashMap<String, Double> currValsBuy = new HashMap<>();
            HashMap<String, Double> currValsSell = new HashMap<>();
            HashMap<String, String> SellBuy = new HashMap<>();
            for (String bankName: this.bankInfo.keySet()){
                currValsBuy.put(bankName, Double.parseDouble(this.bankInfo.get(bankName).get(currName).get("Купить")));
                currValsSell.put(bankName, Double.parseDouble(this.bankInfo.get(bankName).get(currName).get("Продать")));
            }
            SellBuy.put("Купить",
                    currValsBuy.entrySet().stream().
                            sorted(Map.Entry.comparingByValue()).
                            toArray()[0].toString().split("=")[0]);
            SellBuy.put("Продать",
                    currValsSell.entrySet().stream().
                            sorted(Map.Entry.<String, Double>comparingByValue().reversed()).
                            toArray()[0].toString().split("=")[0]);
            choice.put(currName, SellBuy);
        }
        return choice;
    }
    public HashMap<String, HashMap<String, HashMap<String, String>>> transformDict(){
        HashMap<String, HashMap<String, String>> choice = this.selectChoice();
        HashMap<String, HashMap<String, HashMap<String, String>>> newDict = this.bankInfo;
        for (String bankName: this.bankInfo.keySet()){
            for (String currName: choice.keySet()){
                if (this.bankInfo.get(bankName).containsKey(currName)){
                    if (Objects.equals(choice.get(currName).get("Купить"), bankName)){
                        String initVal = newDict.get(bankName).get(currName).get("Купить");
                        newDict.get(bankName).get(currName).put("Купить", initVal + "_");
                    }
                    if (Objects.equals(choice.get(currName).get("Продать"), bankName)) {
                        String initVal = newDict.get(bankName).get(currName).get("Продать");
                        newDict.get(bankName).get(currName).put("Продать", initVal + "_");
                    }
                }
            }
        }
        return newDict;
    }
}
