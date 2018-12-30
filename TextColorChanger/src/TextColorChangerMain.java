import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by jlowrya on 24/10/2016.
 */
public class TextColorChangerMain {
    public static void main(String[] args) {
        NewFrame frame = new NewFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible( true );
    }

    static class NewFrame extends JFrame{
        //buttons to reset textfield and submit color vals to program
        JButton reset;
        JButton submit;
        //text fields for the user to input color values
        JTextField red;
        JTextField green;
        JTextField blue;
        //int for setting size of frame
        int size;
        //Color for the color of the message
        Color col;
        //Message to display appropriate value dependent on situation
        String message;

        public NewFrame()
        {
            size = 600;
            reset = new JButton("Reset");
            submit = new JButton("Submit");
            message = "CE203 Assignment 1, submitted by: James Lowry 1502558";
            red = new JTextField("Red",10);
            green = new JTextField("Green",10);
            blue = new JTextField("Blue",10);
            col = Color.red;

            JPanel northPanel = new JPanel();
            MessagePanel centerPanel = new MessagePanel(this);
            JPanel southPanel = new JPanel();

            reset.addActionListener(new ButtonHandler(this));
            submit.addActionListener(new ButtonHandler(this));


            northPanel.add(reset);
            southPanel.add(red);
            southPanel.add(green);
            southPanel.add(blue);
            southPanel.add(submit);

            add(northPanel, BorderLayout.NORTH);
            add(centerPanel, BorderLayout.CENTER);
            add(southPanel, BorderLayout.SOUTH);

            setSize(size,size);
        }
    }

    static class MessagePanel extends JPanel{
        NewFrame theApp;

        MessagePanel(NewFrame app){
            theApp = app;
        }

        public void paintComponent(Graphics g){
            g.setColor(theApp.col); //set color to one set by user
            g.setFont(new Font("Serif", Font.PLAIN, 20)); //set font to custom one, larger than default
            g.drawString(theApp.message, 50, theApp.size/2); //put message on screen, normal or error
        }
    }

    static class ButtonHandler implements ActionListener
    {
        NewFrame theApp;
        ButtonHandler(NewFrame app){
            theApp = app;
        }

        public void actionPerformed(ActionEvent act){
            //if user tries to enter color values
            if(act.getSource()==theApp.submit)
            {
                try{
                    //get input from textfields
                    int r = Integer.parseInt(theApp.red.getText());
                    int g = Integer.parseInt(theApp.green.getText());
                    int b = Integer.parseInt(theApp.blue.getText());

                    //make sure each is a valid color value, make negatives default to 200, and nums > 250 default to 250
                    if(r < 0){
                        r = 200;
                        //change the contents of textfields to indicate new value
                        theApp.red.setText("200");
                    }
                    else if(r>250)
                        r = 250;
                    if(g < 0) {
                        g = 200;
                        //change the contents of textfields to indicate new value
                        theApp.green.setText("200");
                    }
                    else if(g>250)
                        g = 250;
                    if(b < 0) {
                        b = 200;
                        //change the contents of textfields to indicate new value
                        theApp.blue.setText("200");
                    }
                    else if(b>250)
                        b = 250;

                    //make col new color with values submitted by user
                    theApp.col = new Color(r, g, b);
                    theApp.message = "James Lowry 1502558";
                }
                //if the user has entered invalid input, catch exception and display error message
                catch (Exception e){
                    //make error message red so user is sure to see it, in case text was extremely light color before error occurred
                    theApp.col = Color.red;
                    theApp.message = "Please only enter valid integer values between 0 - 250, inclusive.";
                    theApp.red.setText("Red");
                    theApp.green.setText("Green");
                    theApp.blue.setText("Blue");
                }
            }
            //if button pressed is reset button
            else {
                theApp.col = Color.red;
                theApp.message = "CE203 Assignment 1, submitted by: James Lowry 1502558";
                theApp.red.setText("Red");
                theApp.green.setText("Green");
                theApp.blue.setText("Blue");
            }
            theApp.repaint();
        }
    }


}