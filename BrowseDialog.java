import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Map;
import java.util.Set;

public class BrowseDialog extends JPanel {

    JFrame frame;

    private JLabel hazardLabel;
    private JList list;
    private String currentString;



    Map m;
    private BrowseDialog(JFrame frame){
        m = SQLHelper.getQueryResult();
        //this.frame = frame;
        //frame.setPreferredSize(new Dimension(600,600));
        frame.setBackground(Color.GREEN);
        frame.setLayout(new BoxLayout(frame,BoxLayout.PAGE_AXIS));

        DefaultListModel listModel = new DefaultListModel();
              //nullpointer check refereneces - if not connected to sql
        Set<String> keys = m.keySet();

        for ( String key : keys){
            listModel.addElement(key);
        }



        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");


        list = new JList(listModel);

        list.clearSelection();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(0);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250,500));
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        listScroller.setBorder(BorderFactory.createMatteBorder(20,20,20,10,Color.LIGHT_GRAY));
        list.addListSelectionListener(e -> {
            currentString = (String) list.getSelectedValue();
            hazardLabel.setText((String) m.get(currentString));
        });


        frame.add(listScroller,BorderLayout.LINE_START);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Hazards:");
        panel.add(label);

        hazardLabel = new JLabel();
        panel.add(hazardLabel);


        panel.setPreferredSize(new Dimension(250,500));

        panel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(20,10,20,20,Color.LIGHT_GRAY),
                BorderFactory.createLineBorder(Color.BLACK,1)));

        frame.add(panel,BorderLayout.LINE_END);

        panel = new JPanel(new GridLayout());
        JButton button = new JButton("Cancel");
        button.addActionListener(e -> frame.dispose());
        panel.add(button);
        button = new JButton("OK");
        button.addActionListener(e -> {
            Controller.submitBrowse(currentString,(String) m.get(currentString));
            frame.dispose();
        });
        panel.add(button);
        frame.add(panel,BorderLayout.PAGE_END);

    }



    static void showDialog(){
        JFrame frame = new JFrame("Choose Hazard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BrowseDialog pane = new BrowseDialog(frame);
        pane.setOpaque(true);
        pane.setBorder(BorderFactory.createMatteBorder(20,20,20,20,Color.LIGHT_GRAY));
        frame.pack();
        frame.setVisible(true);
    }

}
