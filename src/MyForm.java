import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class MyForm extends JFrame {
    private static JFrame frame;
    private static JFrame secFrame;
    private static List<Integer> mass;

    public static void intro(Container pane) {

        pane.setLayout(null);
        JLabel label = new JLabel("How many numbers to display?");
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);

        label.setBounds(300, 220, 200, 30);
        pane.add(label);

        JTextField text = new JTextField(4);
        text.setBounds(360, 260, 80, 30);
        pane.add(text);

        JButton btn = new JButton("Button");
        btn.setBounds(360, 300, 80, 30);
        pane.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer.parseInt(text.getText());
                    createAndShowGUI(Integer.parseInt(text.getText()));
                } catch (NumberFormatException exp) {
                    JOptionPane.showMessageDialog(null, "You need write number, not word");
                }
                frame.dispose();
            }
        });


    }

    public static void addComponentsToPane(Container pane, int num) {

        ArrayList<JButton> col = new ArrayList<>();

        mass = new ArrayList<Integer>();

        int randPosition = (int) (Math.random() * mass.size());
        int randNum = (int) (Math.random() * 30);

        for (int i = 0; i < num; i++) {
            mass.add((int) (Math.random() * 1000));
            col.add(new JButton());
            col.get(i).setText(Integer.toString(mass.get(i)));
        }

        mass.set(randPosition, randNum);
        System.out.println(mass.get(randPosition));
        col.get(randPosition).setText(String.valueOf(mass.get(randPosition)));

        col.forEach(j -> j.setForeground(Color.WHITE));
        col.forEach(j -> j.setOpaque(true));
        col.forEach(j -> j.setBackground(Color.BLUE));
        col.forEach(j -> j.setHorizontalAlignment(JLabel.CENTER));

        //Sorting integers
        mass = mass.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < col.size(); i++) {
            col.get(i).setBounds(25 + 70 * (i / 10), 15 + 30 * (i % 10), 60, 20);
            pane.add(col.get(i));
            col.get(i).setName(String.valueOf(i));
            col.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int num = Integer.parseInt(((JButton) e.getSource()).getText());
                    if (num <= 30) {
                        secFrame.dispose();
                        createAndShowGUI(num);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a value smaller or equal to 30");
                    }
                }
            });
        }
        JButton btn = new JButton("Sort");
        btn.setFont(new Font("Dialog", Font.PLAIN, 10));
        btn.setBounds(730, 20, 60, 30);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.reverse(mass);
                for (int i = 0; i < col.size(); i++) {
                    col.get(i).setText(String.valueOf(mass.get(i)));
                }
            }
        });
        pane.add(btn);

        JButton reset = new JButton("Reset");
        reset.setFont(new Font("Dialog", Font.PLAIN, 10));
        reset.setBounds(730, 60, 60, 30);

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secFrame.dispose();
                createAndStartIntro();
            }
        });
        pane.add(reset);
        ////////////////////////
        pane.setLayout(null);


    }

    public static void createAndStartIntro() {
        frame = new JFrame("Intro");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        intro(frame.getContentPane());
        Insets insets = frame.getInsets();

        frame.setSize(800 + insets.left + insets.right,
                600 + insets.top + insets.bottom);
        frame.setVisible(true);
    }

    private static void createAndShowGUI(int num) {
        if (num == 0) {
            JOptionPane.showMessageDialog(null, "You can`t chose zero");
        } else {
            //Create and set up the window.
            secFrame = new JFrame("Random numbers");

            secFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Set up the content pane.
            addComponentsToPane(secFrame.getContentPane(), num);

            //Size and display the window.
            Insets insets = secFrame.getInsets();

            secFrame.setSize(800 + insets.left + insets.right,
                    600 + insets.top + insets.bottom);
            secFrame.setResizable(false);
            secFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndStartIntro();
            }
        });
    }
}
