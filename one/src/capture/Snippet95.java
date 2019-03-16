package capture;


import java.awt.Dimension;


import java.awt.Toolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Snippet95{

	public boolean bool=false;	
	protected Shell shell;

	int x,fx;
	int y,fy;
	int i=0;
	String path;

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		int screenX,screenY;
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		screenX=d.width;
		screenY=d.height;		
		shell = new Shell(SWT.NONE);
		final Shell she=new Shell(shell,SWT.NULL);
		
		setCursor(shell);
		setCursor(she);
		she.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent e) {
				
				setPop(she);
			
			}
		});
		shell.addMouseMoveListener(new MouseMoveListener() {
			
			public void mouseMove(MouseEvent e) {
				
				if(e.stateMask==524288)
				{
					if(i==0)
					{
						x=e.x;
						y=e.y;
						i++;
					}
					she.setAlpha(50);
					she.setBounds(x, y, e.x-x,e.y-y);
					System.out.println(e.x+" "+e.y);
					she.setVisible(true);
				}
			}
		});
		

		shell.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				bool=true;

			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				x=e.x;
				y=e.y;
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				if(e.button==3)
				{
					if(she.isVisible()==true)
					{	
						//bool=true;
						she.setVisible(false);
						i=0;
					}else
					shell.close();
				}
				if(e.button==1)
				{
					fx=e.x;
					fy=e.y;
				}
				
				
			}
		});
		shell.setSize(screenX, screenY);
		shell.setLocation(0, 0);
		shell.setAlpha(1);
		shell.open();
	}
	
	public void setCursor(Shell shell)
	{
		ImageData imageData = new ImageData("src/image/jiantou.png");
        Cursor cursor = new Cursor(shell.getDisplay(), imageData, 0, 0);
        shell.setCursor(cursor);
	}
	
	public void setPop(final Shell shell)
	{
		
		Menu pop=new Menu(shell);
		MenuItem Save=new MenuItem(pop,SWT.None);
		Save.setText("保存");
		 Save.addSelectionListener(new SelectionAdapter() {
			 		boolean flag=false;
			 	public void widgetSelected(SelectionEvent e) {
			 		FileDialog aa=new FileDialog(shell);
			 		aa.setFilterExtensions(new String[]{"*.png"});
			 		String filepath=aa.open();
			 		if(filepath!=null){
			 		path=filepath+".png";
			 		flag=true;
			 		
			 		}
			 		if(flag)
			 		{
			 			try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			 			CutScreen();
				 		shell.setVisible(false);
				 		flag=false;
				 		MessageBox tishi=new MessageBox(shell);
				 		tishi.setText("提示");
				 		tishi.setMessage("截图成功！");
				 		tishi.open();
			 		}
			 		
			 	}
			 });
		MenuItem Exit=new MenuItem(pop,SWT.None);
		Exit.setText("重新选择");
		
		 Exit.addSelectionListener(new SelectionAdapter() {
			 	public void widgetSelected(SelectionEvent e) {
			 		//bool=true;
			 		shell.setVisible(false);
			 		i=0;
			 	}
			 });
		pop.setVisible(true);
	}
	
	public void CutScreen(){
		Image img = getScreen(x,y,fx-x,fy-y); 
		//img.getImageData(). 
		ImageLoader imgLoader = new ImageLoader(); 
		imgLoader.data = new ImageData[]{img.getImageData()}; 
		imgLoader.save(path, SWT.IMAGE_JPEG); 
	}
	public static Image getScreen(int x, int y, int width, int height) { 
		Display display = Display.getDefault(); 
		Rectangle rectangle = new Rectangle(0, 0, width, height); 
		Image image = new Image(display, rectangle); 
		GC gc = new GC(display); 
		gc.copyArea(image, x, y); 
		gc.dispose(); 
		return image; 
		}


}
