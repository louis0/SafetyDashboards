import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

class MainView {

    private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));


    private JPanel hPanel = new JPanel();
    private GridLayout hazardLayout = new GridLayout(2,4,0,0);

    private ArrayList<HazardPanel> panelList = new ArrayList<>();
    private int currentIndex = 0;

    JLabel label;

    MainView(){

        hPanel.setPreferredSize(new Dimension(1400,900));
        hPanel.setLayout(hazardLayout);
        initialise();
    }

    void addHazard(ArrayList fieldData){
        panelList.get(currentIndex).person.setText((String) fieldData.get(0));
        panelList.get(currentIndex).location.setText((String) fieldData.get(1));
        panelList.get(currentIndex).hazard.setText((String) fieldData.get(2));
        panelList.get(currentIndex).description.setText((String) fieldData.get(3));
        panelList.get(currentIndex).startDate.setText((String) fieldData.get(4));
        panelList.get(currentIndex).endDate.setText((String) fieldData.get(5));

        panelList.get(currentIndex).setToActive();
        currentIndex+=1;
        panelList.get(currentIndex).setToAdd();
    }

    private void removeHazard(){

    }

    JPanel getPanel(){
        return this.topPanel;
    }

    private void initialise(){
        for(int i=0;i<8;i++) {
            HazardPanel h = new HazardPanel();
            panelList.add(h);
            hPanel.add(h.getHazardPanel());
        }

        JPanel labelPanel = new JPanel(new GridLayout(2,0));
        labelPanel.setBorder(new MatteBorder(1,0,1,0,Color.BLACK));
        JPanel panel;
        for(int i=0;i<2;i++) {
            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
            panel.setBorder(new MatteBorder(0,1,1-i,0,Color.BLACK));
            label = new JLabel("Hazard");
            label.setPreferredSize(new Dimension(200, 40));
            panel.add(label);
            JPanel p = new JPanel();
            p.setPreferredSize(new Dimension(200, 250));
            panel.add(p);
            label = new JLabel("Description and RA");
            label.setPreferredSize(new Dimension(200, 40));
            panel.add(label);
            label = new JLabel("Responsible Person");
            label.setPreferredSize(new Dimension(200, 40));
            panel.add(label);
            label = new JLabel("Location");
            label.setPreferredSize(new Dimension(200, 40));
            panel.add(label);
            label = new JLabel("Dates Active");
            label.setPreferredSize(new Dimension(200, 40));
            panel.add(label);
            labelPanel.add(panel);
        }

        topPanel.add(labelPanel);
        topPanel.add(hPanel);

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
        JLabel icon = new JLabel();
        JLabel description = new JLabel();
        JLabel location = new JLabel(); //i.e. fume cupboard 1.2
        JLabel person = new JLabel();
        JPanel datePanel = new JPanel();
        JLabel startDate = new JLabel();
        JLabel endDate = new JLabel();

        HazardPanel(){
            //init panels
            inactivePanel.setPreferredSize(new Dimension(350,450));
            activePanel.setPreferredSize(new Dimension(350,450));
            addPanel.setPreferredSize(new Dimension(350,450));
            //activePanel.setLayout(new BoxLayout(activePanel,BoxLayout.PAGE_AXIS));
            activePanel.setLayout(new BorderLayout());
            addPanel.setLayout(new GridBagLayout());

            //activePanel components
            JPanel groupPanel = new JPanel(new GridLayout(4,0)); //change row
            groupPanel.setPreferredSize(new Dimension(350,160));
            hazard.setPreferredSize(new Dimension(350,40));
            hazard.setHorizontalAlignment(SwingConstants.CENTER);
            location.setHorizontalAlignment(SwingConstants.CENTER);
            person.setHorizontalAlignment(SwingConstants.CENTER);
            description.setHorizontalAlignment(SwingConstants.CENTER);
            startDate.setHorizontalAlignment(SwingConstants.CENTER);
            endDate.setHorizontalAlignment(SwingConstants.CENTER);
            icon.setHorizontalAlignment(SwingConstants.CENTER);
            ImageIcon im = new ImageIcon("src/res/icon.png");
            icon.setIcon(im);
            datePanel.setLayout(new GridLayout());
            datePanel.add(startDate);
            datePanel.add(endDate);
            //adding components to activePanel
            groupPanel.add(description);
            groupPanel.add(person);
            groupPanel.add(location);
            groupPanel.add(datePanel);
            activePanel.add(groupPanel,BorderLayout.PAGE_END);
            activePanel.add(hazard,BorderLayout.PAGE_START);
            activePanel.add(icon,BorderLayout.CENTER);


            //adding components to addPanel
            addBtn.addActionListener(e -> CreationDialog.showDialog());
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
