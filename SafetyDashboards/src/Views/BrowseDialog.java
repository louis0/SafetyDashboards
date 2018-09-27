import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Map;
import java.util.Set;

class BrowseDialog extends JPanel {

    JFrame frame;
    private JLabel hazardLabel;
    private JList list;
    private String currentString;
    Map m;
    JLabel label;
    JPanel panel;

    Color PURP = new Color(112,111,211);
    Color ORANGE = new Color(255, 177, 66);

    private BrowseDialog(JFrame frame){
        m = SQLHelper.getQueryResult();
        //this.frame = frame;
        //frame.setPreferredSize(new Dimension(600,600));
        //frame.setBackground(Color.GREEN);
        frame.setLayout(new FlowLayout());

        DefaultListModel listModel = new DefaultListModel();
        Set<String> keys = m.keySet(); //nullpointer check refereneces - if not connected to sql

        for ( String key : keys){
            listModel.addElement(key);
        }

        list = new JList(listModel);


        list.clearSelection();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(0);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(375,525));
        listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        listScroller.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.LIGHT_GRAY));
        list.addListSelectionListener(e -> {
            currentString = (String) list.getSelectedValue();
            hazardLabel.setText((String) m.get(currentString));
        });

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.PAGE_AXIS));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(375,50));

        label = new JLabel("Hazardous Material:", SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(375,50));
        label.setBackground(PURP);
        label.setOpaque(true);
        panel.add(label);
        leftPanel.add(panel);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(375,525));
        panel.add(listScroller);
        leftPanel.add(panel);
        frame.add(leftPanel);


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.PAGE_AXIS));

        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(175,525));

        label = new JLabel("Active Hazards:", SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(175,50));
        label.setBackground(PURP);
        label.setOpaque(true);
        p1.add(label);

        hazardLabel = new JLabel();     //only one hazard listed currently
        hazardLabel.setPreferredSize(new Dimension(175,475));
        p1.add(hazardLabel);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.setPreferredSize(new Dimension(175,50));
        //p2.setBorder(new EmptyBorder(0,0,25,25));
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> frame.dispose());
        p2.add(button);
        button = new JButton("OK");
        button.addActionListener(e -> {
            Controller.submitBrowse(currentString,(String) m.get(currentString));
            frame.dispose();
            CreationDialog.frame.toFront();
        });
        p2.add(button);
        rightPanel.add(p1);
        rightPanel.add(p2);


        /*
        panel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(20,10,20,20,Color.LIGHT_GRAY),
                BorderFactory.createLineBorder(Color.BLACK,1)));
                */
        frame.setBackground(Color.WHITE);
        frame.add(rightPanel);
    }



    static void showDialog(){
        JFrame frame = new JFrame("Choose Hazard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BrowseDialog pane = new BrowseDialog(frame);
        pane.setOpaque(true);
        //pane.setBorder(BorderFactory.createMatteBorder(20,20,20,20,Color.LIGHT_GRAY));
        frame.pack();
        frame.setVisible(true);
    }
}
