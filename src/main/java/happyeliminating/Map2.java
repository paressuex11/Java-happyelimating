package happyeliminating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class Map2 extends JFrame {

	private JPanel contentPane;
	private JTextField txtAaa;
	public static Color[] colors = new Color[] {Color.red, Color.green, Color.blue, Color.yellow};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map2 frame = new Map2();
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
	public Map2() {
		setTitle("Map2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("开始游戏");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("结束游戏");
		panel.add(btnNewButton_1);
		
		JLabel label = new JLabel("\u5206\u6570\uFF1A");
		panel.add(label);
		
		txtAaa = new JTextField();
		txtAaa.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAaa.setText("0");
		panel.add(txtAaa);
		txtAaa.setColumns(30);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(10, 10, 0, 0));
		
		JButton []buttons = new JButton[100];
		
		for (int i = 0; i < 100; ++ i) {
			buttons[i] = new JButton();
			buttons[i].setBackground(colors[(int) Math.floor(Math.random()*4)]);
			panel_1.add(buttons[i]);
		}
	}

}
