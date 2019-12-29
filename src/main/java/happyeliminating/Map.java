package happyeliminating;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Color;


public class Map extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Map();
	}

	/**
	 * Create the frame.
	 */
	public Map() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(812, 517);
		this.setResizable(true);
		this.setTitle("Happy Elimating");
		
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/3, Toolkit.getDefaultToolkit().getScreenSize().height/3);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("\u5F00\u59CB\u6E38\u620F");
		button.setBounds(14, 13, 113, 27);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u7ED3\u675F\u6E38\u620F");
		button_1.setBounds(141, 13, 113, 27);
		getContentPane().add(button_1);
		
		JLabel label = new JLabel("\u5206\u6570\uFF1A");
		label.setBounds(328, 17, 51, 18);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(373, 14, 213, 24);
		textField.setColumns(10);
		getContentPane().add(textField);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 48, 794, 422);
		
		panel.setLayout(new GridLayout(8, 8, 1, 1));
		
//		JButton btnNewButton = new JButton("New button");
//		panel.add(btnNewButton);
		
		JButton[] buttons = new JButton[64];
		for(int i = 0; i < buttons.length ; ++ i) {
			buttons[i] = new JButton("Hello");
			panel.add(buttons[i]);
		}
		getContentPane().add(panel);
		panel.updateUI();
		this.setVisible(true);
	}
}
