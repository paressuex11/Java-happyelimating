package happyeliminatingv2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		setTitle("Login");
		setBounds(480,270,960,540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println(this.getSize());
		System.out.println(this.getLocation());
		//label
		JLabel label = new JLabel("\u5F00\u5FC3\u6D88\u6D88\u4E50");
		label.setBounds(0, 0, getWidth()-5, 58);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.CYAN);
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 50));
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(299, 132, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(299, 180, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(376, 129, 199, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(376, 177, 199, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.setBounds(317, 256, 113, 27);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Sign In");
		btnNewButton_1.setBounds(462, 256, 113, 27);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
	}
	public int getHeight() {return getSize().height;}
	public int getWidth() {return getSize().width;}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText() == "Sign In") {
			dispose();
			new GameFrame();
		}
	}
}
