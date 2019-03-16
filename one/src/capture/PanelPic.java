package capture;




import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.*;

// �йػ滭��������
public class PanelPic extends JPanel{
	int startx, starty, oldx, oldy, newx, newy;
	int action;
	public Color cl;
	public int mode;
	public boolean isempty;
	public Image ibuff;
	public Graphics buffgra;
	public boolean hasbuff;
	Image img;
	
	
	public PanelPic() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// ��ʼ��������������
	void jbInit() throws Exception {
		this.setBackground(Color.white);
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				this_mouseDragged(e);
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				this_mousePressed(e);
			}
			
			public void mouseReleased(MouseEvent e) {
				this_mouseReleased(e);
			}
		});
		
		startx = -1;
		starty = -1;
		oldx = -1;
		oldy = -1;
		newx = -1;
		newy = -1;
		action = -1;
		mode = 1;
		hasbuff = false;
		isempty = true;
	}
	
	public void pdraw(Graphics gra) {
		gra.setColor(this.getForeground());
		//gra.setColor(Color.black);
		
		if (mode == 4) {// Ǧ��ģʽ
			gra.setPaintMode();
			switch (action) {
			case 1:
				oldx = newx;
				oldy = newy;
				drawpic(gra, oldx, oldy, newx, newy);
				break;
			case 2:
				drawpic(gra, oldx, oldy, newx, newy);
				oldx = newx;
				oldy = newy;
				break;
			case 3:
				drawpic(gra, oldx, oldy, newx, newy);
				oldx = -1;
				oldy = -1;
				newx = -1;
				newy = -1;
				startx = -1;
				starty = -1;
				break;
			default :
				break;
			}
			repaint();
			return;
		}
		if (mode == 5) {// ��Ƥ
			gra.setPaintMode();
			gra.setColor(Color.white);
			
			switch (action) {
			case 1:
				drawpic(gra, oldx, oldy, newx, newy);
				break;
			case 2:
				drawpic(gra, oldx, oldy, newx, newy);
				break;
			case 3:
				drawpic(gra, oldx, oldy, newx, newy);
				oldx = -1;
				oldy = -1;
				newx = -1;
				newy = -1;
				startx = -1;
				starty = -1;
				break;
			default :
				break;
			}
			repaint();
			return;
		}
		switch (action) {
		case 1:
			oldx = newx;
			oldy = newy;
			if (mode != 3)
				drawpic(gra, startx, starty, newx, newy);
			break;
		case 2:
			gra.setXORMode(Color.white);
			// ���ԭ�л�ͼ���»���
			drawpic(gra, startx, starty, oldx, oldy);
			drawpic(gra, startx, starty, newx, newy);
			
			oldx = newx;
			oldy = newy;
			break;
		case 3:
			gra.setXORMode(Color.white);
			drawpic(gra, startx, starty, oldx, oldy);
			gra.setPaintMode();
			drawpic(gra, startx, starty, newx, newy);
			if (!isempty) {
				fillpic(gra, startx, starty, newx, newy);
			}
			oldx = -1;
			oldy = -1;
			newx = -1;
			newy = -1;
			startx = -1;
			starty = -1;
			break;
		default :
			break;
		}
		repaint();
	}
	
	// ��갴�º����Ϣ������
	void this_mousePressed(MouseEvent e) {
		startx = e.getX();
		starty = e.getY();
		newx = startx;
		newy = starty;
		action = 1;
		if (ibuff == null)
			init_buff();
		pdraw(buffgra);
	}
	
	// ����϶���Ϣ������
	void this_mouseDragged(MouseEvent e) {
		newx = e.getX();
		newy = e.getY();
		action = 2;
		pdraw(buffgra);
	}
	
	// ����ͷź���Ϣ������
	void this_mouseReleased(MouseEvent e) {
		newx = e.getX();
		newy = e.getY();
		action = 3;
		pdraw(buffgra);
	}
	
	// ��ͼ����
	void drawpic(Graphics gra, int x1, int y1, int x2, int y2) {
		int tx, ty;
		switch (mode) {
		case 1:// ����ֱ��
			gra.drawLine(x1, y1, x2, y2);
			break;
		case 2:// ���ƾ���
			if (x1 > x2) {
				tx = x2;
				x2 = x1;
				x1 = tx;
			}
			if (y1 > y2) {
				ty = y2;
				y2 = y1;
				y1 = ty;
			}
			gra.drawRect(x1, y1, x2-x1, y2-y1);
			break;
		case 3:// ������Բ
			if (x1 > x2) {
				tx = x2;
				x2 = x1;
				x1 = tx;
			}
			if (y1 > y2) {
				ty = y2;
				y2 = y1;
				y1 = ty;
			}
			gra.drawOval(x1, y1, x2-x1, y2-y1);
			break;
		case 4:// ����Ǧ�ʻ�������java�������ٶȽ��������Բ��û��ߵķ�ʽ��ֹ������
			gra.drawLine(x1, y1, x2, y2);
			break;
		case 5:// ��Ƥ
			gra.drawRect(x2-5, y2-5, 10, 10);
			gra.fillRect(x2-5, y2-5, 10, 10);
			break;
		default :
			break;
		}
	}
	
	// ����ʵ��ͼ��
	public void fillpic(Graphics gra, int x1, int y1, int x2, int y2) {
		int tx, ty;
		switch (mode) {
		case 2:// ����ʵ�ľ���
			if (x1 > x2) {
				tx = x2;
				x2 = x1;
				x1 = tx;
			}
			if (y1 > y2) {
				ty = y2;
				y2 = y1;
				y1 = ty;
			}
			gra.fillRect(x1, y1, x2-x1, y2-y1);
			break;
		case 3:// ����ʵ����Բ
			if (x1 > x2) {
				tx = x2;
				x2 = x1;
				x1 = tx;
			}
			if (y1 > y2) {
				ty = y2;
				y2 = y1;
				y1 = ty;
			}
			gra.fillOval(x1, y1, x2-x1, y2-y1);
			break;
		default :
			break;
		}
		repaint();
	}
	
	// ���غ���
	public void paintComponent(Graphics gra) {
		super.paintComponent(gra);
		if (ibuff != null)
			gra.drawImage(ibuff, 0, 0, this);
	}
	
	// �Ի�����ibuff����ͼ�������ĳ�ʼ��
	public void init_buff() {
		ibuff = createImage(getWidth(), getHeight());
		buffgra = ibuff.getGraphics();
		buffgra.setColor(Color.white);
		buffgra.fillRect(0, 0, getWidth(), getHeight());
		buffgra.setColor(Color.black);
	}
	
	// ͼƬ����
	public void loadimage(String s) {
		img = Toolkit.getDefaultToolkit().getImage(s);
		
		if (ibuff == null)
			init_buff();
		buffgra.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	// ͼƬ���ؽ��̿���
	public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h) {
		Color col;
		if (flags == ImageObserver.ALLBITS) {
			col = buffgra.getColor();
			buffgra.setColor(Color.white);
			buffgra.fillRect(0, 0, getWidth(), getHeight());
			buffgra.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			buffgra.setColor(col);
			repaint();
			return false;
		}
		return true;
	}
	
	// �������
	public void clearAll() {
		Color col = buffgra.getColor();
		buffgra.setColor(Color.white);
		buffgra.fillRect(0, 0, getWidth(), getHeight());
		buffgra.setColor(col);
		repaint();
	}
}
