package happyeliminating;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class a extends JPanel implements Runnable{
	public JButton buttons[] = new JButton[9];
	public static a panel = new a();
	public a() {
		this.setLayout(new GridLayout(3, 3, 0, 0));
		for (int i = 0; i < 9; ++i) {
			buttons[i] = new JButton(Integer.toString(i));
			this.add(buttons[i]);
			buttons[i].addMouseListener(new Mouseli());
		}
//		new Thread(this).start();
	}

	public void paint(Graphics g) {
		super.paint(g);
//		paintAll(g);
		
	}
	public void elimate(JButton button) throws InterruptedException {
		while (true) {
			int height = button.getSize().height;
			int width = button.getSize().width;
			button.setSize(width - 1, height - 1);
			Dimension men = button.getSize();
			men.setSize(CENTER_ALIGNMENT, CENTER_ALIGNMENT);
			Thread.sleep(5);
			if (width < 10)
				break;
		}
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

	public static void main(String[] args) throws InterruptedException {
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.add(panel);
		Container pa = jFrame.getContentPane();
		System.out.print(pa.getComponent(0));
		jFrame.setSize(300, 300);
		jFrame.setVisible(true);
		System.out.print(panel.buttons[0].getSize());
		System.out.print(panel.buttons[0].getX() + "\t" + panel.buttons[1].getX());
		panel.exchange(panel.buttons[0], panel.buttons[2]);
		panel.exchange(panel.buttons[1], panel.buttons[4]);
		System.out.println(panel.buttons[2].getY());
        panel.buttons[2].setLocation(panel.buttons[2].getX()+5, panel.buttons[2].getY());
        
        System.out.print(panel.getBlockFromGridPoint(0, 0).getLocation());
        
	}
	
	public Block getBlockFromGridPoint(int x, int y) {
		return (Block) getComponentAt(y * new Block().getWidth() , x * new Block().getHeight() );
	}
	
	public void run() {
		repaint();
	}
	
}

class Mouseli implements MouseListener {
	public void mouseClicked(MouseEvent e) {
		final JButton button = (JButton)e.getSource();
		
		final a panel = a.panel;
		//			panel.exchange(button,
//					(JButton) panel.getComponentAt(e.getX() + button.getX() + button.getWidth(), e.getY() + button.getY()));
		
			
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				while(true) {
					int height = button.getSize().height;
					int width = button.getSize().width;
					button.setSize(width - 1, height - 1);
					panel.repaint();
					//men.setSize(CENTER_ALIGNMENT, CENTER_ALIGNMENT);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (width < 10)
						break;
				}
			}
		});
			
		
		
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println(e.getX());
		JButton button = (JButton) e.getSource();
		a panel = a.panel;
		if (e.getY() <= button.getHeight() && e.getY() >= 0) {
			if (e.getX() > button.getWidth() && e.getX() < button.getWidth() * 2
					|| e.getX() < 0 && e.getX() > -1 * button.getWidth()) {
				try {
					panel.exchange(button,
							(JButton) panel.getComponentAt(e.getX() + button.getX(), e.getY() + button.getY()));
					return;
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	

}