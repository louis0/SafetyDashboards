import javax.sql.rowset.BaseRowSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TimerTask;

class CreationView {

    private JPanel parentPanel = new JPanel();
    private JPanel topPanel = new JPanel(new GridLayout(1, 2));
    private JPanel formPanel = new JPanel();
    private JPanel previewPanel = new JPanel(new GridBagLayout());

    private JPanel bottomPanel = new JPanel(new GridLayout(1,2));

    private JTextField personText;
    static JTextField hazardText;
    private JTextField startDate;
    private JTextField endDate;
    private JTextField descText;
    private JTextField locText;
    private Timer timer;

    private ArrayList<String> fieldData;

    CreationView(){
        parentPanel.setLayout(new BoxLayout(parentPanel,BoxLayout.PAGE_AXIS));
        formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.PAGE_AXIS));
        topPanel.add(formPanel);
        topPanel.add(previewPanel);
        initialise();
        //parentPanel.add(new JPanel());
        //parentPanel.add(submitPanel);
    }

    private void initialise(){
        //Person
        JLabel label = new JLabel("Responsible Person:");
        label.setOpaque(true);
        formPanel.add(label);
        personText = new JTextField();
        formPanel.add(personText);

        //Hazard
        label = new JLabel("Hazard:");
        formPanel.add(label);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hazardText = new JTextField();
        hazardText.setPreferredSize(new Dimension(700,25));
        panel.add(hazardText);
        JButton button = new JButton("Browse");
        button.setPreferredSize(new Dimension(80,25));
        button.addActionListener(e -> BrowseDialog.showDialog());
        panel.add(button);
        formPanel.add(panel);

        //Location
        label = new JLabel("Location:");
        formPanel.add(label);
        locText = new JTextField();
        formPanel.add(locText);

        //Dates
        panel = new JPanel(new FlowLayout());
        label = new JLabel("Start Date:");
        panel.add(label);
        label = new JLabel("End Date:");
        panel.add(label);
        formPanel.add(panel);

        panel = new JPanel(new FlowLayout());
        startDate = new JTextField();
        endDate = new JTextField();
        startDate.setPreferredSize(new Dimension(200,25));
        endDate.setPreferredSize(new Dimension(200,25));
        panel.add(startDate);
        panel.add(endDate);
        formPanel.add(panel);

        //Desc
        label = new JLabel("Description:");
        formPanel.add(label);
        descText = new JTextField();
        formPanel.add(descText);

        //meta panels
        topPanel.add(formPanel);
        topPanel.add(previewPanel);
        topPanel.setBackground(Color.white);

        bottomPanel.add(new JPanel());
        panel = new JPanel(new FlowLayout());
        button = new JButton("Cancel");
        button.addActionListener(e -> {
            personText.setText("");
            hazardText.setText("");
            startDate.setText("");
            endDate.setText("");
            descText.setText("");
            locText.setText("");
            Controller.updateView("Main");
        });

        panel.add(button);
        button = new JButton("OK");
        button.addActionListener(e -> {
            submit();
            personText.setText("");
            hazardText.setText("");
            startDate.setText("");
            endDate.setText("");
            descText.setText("");
            locText.setText("");
            Controller.updateView("Main");
        });
        panel.add(button);
        bottomPanel.add(panel);

        parentPanel.add(topPanel);
        parentPanel.add(bottomPanel);

        fieldData = new ArrayList<>();
        fieldData.add(personText.getText());
        fieldData.add(hazardText.getText());
        fieldData.add(startDate.getText());
        fieldData.add(endDate.getText());
        fieldData.add(descText.getText());
        fieldData.add(locText.getText());

        updatePreview();
    }

    void updatePreview(){

        timer = new Timer(3000, e -> {
            fieldData.set(0,personText.getText());
            fieldData.set(0,hazardText.getText());
            fieldData.set(0,startDate.getText());
            fieldData.set(0,endDate.getText());
            fieldData.set(0,descText.getText());
            fieldData.set(0,locText.getText());
            Controller.addHazard(fieldData);
            MainView.getHazardPanel();

        });

        timer.start();
    }

    /*
    void updateView(){
        hazardText.setText("a");
    }
    */

    private void submit(){ //need thing in controller, or model?
        timer.stop();
        fieldData.set(0,personText.getText());
        fieldData.set(0,hazardText.getText());
        fieldData.set(0,startDate.getText());
        fieldData.set(0,endDate.getText());
        fieldData.set(0,descText.getText());
        fieldData.set(0,locText.getText());

        Controller.addHazard(fieldData);
    }

    JPanel getPanel(){
        return this.parentPanel;
    }
}

