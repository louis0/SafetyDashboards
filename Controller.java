import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Controller {

    private static JFrame frame = new JFrame();
    private static JPanel cards;
    private static CardLayout cardLayout = new CardLayout();
    int index = 0;
    private static MainView mainView = new MainView();
    private static CreationView creationView = new CreationView();

    Controller(){
        cards = new JPanel();//cardLayout);
        cards.add(mainView.getPanel(),"Main");
        cards.add(creationView.getPanel(),"Creation");
        cardLayout.addLayoutComponent(mainView.getPanel(),"Main");
        cardLayout.addLayoutComponent(creationView.getPanel(),"Creation");

        cards.setLayout(cardLayout);
        cardLayout.show(cards,"Main");
        //cardLayout.show(cards,"Creation");

        frame.add(cards);
        frame.pack();
        frame.setVisible(true);
    }

    static void addHazard(ArrayList fieldData){
        //frame.setVisible(false);
        mainView.addHazard(fieldData);
        //updateView("Main");
        //frame.setVisible(true);
    }

    static void submitBrowse(String chemical, String hazard){
        frame.setVisible(false);
        CreationView.hazardText.setText(hazard);
        frame.setVisible(true);
    }

    static void updateView(String s){
        switch(s){
            case "Main": cardLayout.show(cards,"Main") ; break;
            case "Creation": cardLayout.show(cards,"Creation") ; break;
        }

    }
}
