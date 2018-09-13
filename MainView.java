import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

class MainView {

    private JPanel panel = new JPanel();
    private GridLayout layout = new GridLayout(2,4);
    private ArrayList<HazardPanel> panelList = new ArrayList<>();
    private int currentIndex = 0;

    MainView(){
        panel.setPreferredSize(new Dimension(1600,900));
        panel.setLayout(layout);
        initialise();
    }

    void addHazard(ArrayList fieldData){
        panelList.get(currentIndex).hazard.setText((String) fieldData.get(1));
        panelList.get(currentIndex).startDate.setText((String) fieldData.get(2));
        panelList.get(currentIndex).endDate.setText((String) fieldData.get(3));
        panelList.get(currentIndex).description.setText((String) fieldData.get(4));
        panelList.get(currentIndex).setToActive();
        currentIndex+=1;
        panelList.get(currentIndex).setToAdd();
    }

    private void removeHazard(){

    }

    JPanel getPanel(){
        return this.panel;
    }

    private void initialise(){
        for(int i=0;i<8;i++) {
            HazardPanel h = new HazardPanel();
            panelList.add(h);
            panel.add(h.getHazardPanel());
        }

        panelList.get(currentIndex).setToAdd();
    }

    class HazardPanel {
        JPanel parentPanel;
        JPanel activePanel = new JPanel();
        JPanel inactivePanel = new JPanel();
        JPanel addPanel = new JPanel();
        CardLayout cl;

        boolean visible; //?
        boolean active; //?
        JButton addBtn = new JButton("Add Hazard");

        JLabel hazard = new JLabel();
        JPanel icon = new JPanel();
        JLabel description = new JLabel();
        //JLabel location; //i.e. fume cupboard 1.2
        JPanel datePanel = new JPanel();
        JLabel startDate = new JLabel();
        JLabel endDate = new JLabel();

        HazardPanel(){
            //init panels
            inactivePanel.setPreferredSize(new Dimension(400,450));
            activePanel.setPreferredSize(new Dimension(400,450));
            addPanel.setPreferredSize(new Dimension(400,450));
            activePanel.setLayout(new BoxLayout(activePanel,BoxLayout.PAGE_AXIS));
            addPanel.setLayout(new GridBagLayout());

            //activePanel components
            hazard.setPreferredSize(new Dimension(300,50));
            icon.setPreferredSize(new Dimension(250,250));
            description.setPreferredSize(new Dimension(300,50));
            datePanel.setPreferredSize(new Dimension(300,50));
            datePanel.setLayout(new GridLayout());
            datePanel.add(startDate);
            datePanel.add(endDate);
            //adding components to activePanel
            activePanel.add(hazard);
            activePanel.add(icon);
            activePanel.add(description);
            activePanel.add(datePanel);

            //adding components to addPanel
            addBtn.addActionListener(e -> Controller.updateView("Creation"));
            addPanel.add(addBtn);

            //CardLayout
            parentPanel = new JPanel();
            cl = new CardLayout();
            parentPanel.add(activePanel,"Active");
            parentPanel.add(inactivePanel,"Inactive");
            parentPanel.add(addPanel,"Add");
            cl.addLayoutComponent(activePanel,"Active");
            cl.addLayoutComponent(inactivePanel,"Inactive");
            cl.addLayoutComponent(addPanel,"Add");

            parentPanel.setLayout(cl);
            cl.show(parentPanel,"Inactive");

            parentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
            parentPanel.setVisible(true);
        }

        void setToActive(){
            cl.show(parentPanel,"Active");
        }

        void setToInactive(){
            cl.show(parentPanel,"Inactive");
        }

        void setToAdd(){
            cl.show(parentPanel,"Add");
        }

        JPanel getHazardPanel(){
            return this.parentPanel;
        }
    }
}
