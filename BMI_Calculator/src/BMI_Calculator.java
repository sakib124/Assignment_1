import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;


public class BMI_Calculator {

    private JFrame frame;
    private double BMI;
    private JTextField txtWeight;
    private JTextField txtHeight;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BMI_Calculator window = new BMI_Calculator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public BMI_Calculator() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 200, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.cyan);
        frame.getContentPane().setLayout(null);

        txtWeight = new JTextField();
        txtWeight.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != 8 && c != 46) {
                    e.consume();
                }
            }
        });
        txtWeight.setBounds(100, 70, 50, 30);
        frame.getContentPane().add(txtWeight);
        txtWeight.setColumns(10);

        txtHeight = new JTextField();
        txtHeight.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != 8 && c != 46) {
                    e.consume();
                }
            }
        });
        
        txtHeight.setBounds(100, 150, 50, 30);
        frame.getContentPane().add(txtHeight);
        txtHeight.setColumns(10);

        JLabel lblWeight = new JLabel("Weight (kg):");
        lblWeight.setBounds(20, 70, 136, 16);
        frame.getContentPane().add(lblWeight);

        JLabel lblHeight = new JLabel("Height (m):");
        lblHeight.setBounds(20, 150, 88, 16);
        frame.getContentPane().add(lblHeight);

        JButton btnCompute = new JButton("Compute BMI");
        btnCompute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double weight = Double.parseDouble(txtWeight.getText());
                double height = Double.parseDouble(txtHeight.getText());
                EchoClient client = new EchoClient(weight, height);

                try {
                    client.startClient();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                BMI = client.getBMI();


                JOptionPane.showMessageDialog(null, "This client's BMI is: " + BMI, "Results", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        btnCompute.setBounds(30, 220, 120, 30);
        frame.getContentPane().add(btnCompute);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnExit.setBounds(30, 280, 120, 30);
        frame.getContentPane().add(btnExit);
    }
}