import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Base64;

class LoginView {

    private JPanel panel;
    private JPanel loginPanel;
    private JLabel label;
    private JTextField userText, passText;

    LoginView(){
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(1600,900));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        initialise();
    }

    private void initialise(){
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.PAGE_AXIS));
        loginPanel.setBorder(new LineBorder(Color.BLACK,1));
        loginPanel.setBackground(Color.LIGHT_GRAY);
        loginPanel.setPreferredSize(new Dimension(600,600));

        JPanel icon = new JPanel();
        icon.setPreferredSize(new Dimension(250,250));
        icon.setBorder(new MatteBorder(50,175,50,175,Color.LIGHT_GRAY));
        icon.setBackground(Color.blue);
        loginPanel.add(icon);

        JPanel userPanel = new JPanel();
        label = new JLabel("Username");
        userText = new JTextField();
        userText.setPreferredSize(new Dimension(200,25));
        userPanel.add(label);
        userPanel.add(userText);
        userPanel.setBackground(Color.LIGHT_GRAY);
        loginPanel.add(userPanel);

        JPanel passPanel = new JPanel();
        label = new JLabel("Password");
        passText = new JTextField();
        passText.setPreferredSize(new Dimension(200,25));
        passPanel.add(label);
        passPanel.add(passText);
        passPanel.setBackground(Color.LIGHT_GRAY);
        loginPanel.add(passPanel);

        JButton submit = new JButton("Login");
        submit.addActionListener(e -> submit());
        submit.setBorder(new MatteBorder(0,0,50,0,Color.LIGHT_GRAY));
        loginPanel.add(submit);

        panel.add(loginPanel);
    }

    private void submit(){
        /*
        String salt = Hasher.genSalt();
        String hashed = Hasher.hash(passText.getText(),salt.getBytes());
        String ADDUSER = "INSERT INTO [Training].[dbo].[sdbUsers] VALUES(" +
                "'" +userText.getText()+ "','"  +hashed+"','"+salt+"');" ;
        SQLHelper.insert(ADDUSER);
        */

        //if user is in table, check ptpass + salt is same as hashed pass

        ArrayList<ArrayList<String>> results = SQLHelper.genericSel("SELECT TOP 1 [username],[password],[salt] FROM [Training].[dbo].[sdbUsers] WHERE username = '"+userText.getText()+"'");
        if(results.size() > 0){
            
            byte[] decoded = Base64.getDecoder().decode(results.get(0).get(2));
            String hashed = Hasher.hash(passText.getText(),decoded);
            if(hashed==results.get(0).get(1)){
                Controller.updateView("Main");
            } else {
                System.out.println("wrong pass");
            }
        } else {
            System.out.println("wrong user");
        }



    }

    JPanel getPanel(){
        return this.panel;
    }
}
