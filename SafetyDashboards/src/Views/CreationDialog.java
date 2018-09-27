import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;


//TODO CLEAR FIELDDATA
class CreationDialog extends Dialog {

    private JComboBox startYear, startMonth, startDay, endYear, endMonth, endDay;
    static JTextField hazardText, personText, locationText;
    private JTextArea descText;

    private Calendar calendar = Calendar.getInstance();

    private JPanel parentPanel = new JPanel();
    private JLabel label;

    static JFrame frame;

    Color PURPLE = new Color(112,111,211);
    private ArrayList<String> fieldData = new ArrayList<>(Arrays.asList("","","","","",""));

    private CreationDialog(JFrame frame){

        parentPanel.setLayout(new BoxLayout(parentPanel,BoxLayout.PAGE_AXIS));

        JPanel datesPanel = new JPanel();
        TitledBorder border;
        border = BorderFactory.createTitledBorder("Active Dates of Hazard");
        border.setBorder(BorderFactory.createLineBorder(PURPLE));
        datesPanel.setBorder(border);
        JPanel datesInnerPanel = new JPanel(new GridLayout(2, 4, 10, 10));


        JLabel startDateLabel = new JLabel("Start Date:  ", SwingConstants.RIGHT);
        startYear = new JComboBox();
        for(int i=0; i<10; i++){
            startYear.addItem(Integer.toString( calendar.get(Calendar.YEAR) - i));
        }
        startYear.setSelectedItem(Calendar.YEAR);
        startMonth = new JComboBox();
        for(int i=0; i<12; i++){
            calendar.set(Calendar.MONTH,i);
            startMonth.addItem(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH));
        }
        calendar = Calendar.getInstance();
        startMonth.setSelectedIndex((calendar.get(Calendar.MONTH)));
        startDay = new JComboBox();
        populateDays("Start");
        startDay.setSelectedIndex(calendar.get(Calendar.DAY_OF_MONTH)-1);
        datesInnerPanel.add(startDateLabel);
        datesInnerPanel.add(startDay);
        datesInnerPanel.add(startMonth);
        datesInnerPanel.add(startYear);

        JLabel endDateLabel = new JLabel("End Date:  ", SwingConstants.RIGHT);
        endYear = new JComboBox();
        for(int i=0; i<10; i++){
            endYear.addItem(Integer.toString( calendar.get(Calendar.YEAR) - i));
        }
        endYear.setSelectedItem(calendar.get(Calendar.YEAR));
        endMonth = new JComboBox();
        for(int i=0; i<12; i++){
            calendar.set(Calendar.MONTH,i);
            endMonth.addItem(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.ENGLISH));
        }
        calendar = Calendar.getInstance();
        endMonth.setSelectedIndex((calendar.get(Calendar.MONTH)));
        endDay = new JComboBox();
        populateDays("End");
        endDay.setSelectedIndex(calendar.get(Calendar.DAY_OF_MONTH));
        datesInnerPanel.add(endDateLabel);
        datesInnerPanel.add(endDay);
        datesInnerPanel.add(endMonth);
        datesInnerPanel.add(endYear);
        datesPanel.add(datesInnerPanel);


        JPanel hazardPanel = new JPanel(new BorderLayout());
        border = BorderFactory.createTitledBorder("Hazard Information");
        border.setBorder(BorderFactory.createLineBorder(PURPLE));
        hazardPanel.setBorder(border);
        JPanel hazardInnerPanel = new JPanel(new BorderLayout());
        label = new JLabel("Hazard:");
        hazardInnerPanel.add(label,BorderLayout.PAGE_START);
        JPanel browsePanel = new JPanel(new BorderLayout());
        hazardText = new JTextField();
        browsePanel.add(hazardText,BorderLayout.CENTER);
        JButton button = new JButton("Browse");
        button.addActionListener(e -> BrowseDialog.showDialog());
        browsePanel.add(button, BorderLayout.LINE_END);
        hazardInnerPanel.add(browsePanel);
        JPanel descPanel = new JPanel(new BorderLayout());
        label = new JLabel("Description and RA:");
        descPanel.add(label,BorderLayout.PAGE_START);
        descText = new JTextArea();
        descText.setRows(2);
        descText.setBorder(hazardText.getBorder());
        descPanel.add(descText,BorderLayout.CENTER);
        hazardPanel.add(hazardInnerPanel,BorderLayout.PAGE_START);
        hazardPanel.add(descPanel,BorderLayout.PAGE_END);


        JPanel infoPanel = new JPanel(new BorderLayout());
        border = BorderFactory.createTitledBorder("Metadata");
        border.setBorder(BorderFactory.createLineBorder(PURPLE));
        infoPanel.setBorder(border);
        JPanel infoInnerPanel = new JPanel(new BorderLayout());
        label = new JLabel("Responsible Person:");
        infoInnerPanel.add(label,BorderLayout.PAGE_START);
        JPanel personPanel = new JPanel(new BorderLayout());
        personText = new JTextField();
        personPanel.add(personText,BorderLayout.CENTER);
        button = new JButton("Browse");
        //button.addActionListener(e -> BrowseDialog.showDialog()); //needs listener
        personPanel.add(button, BorderLayout.LINE_END);
        infoPanel.add(personPanel);
        JPanel locationPanel = new JPanel(new BorderLayout());
        label = new JLabel("Location:");
        locationPanel.add(label,BorderLayout.PAGE_START);
        JPanel locTextPanel = new JPanel(new BorderLayout());
        locationText = new JTextField();
        locTextPanel.add(locationText,BorderLayout.CENTER);
        button = new JButton("Browse");
        //button.addActionListener(e -> BrowseDialog.showDialog()); //needs listener
        locTextPanel.add(button, BorderLayout.LINE_END);
        locationPanel.add(locTextPanel);
        infoPanel.add(infoInnerPanel,BorderLayout.PAGE_START);
        infoPanel.add(locationPanel,BorderLayout.PAGE_END);


        parentPanel.add(infoPanel,BorderLayout.PAGE_START);
        parentPanel.add(hazardPanel,BorderLayout.CENTER);
        parentPanel.add(datesPanel,BorderLayout.PAGE_END);

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        button = new JButton("Cancel");
        button.addActionListener(e -> {
            personText.setText("");
            locationText.setText("");
            hazardText.setText("");
            descText.setText("");
            Controller.updateView("Main");
        } );
        buttonPanel.add(button);
        button = new JButton("Submit");
        button.setBackground(PURPLE);
        button.addActionListener(e -> submit());
        buttonPanel.add(button);


        topPanel.add(parentPanel,BorderLayout.CENTER);
        topPanel.add(buttonPanel,BorderLayout.PAGE_END);
        frame.add(topPanel);
    }

    private void populateDays(String s){
        Calendar currentMonth = Calendar.getInstance();
        switch (s){
            case "Start":
                startDay.removeAllItems();
                currentMonth.set(Calendar.MONTH,startMonth.getSelectedIndex());
                for(int i=1; i<=currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
                    startDay.addItem(Integer.toString(i));
                }
                break;
            case "End":
                endDay.removeAllItems();
                currentMonth.set(Calendar.MONTH,endMonth.getSelectedIndex());
                for(int i=1; i<=currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
                    endDay.addItem(Integer.toString(i));
                }
                break;
        }
    }

    private void submit(){
        fieldData.set(0,personText.getText());
        fieldData.set(1,locationText.getText());
        fieldData.set(2,hazardText.getText());
        fieldData.set(3,descText.getText());
        fieldData.set(4,startDay.getSelectedItem()+" "+startMonth.getSelectedItem()+" "+startYear.getSelectedItem());
        fieldData.set(5,endDay.getSelectedItem()+" "+endMonth.getSelectedItem()+" "+endYear.getSelectedItem());
        Controller.addHazard(fieldData);
        frame.dispose();
    }

    static void showDialog(){
        frame = new JFrame("New Hazard");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CreationDialog pane = new CreationDialog(frame);
        pane.setOpaque(true);

        frame.pack();
        frame.setVisible(true);
    }
}
