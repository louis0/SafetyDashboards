import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hazard extends JFrame {

    private String hazardType;
    private String hazardIcon;
    private String responsiblePerson;
    private String chemical;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private JPanel p  = new JPanel();
    private ArrayList<JLabel> labelList = new ArrayList<>();

    public Hazard(String responsiblePerson, String chemical, LocalDateTime endDate, String location){
        this.responsiblePerson = responsiblePerson;
        this.chemical = chemical;
        this.endDate = endDate;
        this.location = location;
        initPanel();

    }

    private void initPanel(){
        p.setPreferredSize(new Dimension(400, 450));
        JLabel icon = new JLabel();
        icon.setPreferredSize(new Dimension(300,300));
        icon.setIcon(new ImageIcon("src/res/icon.png"));
        this.p.add(icon);
        for(int i=0; i<5; i++){
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(400,30));
            label.setText("a");
            this.p.add(label);
            labelList.add(label);
        }
    }

    public String getHazardType() {
        return hazardType;
    }

    public void setHazardType(String hazardType) {
        this.hazardType = hazardType;
    }

    public void setStartDate() {
        LocalDateTime currentDT = LocalDateTime.now();
        this.startDate = currentDT;
    }

    public JPanel getPanel(){
        return this.p;
    }
}
