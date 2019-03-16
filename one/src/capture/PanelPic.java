package capture;




import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.*;

// 有关绘画的属性类
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
	
	// 初始化所有属性数据
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
		
		if (mode == 4) {// 铅笔模式
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
		if (mode == 5) {// 橡皮
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
			// 清除原有绘图重新绘制
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
	
	// 鼠标按下后的消息处理函数
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
	
	// 鼠标拖动消息处理函数
	void this_mouseDragged(MouseEvent e) {
		newx = e.getX();
		newy = e.getY();
		action = 2;
		pdraw(buffgra);
	}
	
	// 鼠标释放后消息处理函数
	void this_mouseReleased(MouseEvent e) {
		newx = e.getX();
		newy = e.getY();
		action = 3;
		pdraw(buffgra);
	}
	
	// 绘图函数
	void drawpic(Graphics gra, int x1, int y1, int x2, int y2) {
		int tx, ty;
		switch (mode) {
		case 1:// 绘制直线
			gra.drawLine(x1, y1, x2, y2);
			break;
		case 2:// 绘制矩形
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
		case 3:// 绘制椭圆
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
		case 4:// 绘制铅笔画，由于java的运行速度较慢，所以采用绘线的方式防止不连贯
			gra.drawLine(x1, y1, x2, y2);
			break;
		case 5:// 橡皮
			gra.drawRect(x2-5, y2-5, 10, 10);
			gra.fillRect(x2-5, y2-5, 10, 10);
			break;
		default :
			break;
		}
	}
	
	// 绘制实体图形
	public void fillpic(Graphics gra, int x1, int y1, int x2, int y2) {
		int tx, ty;
		switch (mode) {
		case 2:// 绘制实心矩形
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
		case 3:// 绘制实心椭圆
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
	
	// 重载函数
	public void paintComponent(Graphics gra) {
		super.paintComponent(gra);
		if (ibuff != null)
			gra.drawImage(ibuff, 0, 0, this);
	}
	
	// 对缓冲区ibuff及其图形上下文初始化
	public void init_buff() {
		ibuff = createImage(getWidth(), getHeight());
		buffgra = ibuff.getGraphics();
		buffgra.setColor(Color.white);
		buffgra.fillRect(0, 0, getWidth(), getHeight());
		buffgra.setColor(Color.black);
	}
	
	// 图片加载
	public void loadimage(String s) {
		img = Toolkit.getDefaultToolkit().getImage(s);
		
		if (ibuff == null)
			init_buff();
		buffgra.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	// 图片加载进程控制
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
	
	// 清除画布
	public void clearAll() {
		Color col = buffgra.getColor();
		buffgra.setColor(Color.white);
		buffgra.fillRect(0, 0, getWidth(), getHeight());
		buffgra.setColor(col);
		repaint();
	}
}
