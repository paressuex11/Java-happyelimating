package happyeliminatingv2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame {

	private JPanel contentPane;
	private GameMap map;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
//					try {
//						frame.map.elimate(frame.map.blocks[0][0]);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(240,70,1000,800);
		setResizable(false);
		setTitle("Happy Eliminating");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Score:");
		lblNewLabel.setBounds(832, 13, 58, 18);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0");
		textField.setBounds(894, 10, 86, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		map = new GameMap();
		
		contentPane.add(map);
		setVisible(true);

		
		
		System.out.println(map.getSize());
	}
}

class GameMap extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Block blocks[][];
	public int height = 8;
	public int width = 8;
	public GameMap() {
		this.setLayout(null);
		setVisible(true);
		this.setBounds(0, 0, 800, 760);
		init();
	}
	public void init(int width) {
		//一般用方矩阵
	}
	public void init() {
		//默认init
		int jb_width = this.getSize().width / width;
		int jb_height = this.getSize().height / height;
		blocks = new Block[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				blocks[i][j] = new Block("white", i, j, this);
				blocks[i][j].addMouseListener(this);
			}
		}
		geneVaildMap();
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				blocks[i][j].syncColor();
				blocks[i][j].setBounds(i*jb_width,j*jb_height,jb_width,jb_height);
				blocks[i][j].setVisible(true);
				this.add(blocks[i][j]);
			}
		}
	}
	public void geneVaildMap() {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				if (i < 2 && j < 2) {
					blocks[i][j].colornum = (int) Math.floor(Math.random() * 4);
				} else if (i >= 2 && j < 2) {
					if (blocks[i - 1][j].colornum == blocks[i - 2][j].colornum) {
						int temp = 0;
						do {
							temp = (int) Math.floor(Math.random() * 4);
						} while (temp == blocks[i - 1][j].colornum);
						blocks[i][j].colornum = temp;
					} else {
						blocks[i][j].colornum = (int) Math.floor(Math.random() * 4);
					}
				} else if (i < 2 && j >= 2) {
					if (blocks[i][j - 1].colornum == blocks[i][j - 2].colornum) {
						int temp = 0;
						do {
							temp = (int) Math.floor(Math.random() * 4);
						} while (temp == blocks[i][j - 1].colornum);
						blocks[i][j].colornum = temp;
					} else {
						blocks[i][j].colornum = (int) Math.floor(Math.random() * 4);
					}
				} else {
					if (blocks[i][j - 1].colornum == blocks[i][j - 2].colornum) {
						if (blocks[i - 1][j].colornum == blocks[i - 2][j].colornum) {
							int temp = 0;
							do {
								temp = (int) Math.floor(Math.random() * 4);
							} while (temp == blocks[i][j - 1].colornum || temp == blocks[i - 1][j].colornum);
							blocks[i][j].colornum = temp;
						} else {
							int temp = 0;
							do {
								temp = (int) Math.floor(Math.random() * 4);
							} while (temp == blocks[i][j - 1].colornum);
							blocks[i][j].colornum = temp;
						}
					} else {
						if (blocks[i - 1][j].colornum == blocks[i - 2][j].colornum) {
							int temp = 0;
							do {
								temp = (int) Math.floor(Math.random() * 4);
							} while (temp == blocks[i - 1][j].colornum);
							blocks[i][j].colornum = temp;
						} else {
							blocks[i][j].colornum = (int) Math.floor(Math.random() * 4);
						}
					}
				}
			}
		}
	}
	public void elimate(Block button) throws InterruptedException {
		int height = button.getSize().height;
		int width = button.getSize().width;
		button.setSize(0,0);
		repaint();
	}
	public void mouseClicked(MouseEvent e) {
		try {
			elimate((Block)e.getSource());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void exchange(JButton button1, JButton button2) throws InterruptedException {
		int button_1_x = button1.getX();
		int button_1_y = button1.getY();

		int button_2_x = button2.getX();
		int button_2_y = button2.getY();

		int flag_x = button_1_x > button_2_x ? 1 : (button_1_x == button_2_x ? 0 : -1);
		int flag_y = button_1_y > button_2_y ? 1 : (button_1_y == button_2_y ? 0 : -1);
		
		
		while (true) {
			if (flag_x == 0) {
				int button_1_y_ = button1.getY();
				int button_2_y_ = button2.getY();
				button1.setLocation(button_1_x, button_1_y_ - flag_y);
				button2.setLocation(button_2_x, button_2_y_ + flag_y);
				
				Thread.sleep(5);
				if (button1.getY() == button_2_y)
					break;
			} else {
				int button_1_x_ = button1.getX();
				int button_2_x_ = button2.getX();
				button1.setLocation(button_1_x_ - flag_x, button_1_y);
				button2.setLocation(button_2_x_ + flag_x, button_2_y);
				repaint();
				Thread.sleep(5);
				if (button1.getX() == button_2_x)
					break;
			}
		}

	}
}
class Block extends JButton{
	public static Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.white};
	public int colornum;
	public int col;
	public int row;
	public GameMap map;
	public Block(String color, int row, int col,  GameMap map) {
		this.row = row;
		this.col = col;
		this.map = map;
		if(color == "red") this.colornum = 0;
		else if (color == "green") this.colornum = 1;
		else if (color == "blue") this.colornum = 2;
		else if (color == "yellow") this.colornum = 3;
		else this.colornum = 4;
		
	}
	public void syncColor() {
		this.setBackground(colors[colornum]);
	}
}
