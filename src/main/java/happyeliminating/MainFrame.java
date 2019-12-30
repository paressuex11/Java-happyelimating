package happyeliminating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {

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
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("Happy Eliminating");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();


		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("开始游戏");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("结束游戏");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				
				Container root_panel = source.getParent();
				
				System.out.print(root_panel.getComponentCount());
			}
		});;
			
		JLabel label = new JLabel("\u5206\u6570\uFF1A");
		panel.add(label);
		
		txtAaa = new JTextField();
		txtAaa.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAaa.setText("0");
		panel.add(txtAaa);
		txtAaa.setColumns(30);
		
		GameMap panel_1 = new GameMap();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		panel_1.RandomColor();
		panel_1.printMap();
		panel_1.checkMap();
		

	}


}
