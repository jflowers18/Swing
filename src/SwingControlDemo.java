import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.*;

public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta;
    private int WIDTH=800;
    private int HEIGHT=700;

    //add new fields
    private JLabel urllabel;
    private JLabel searchlabel;
    private JTextField urltext;
    private JTextField searchtext;




    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(4, 2));
        //see what the flow layout does
        //mainFrame.setLayout(new FlowLayout());

        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);


        //add new fields
        urllabel = new JLabel("Url:");
        searchlabel = new JLabel("Search Term");
        urltext = new JTextField();
        searchtext = new JTextField();
        mainFrame.add(urllabel);
        mainFrame.add(urltext);
        mainFrame.add(searchlabel);
        mainFrame.add(searchtext);

        ta = new JTextArea();
        ta.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        mainFrame.add(mb);
        mainFrame.add(ta);
        mainFrame.setJMenuBar(mb);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

       // mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);


    }

    private void showEventDemo() {
        headerLabel.setText("Control in action: Button");

        //change the name of the buttom to read milton
        JButton okButton = new JButton("Read URL");
        //JButton submitButton = new JButton("Submit");
        //JButton cancelButton = new JButton("Cancel");

        okButton.setActionCommand("read");
       // submitButton.setActionCommand("Submit");
      //  cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
       // submitButton.addActionListener(new ButtonClickListener());
       // cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton);
       // controlPanel.add(submitButton);
       // controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            //if they click readmilton them it calls the htmlread class
            if (command.equals("read")) {
                statusLabel.setText("Reading Milton Webpage");
                //call the static method main
                HtmlRead();
            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
    private void HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL(urltext.getText());//new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {

                System.out.println(line);
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }
}