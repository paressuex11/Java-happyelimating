package happyeliminating;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class a extends JPanel{
	public JButton buttons[] = new JButton[4];
	public a() {
		this.setLayout(new GridLayout(2, 2, 0, 0));
		for(int i = 0; i < 4 ; ++ i) {
			buttons[i] = new JButton(Integer.toString(i));
			this.add(buttons[i]);
		}
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
	
	public void exchange(JButton button1, JButton button2) throws InterruptedException {
		int button_1_x = button1.getX();
		int button_1_y = button1.getY();
		
		int button_2_x = button2.getX();
		int button_2_y = button2.getY();
		
		int flag_x = button_1_x > button_2_x ? 1 : (button_1_x == button_2_x ? 0 : -1);
		int flag_y = button_1_y > button_2_y ? 1 : (button_1_y == button_2_y ? 0 : -1);
		
		
		while(true) {
			if(flag_x == 0) {
				int button_1_y_ = button1.getY();
				int button_2_y_ = button2.getY();
				button1.setLocation(button_1_x, button_1_y_ - flag_y);
				button2.setLocation(button_2_x, button_2_y_ + flag_y);
				Thread.sleep(5);
				if(button1.getY() == button_2_y) break;
			}
			else {
				int button_1_x_ = button1.getX();
				int button_2_x_ = button2.getX();
				button1.setLocation(button_1_x_ - flag_x, button_1_y);
				button2.setLocation(button_2_x_ + flag_x, button_2_y);
				Thread.sleep(5);
				if(button1.getX() == button_2_x) break;
			}
		}
		
	}
    public static void main(String[] args) throws InterruptedException {
        // �������
        JFrame jFrame = new JFrame();
        // ��������
        a panel = new a();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        
        //������С��ͼ��Ļ���Ƕ�뵽�����
        jFrame.add(panel);
        Container pa =  jFrame.getContentPane();
        System.out.print(pa.getComponent(0));
        // ���û����С����ȣ��߶ȣ���Ĭ�϶�Ϊ0
        jFrame.setSize(300, 300);
        // ������չʾ������true���ÿɼ���Ĭ��Ϊfalse����
        jFrame.setVisible(true);
        System.out.print(panel.buttons[0].getSize());
        System.out.print(panel.buttons[0].getX() + "\t" + panel.buttons[1].getX());
        panel.exchange(panel.buttons[0], panel.buttons[2]);
        System.out.println(panel.buttons[2].getY());
//        panel.buttons[2].setLocation(panel.buttons[2].getX(), panel.buttons[2].getY());
        
    }
}
