package capture;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;

public class Mainui {

	
	protected Shell shell;
	static int num=0;
	String selecteddir;
	private Snippet95 cutScreen;
	private About aboutUs;
	private Edit edit;
    public  static BufferedImage bufImage ;
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static void main(String[] args) {
		try {
			Mainui window = new Mainui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
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
	private static void toggleDisplay(Shell shell, Tray tray) {   
        try {   
            shell.setVisible(!shell.isVisible());   
            tray.getItem(0).setVisible(!shell.isVisible());   
            if (shell.getVisible()) {   
                shell.setMinimized(false);   
                shell.setActive();   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }  
	
	
		/**
		 * 关闭主窗口，最小托盘化
		 */
		protected void createContents() {
			
		shell = new Shell();
		/*shell.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				
				
				System.out.println("hahahahhahahhahhahha");
			}
		});*/
		shell.setBackground(SWTResourceManager.getColor(240, 230, 140));
		shell.setImage(org.eclipse.wb.swt.SWTResourceManager.getImage(Mainui.class, "/image/all.png"));
		shell.setSize(480, 84);
		shell.setText("kaca");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnNewButton = new Button(composite, SWT.BORDER | SWT.CENTER);
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton.setImage(org.eclipse.wb.swt.SWTResourceManager.getImage(Mainui.class, "/image/all1.png"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				
				//全屏截图
				Rectangle rect = new Rectangle(0, 0, screenSize.width, screenSize.height);		       		      
				num++;
				// 截屏操作
				try {
					bufImage = new Robot().createScreenCapture(rect);
					
					folderDig(shell);
					quickSave(bufImage);
					
					edit=new Edit();
	            	edit.open();
	            	
	            	
	            	
					

				} catch (AWTException | IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
			
		});
		btnNewButton.setText("\u622A\u53D6\u5168\u5C4F");
		
		Button btnNewButton_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				num++;
				//shlKaca.dispose();
				cutScreen=new Snippet95();
            	cutScreen.open();
            	
				
			}
		});
		btnNewButton_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		btnNewButton_1.setImage(org.eclipse.wb.swt.SWTResourceManager.getImage(Mainui.class, "/image/fixed_field.png"));
		btnNewButton_1.setText("\u77E9\u5F62\u533A\u57DF");
		
		Button btnNewButton_3 = new Button(composite, SWT.BORDER);
		btnNewButton_3.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				edit=new Edit();
            	edit.open();
				
			}
			
		});
		btnNewButton_3.setText("\u56FE\u7247\u7F16\u8F91\u5668");
		
		Button btnNewButton_2 = new Button(composite, SWT.BORDER);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aboutUs=new About();
				aboutUs.open();
				
			}
		});
		btnNewButton_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnNewButton_2.setText("\u5173\u4E8E\u6211\u4EEC");

	}
	/*public static File getBufferImage() {
		BufferedImage  out=ImageIO.read(new File(<String>bufImage.getData()));
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		
		ImageIO.write(out,".jpg",baos);
		baos.flush();
		byte[] imageInByte=baos.toByteArray();
		baos.close();
		 ImageIO.write(bufImage,".jpg",out);
	}
	*/
	
	protected void folderDig(Shell parent){
		//新建文件夹（目录）对话框
		DirectoryDialog folderdlg=new DirectoryDialog(parent);
		//设置文件对话框的标题
		folderdlg.setText("文件选择");
		//设置初始路径
		folderdlg.setFilterPath("SystemDrive");
		//设置对话框提示文本信息
		folderdlg.setMessage("请选择相应的文件夹");
		//打开文件对话框，返回选中文件夹目录
		selecteddir=folderdlg.open();
		if(selecteddir==null){
			return ;
		}
		else{
			System.out.println("您选中的文件夹目录为："+selecteddir);
		}
	}

	private void quickSave(BufferedImage bufImage) throws IOException {
		File screenFile=new File(selecteddir);
		if(!screenFile.exists()) {
			screenFile.mkdir();
		}
		File f=new File(screenFile,num+".png");
		ImageIO.write(bufImage, "png", f);
		
		/*if (Desktop.isDesktopSupported()
				&& Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
			Desktop.getDesktop().open(f);*/
	}
}
