package happyeliminating;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class Block extends JButton implements MouseListener{
	public static Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.white};
	public int colornum;
	public int col;
	public int row;
	public Block(String color, int row, int col) {
		this.row = row;
		this.col = col;
		if(color == "red") this.colornum = 0;
		else if (color == "green") this.colornum = 1;
		else if (color == "blue") this.colornum = 2;
		else if (color == "yellow") this.colornum = 3;
		else this.colornum = 4;
		
	}
	public Block(int color, int row, int col) {
		this.row = row;
		this.col = col;
		this.colornum = color;
	}
	public Block() {
		// doing nothing
	}
	public void syncColor() {
		this.setBackground(colors[colornum]);
	}
	public void mouseClicked(MouseEvent arg0) {
		
	}
	public void mouseEntered(MouseEvent arg0) {
		
	}
	public void mouseExited(MouseEvent arg0) {
		
	}
	public void mousePressed(MouseEvent arg0) {
		
	}
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	
}
