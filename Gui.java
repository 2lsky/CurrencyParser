import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Gui extends JFrame {

    public Gui(HashMap<String, HashMap<String, HashMap<String, String>>> bankInfo) {
        super("Валюты онлайн");
        setSize(300, 300);
        setResizable(false);
        Box box = Box.createVerticalBox();
        for (String nameBank: bankInfo.keySet()){
            box.add(this.bankPanel(nameBank, bankInfo.get(nameBank)));
        }
        add(box);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JPanel currPanel(String curr, HashMap<String, String> vals){
        JPanel panel = new JPanel();
        JLabel nameCurr = new JLabel(curr);
        GridLayout layout = new GridLayout(1, 3);
        panel.setLayout(layout);
        panel.add(nameCurr);
        if (vals.get("Купить").contains("_")) {
            JLabel valLabelBuy = new JLabel(vals.get("Купить").split("_")[0]);
            valLabelBuy.setForeground(Color.GREEN);
            panel.add(valLabelBuy);
        }
        else {
            JLabel valLabelBuy = new JLabel(vals.get("Купить"));
            panel.add(valLabelBuy);
        }
        if (vals.get("Продать").contains("_")) {
            JLabel valLabelSell = new JLabel(vals.get("Продать").split("_")[0]);
            valLabelSell.setForeground(Color.GREEN);
            panel.add(valLabelSell);
        }
        else {
            JLabel valLabelSell = new JLabel(vals.get("Продать"));
            panel.add(valLabelSell);
        }
        panel.setVisible(true);
        return panel;
    }
    private JInternalFrame bankPanel(String bankName, HashMap<String, HashMap<String, String>> currs){
        JInternalFrame frame = new JInternalFrame(bankName, false, false, false, false);
        GridLayout layout = new GridLayout(1, 3);
        JPanel initPanel = new JPanel();
        initPanel.setLayout(layout);
        JLabel actName = new JLabel("Действие");
        JLabel buyName = new JLabel("Купить");
        JLabel sellName = new JLabel("Продать");
        initPanel.add(actName);
        initPanel.add(buyName);
        initPanel.add(sellName);
        Box box = Box.createVerticalBox();
        box.add(initPanel);
        for (String nameCurr: currs.keySet()){
            JPanel panel = this.currPanel(nameCurr, currs.get(nameCurr));
            box.add(panel);
        }
        frame.add(box);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}
