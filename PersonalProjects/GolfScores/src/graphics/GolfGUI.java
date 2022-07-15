package graphics;

import domains.GolfGame;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static utils.Variables.*;

/**
 *
 * @author Randal Obringer
 */
public class GolfGUI extends JPanel implements ActionListener{

    private Button birdieBtn,parBtn,scoreBtn; //buttons
    private JLabel output; //holds output to answers from button presses from the game object
    private int type=-1; //a flag used for button press actions
    private GolfGame game; //create the golf game
    private File file; //create the file
    private JComboBox holeList;
    private String carryUpdate;
    
    public GolfGUI(){ //constructor
        carryUpdate="";
        file = new File("src/res/data.txt"); //instantiate the file. src is the src package in Netbeans where source code is stored. res is the package that the file resides in.
        game = new GolfGame(file); //instatiate the game from the file
        holeList = new JComboBox(game.getHoleNumbers());
        //game.saveGolfGame(file);
        type=-1; //ensure type is -1 before computing it later
        output = new JLabel("Test"); //produce something on the window for output test
        output.setBounds(120,50,150,150); //still need to test if we can move. 
        output.setForeground(new Color(255,255,0)); //set color of text
        output.setFont(new Font("Times New Roman",Font.BOLD,32)); //set type and size of text
        birdieBtn=new Button("# of Birdies");
        parBtn=new Button("# of Pars");
        scoreBtn=new Button("Overall Score");
        birdieBtn.addActionListener(this); //create listeners so we can execute an action when the button is pressed
        parBtn.addActionListener(this);
        scoreBtn.addActionListener(this);
        birdieBtn.setBounds(10,20,100,50);
        parBtn.setBounds(120,20,100,50);
        scoreBtn.setBounds(230,20,100,50);
        birdieBtn.setForeground(new Color(255,0,0)); //set color of text
        parBtn.setForeground(new Color(0,255,0));
        scoreBtn.setForeground(new Color(0,0,255));

        birdieBtn.setFont(new Font("Times New Roman",Font.BOLD,32)); //set font type and size
        parBtn.setFont(new Font("Times New Roman",Font.BOLD,32));
        scoreBtn.setFont(new Font("Times New Roman",Font.BOLD,32));
        
        holeList.setSelectedIndex(0);
        holeList.addActionListener(this);
        add(holeList);
        add(birdieBtn); //add the elements to the panel (window)
        add(parBtn);
        add(scoreBtn);
        add(output);
        setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT)); //force window size and height
        setBackground(Color.BLUE);//force window color
    }
    
    //
    public void paintComponent(Graphics g){
        String temp="";
        super.paintComponent(g); //since we are extending JPanel class we want to ensure we call whatever the original class does first
        if(type==BIRDIE_BTN){ //set Variables.java if the type is changed from pressing a button and it equals the ID of the birdie button
            temp=Integer.toString(game.countBirdies()); //set temp to count total birdies
        }else if(type==PAR_BTN){ //etc.
            temp=Integer.toString(game.countPars());
        }else if(type==SCORE_BTN){
            if(game.getGameFinal()==OVER_PAR){ //see Variables.java, if result is equal to ID for over par
                temp="Over Par"; //set temp to output that is is over par
            }else if(game.getGameFinal()==UNDER_PAR){ //etc.
                temp="Under Par";
            }else if(game.getGameFinal()==PAR){
                temp="Par";
            }
        }else{//otherwise the menu was picked and we need to retrieve the value from carryUpdate 
            temp=carryUpdate;
        }
        output.setText(temp); //finally update the output to the window with our results above
    }

    //we are implementing ActionListener class by implementing its function here
    @Override //it is an abstract method in the parent function so we must override it to call our method instead of the original
    public void actionPerformed(ActionEvent er) {
        if(er.getSource().getClass()==JComboBox.class){ //getSource returns the objects stored in the ActionEvent. If we clicked the combobox
            JComboBox ref; //create a reference combobox
            ref=(JComboBox)er.getSource();
            String hole = (String)ref.getSelectedItem(); //store the item that was selected
            if(hole!="-"){ //if the user hasn't picked an item in the menu yet.
                hole=game.getSingleHoleScore(Integer.parseInt(hole)-1); //change the hole number to match the array indexing so we can pull the correct score
            }
            carryUpdate=hole; //carry the variable to the paintComponent function
            type=-1; //ensure type is not birdieBtn, parBtn, or scoreBtn
        }else if(er.getSource().getClass()==Button.class){
            Button ref; //create a reference button
            ref=(Button)er.getSource();
            if(ref==birdieBtn){ //if the birdie button was pressed
                type=BIRDIE_BTN; //we will perform birdie computation inside paintComponent using our type flag to carry that data for us
            }else if(ref==parBtn){ //etc.
                type=PAR_BTN;
            }else if(ref==scoreBtn){
                type=SCORE_BTN;
            }
        }
        repaint(); //repaint calls our paintComponent method for us...
    }
    
}
