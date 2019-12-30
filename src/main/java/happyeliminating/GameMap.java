package happyeliminating;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameMap extends JPanel {

	/**
	 * Create the panel. 默认Map是正方形
	 */

	public int height = 10;
	public int width = 10;
	public Block[][] blocks;

	public GameMap() {
		this.setLayout(new GridLayout(height, width, 0, 0));
		init();
	}

	public void printMap() {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				System.out.print(blocks[i][j].colornum + "\t");
			}
			System.out.print("\n");
		}
	}

	public GameMap(int hei, int wid) {
		height = hei;
		width = wid;
		this.setLayout(new GridLayout(height, width, 0, 0));
		init();
	}
	
	public void elimate(JButton button) throws InterruptedException {
		while(true) {
			int height = button.getSize().height;
			int width = button.getSize().width;
			button.setSize(width-1, height-1);
			Thread.sleep(10);
			if(width < 10) break;
		}
	}
	
	public void init() {
		blocks = new Block[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				blocks[i][j] = new Block("white", i, j);
			}
		}
		geneVaildMap();
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				blocks[i][j].syncColor();
				this.add(blocks[i][j]);
			}
		}
	}

	public void restart() {
		geneVaildMap();
		syncAllColor();
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

	public void checkMap() {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				System.out.print(checkBlock(i, j) + "\t");
			}
			System.out.print("\n");
		}
	}

	public void syncAllColor() {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				blocks[i][j].syncColor();
			}
		}
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

	public void RandomColor() {
		/*
		 * 这个方法是测试checkBlock用的，一般用不到
		 */
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				blocks[i][j].colornum = (int) Math.floor(Math.random() * 4);
				blocks[i][j].syncColor();
			}
		}
	}

	public static void main(String[] args) {
		new GameMap();
	}
}
