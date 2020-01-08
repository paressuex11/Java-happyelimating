package happyeliminatingv2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
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

class GameMap extends JPanel implements MouseListener, Runnable{

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
		new Thread(this).start();
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
				blocks[i][j].setBounds(j*jb_width,i*jb_height,jb_width,jb_height);
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
	public void elimate(Block button)  {
		button.setSize(0,0);
		repaint();
	}
	public boolean checkBlock(int row, int col) {
		int left_x = col;
		int right_x = col;
		int top_y = row;
		int bottom_y = row;

		while (true) {
			left_x--;
			if (left_x == -1 || blocks[row][left_x].colornum != blocks[row][col].colornum) {
				left_x++;
				break;
			}
		}

		while (true) {
			right_x++;
			if (right_x == width || blocks[row][right_x].colornum != blocks[row][col].colornum) {
				right_x--;
				break;
			}
		}

		while (true) {
			top_y--;
			if (top_y == -1 || blocks[top_y][col].colornum != blocks[row][col].colornum) {
				top_y++;
				break;
			}
		}

		while (true) {
			bottom_y++;
			if (bottom_y == height || blocks[bottom_y][col].colornum != blocks[row][col].colornum) {
				bottom_y--;
				break;
			}
		}

		if (Math.abs(left_x - right_x) >= 2 || Math.abs(top_y - bottom_y) >= 2)
			return false; // false代表要被消除
		return true;
	}
	
	public void mouseClicked(MouseEvent e) {
		Block button = (Block)e.getSource();
		System.out.println(button.row);
		System.out.println(button.col);
		System.out.println(blocks[button.row][button.col].row);
		System.out.println(blocks[button.row][button.col].col);
		System.out.println(checkBlock(button.row, button.col));
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
		Block button = (Block) e.getSource();
		Block button2 = (Block)getComponentAt(e.getX() + button.getX(), e.getY() + button.getY());
		if (e.getY() <= button.getHeight() && e.getY() >= 0) {
			if (e.getX() > button.getWidth() && e.getX() < button.getWidth() * 2
					|| e.getX() < 0 && e.getX() > -1 * button.getWidth()) {
				exchange(button, button2);
				button.map.repaint();
			}

		}
		else if(e.getX() <= button.getWidth() && e.getX() >= 0) {
			if (e.getY() > button.getHeight() && e.getY() < button.getHeight() * 2
					|| e.getY() < 0 && e.getY() > -1 * button.getHeight()) {
				exchange(button, button2);
				repaint();
			}
		}
	}
	public void exchange(Block button1, Block button2)   {
		swap_blocks(button1, button2);
		swap_rowcol(button1, button2);
		swap_point(button1, button2);
	}
	public void swap_point(Block button1, Block button2) {
		Point b1_po = button1.getLocation();
		Point b2_po = button2.getLocation();
		button1.setLocation(b2_po);
		button2.setLocation(b1_po);
	}
	public void swap_rowcol(Block button1, Block button2) {
		int b1_row = button1.row;
		int b1_col = button1.col;
		button1.row = button2.row;
		button1.col = button2.col;
		button2.row = b1_row;
		button2.col = b1_col; 
	}
	public void swap_blocks(Block button1, Block button2) {
		Block temp = button1;
		blocks[button1.row][button1.col] = button2;
		blocks[button2.row][button2.col] = button1;
	}
	public void run() {
		while(true) {
			if(checkMap()) {
				reorganize();
			}
		}
	}
	public void reorganize() {
		for(int j = 0; j < width ; ++ j) {
			for (int i = 0; i < height ; ++ i) {
				
			}
		}
	}
	public boolean checkMap() {
		boolean temp = false;
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				if(!checkBlock(i, j)) {
					elimateAnimate(blocks[i][j]);
					temp = true;
				}
			}
		}
		return temp;
	}
	private void elimateAnimate(Block block) {
		while(true) {
			block.setSize(block.getWidth() - 1, block.getHeight() - 1);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(block.getWidth() == 0 || block.getHeight() == 0) {
				block.setSize(0,0);
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
