/*
 CardLayout cardLayout = (CardLayout) cards.getLayout();
 cardLayout.show(cards,"Main");


 frame.getContentPane().setSize(1600,900);
 frame.setLayout(cardLayout);
 frame.pack();
 frame.setVisible(true);





 //activeView.getContentPane().setSize(1600,900);

 //activeView.setVisible(true);
 //activeView.pack();
 active = new JPanel(new CardLayout());

 active.add(mainView.getFrame(),"Main");
 //active.add(creationView.getFrame(),"Creation");
 //activeView = mainView.getFrame();
 cardLayout.show(active,"Main");
 */
/*
import javax.swing.*;
import java.awt.*;

class CreationView {

    private JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    private JPanel previewPanel = new JPanel();
    JButton button;
    JLabel label;
    JTextField textbox;

    CreationView(){
        panel.setPreferredSize(new Dimension(1600,900));
        //panel.setBackground(Color.GREEN);
        initialise();
    }

    private void initialise(){
        c.weightx = 1;
        c.weighty = 1;

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;

        c.gridy = 0;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        panel.add(label,c);

        c.gridy = 1;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        panel.add(label,c);

        c.gridy = 2;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        panel.add(label,c);

        c.gridy = 3;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        panel.add(label,c);

        c.gridy = 4;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        panel.add(label,c);

        c.gridy = 5;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        panel.add(label,c);

        c.gridy = 6;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        panel.add(label,c);

        //c.weighty = 7;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridheight = 6;
        previewPanel.setBackground(Color.cyan);
        panel.add(previewPanel,c);

        /*
        //c.gridwidth = 1;
        //c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;

        button = new JButton("temp");
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(button, c);
        button = new JButton("Browse");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 3;
        panel.add(button, c);

        button = new JButton("Cancel");
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 7;
        panel.add(button, c);

        button = new JButton("Apply");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 7;
        panel.add(button, c);

        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(label, c);

        label = new JLabel("preview");
        label.setOpaque(true);
        label.setBackground(Color.GREEN);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.ipadx = 2;
        c.gridx = 3;
        c.gridy = 1;
        panel.add(label,c);

        c.ipady = 0;
        c.ipadx = 0;




        //c.anchor = GridBagConstraints.PAGE_END;
        */
/*
    }

    JPanel getPanel(){
        return this.panel;
    }
}
*/



/*
        panelList.get(0).addPanel.setVisible(true);

        for(int i=0;i<8;i++) {
            p = new JPanel();
            panelList.add(p);
            p.setPreferredSize(new Dimension(400, 450));
            p.setBackground(Color.LIGHT_GRAY);
            if(i==0) {
                p.setLayout(new GridBagLayout());
            }

            panel.add(p);
            /*
            if(i==0) {
                frame.getContentPane().remove(panelList.get(i));

                frame.getContentPane().add(hazard.getPanel(),0);
                frame.validate();
                frame.repaint();
            }
            //
        }
        */


    /*
    private JPanel parentPanel = new JPanel(new GridLayout(2,2));

    private JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    private JPanel previewPanel = new JPanel();
    JButton button;
    JLabel label;
    JTextField personText;
    static JTextField hazardText;
    JTextField startDateText;
    JTextField endDateText;
    JTextField descText;
    JButton addButton;

    CreationView(){

        parentPanel.add(formPanel);
        parentPanel.add(previewPanel);
        parentPanel.setPreferredSize(new Dimension(1600,900));
        previewPanel.setBackground(Color.cyan);
        initialise();
    }

    private void initialise(){
        c.weightx = 1;
        c.weighty = 1;

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;

        c.gridy = 0;
        label = new JLabel("Responsible Person");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        formPanel.add(label,c);

        c.gridx = 1;
        personText = new JTextField();
        formPanel.add(personText,c);
        c.gridx = 0;

        c.gridy = 1;



        label = new JLabel("Hazard");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        formPanel.add(label,c);


        c.gridy = 2;

        hazardText = new JTextField();
        formPanel.add(hazardText,c);
        c.gridx = 1;

        button = new JButton("Browse");
        button.addActionListener(e -> BrowseDialog.showDialog());
        formPanel.add(button,c);
        c.gridx = 0;



        c.gridy = 3;

        label = new JLabel("Start Date");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        formPanel.add(label,c);

        c.gridx = 1;
        label = new JLabel("End Date");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        formPanel.add(label,c);
        c.gridx = 0;

        c.gridy = 4;

        startDateText = new JTextField();
        formPanel.add(startDateText,c);
        c.gridx = 1;
        endDateText = new JTextField();
        formPanel.add(endDateText,c);
        c.gridx = 0;

        c.gridy = 5;

        label = new JLabel("Description");
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        formPanel.add(label,c);
        c.gridy = 6;
        descText = new JTextField();
        formPanel.add(descText,c);
        c.gridx = 1;
        addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            submit();
            personText.setText("");
            hazardText.setText("");
            startDateText.setText("");
            endDateText.setText("");
            descText.setText("");
            Controller.updateView("Main");
        });



        formPanel.add(addButton,c);



    }

    void updateView(){
        hazardText.setText("a");
    }

    void submit(){ //need thing in controller, or model?
        fieldData = new ArrayList<>();
        fieldData.add(personText.getText());
        fieldData.add(hazardText.getText());
        fieldData.add(startDateText.getText());
        fieldData.add(endDateText.getText());
        fieldData.add(descText.getText());

        Controller.addHazard(fieldData);
    }

    JPanel getPanel(){
        return this.parentPanel;
    }
}
*/


/*********
 //code for adding to db

        String hashed = Hasher.hash(passText.getText(),Hasher.genSalt());
                String ADDUSER = "INSERT INTO [Training].[dbo].[sdbUsers] VALUES(" +
                "'" +userText.getText()+ "','"  +hashed+   "');" ;
                SQLHelper.insert(ADDUSER);
 *****/
