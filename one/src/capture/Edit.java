package capture;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.TrayItem;


import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


//import 截图工具.编辑框;

public class Edit {

	protected Shell shlFaststone;
	private Composite composite_3;
	private Event event2;
	private Event event1;
	private Text text;
	static boolean flag[]=new boolean[2];
	private MenuItem mntmNewItem_2;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
private MenuItem mntmNewItem_5 ;

static boolean mouseDown=false;
static Point pt;
static ArrayList<int[]> list=new ArrayList<int[]>();
static Composite rect;
static boolean dirty=false;
static Rectangle[] rexx=new Rectangle[1000];
static int n=0;

	/**
	 * Launch the application.
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			Edit window = new Edit();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlFaststone.open();
		shlFaststone.layout();
		while (!shlFaststone.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlFaststone = new Shell();
		shlFaststone.setImage(SWTResourceManager.getImage(Edit.class, "/image/all1.png"));
		shlFaststone.setToolTipText("");
		shlFaststone.setSize(944, 479);
		shlFaststone.setText("kaca");
		shlFaststone.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlFaststone, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		//
		Menu menu = new Menu(shlFaststone, SWT.BAR);
		shlFaststone.setMenuBar(menu);
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setSelection(true);
		menuItem.setText("\u6587\u4EF6");
		
		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);
		
		final Canvas down=new Canvas(shlFaststone,SWT.NONE|SWT.BORDER|SWT.DOUBLE_BUFFERED);
		
		Composite composite_4 = new Composite(sashForm, SWT.BORDER);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_3 = new SashForm(composite_4, SWT.NONE);
		formToolkit.adapt(sashForm_3);
		formToolkit.paintBordersFor(sashForm_3);
		
		ToolBar toolBar = new ToolBar(sashForm_3, SWT.BORDER | SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		toolBar.setToolTipText("");
		
		ToolItem toolItem_6 = new ToolItem(toolBar, SWT.NONE);
		toolItem_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mntmNewItem_2.notifyListeners(SWT.Selection,event1);

			}
		});
		toolItem_6.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6253\u5F00.jpg"));
		toolItem_6.setText("\u6253\u5F00");
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		
		
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				

			}
		});
		//
		
		tltmNewItem.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u53E6\u5B58\u4E3A.jpg"));
		tltmNewItem.setText("\u53E6\u5B58\u4E3A");
		//关闭
		ToolItem tltmNewItem_4 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mntmNewItem_5.notifyListeners(SWT.Selection, event2);
				
			}
		});
		tltmNewItem_4.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7ED3\u675F.jpg"));
		tltmNewItem_4.setText("\u5173\u95ED");
		
		
		
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		
		
		//缩放
		
		
		tltmNewItem_1.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u653E\u5927.jpg"));
		
		//工具栏：放大
		
		tltmNewItem_1.setText("\u653E\u5927");
		
		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.NONE);
		
		//工具栏：缩小
		tltmNewItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		
		tltmNewItem_2.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F29\u5C0F.jpg"));
		tltmNewItem_2.setText("\u7F29\u5C0F");
		ToolItem toolItem_5 = new ToolItem(toolBar, SWT.NONE);
		toolItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mntmNewItem_5.notifyListeners(SWT.Selection, event2);

			}
		});
		toolItem_5.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7ED3\u675F.jpg"));
		toolItem_5.setText("\u9000\u51FA");
		
		ToolBar toolBar_1 = new ToolBar(sashForm_3, SWT.BORDER | SWT.FLAT | SWT.RIGHT);
		formToolkit.adapt(toolBar_1);
		formToolkit.paintBordersFor(toolBar_1);
		
		ToolItem toolItem_7 = new ToolItem(toolBar_1, SWT.NONE);
		
		//工具栏：标题
		toolItem_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		//
		
		toolItem_7.setText("\u6807\u9898");
		toolItem_7.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6807\u9898.jpg"));
		
		ToolItem toolItem_1 = new ToolItem(toolBar_1, SWT.NONE);
		
		//工具栏：调整大小
		toolItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		//
		
		toolItem_1.setText("\u8C03\u6574\u5927\u5C0F");
		toolItem_1.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u8C03\u6574\u5927\u5C0F.jpg"));
		
		ToolBar toolBar_2 = new ToolBar(sashForm_3, SWT.BORDER | SWT.FLAT | SWT.RIGHT);
		toolBar_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		formToolkit.adapt(toolBar_2);
		formToolkit.paintBordersFor(toolBar_2);
		
		ToolItem toolItem_2 = new ToolItem(toolBar_2, SWT.NONE);
		
		//工具栏：裁剪
		toolItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		//
		
		toolItem_2.setText("\u88C1\u526A");
		toolItem_2.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u88C1\u526A.jpg"));
		
		ToolItem toolItem_3 = new ToolItem(toolBar_2, SWT.NONE);
		
		//工具栏：剪切
		toolItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		//
		
		toolItem_3.setText("\u526A\u5207");
		toolItem_3.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u526A\u5207.jpg"));
		
		ToolItem toolItem_4 = new ToolItem(toolBar_2, SWT.NONE);
		

		//工具栏：粘贴
		toolItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				composite_3.layout();
				File f=new File("src/picture/beauty.jpg");
				ImageData imageData;
				try {
					imageData = new ImageData(  new FileInputStream(  f));
					Image image=new Image(shlFaststone.getDisplay(),imageData);
					Button lblNewLabel_3 = null;
					lblNewLabel_3.setImage(image);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				composite_3.layout();
			}
		});
		//omposite;
		//
		
		toolItem_4.setText("\u590D\u5236");
		toolItem_4.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u590D\u5236.jpg"));
		
		ToolItem tltmNewItem_3 = new ToolItem(toolBar_2, SWT.NONE);
		tltmNewItem_3.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u7C98\u8D34.jpg"));
		tltmNewItem_3.setText("\u7C98\u8D34");
		sashForm_3.setWeights(new int[] {486, 165, 267});
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		formToolkit.adapt(composite_1);
		formToolkit.paintBordersFor(composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		formToolkit.adapt(sashForm_1);
		formToolkit.paintBordersFor(sashForm_1);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		formToolkit.adapt(composite_2);
		formToolkit.paintBordersFor(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(composite_2, SWT.NONE);
		tabFolder.setTouchEnabled(true);
		formToolkit.adapt(tabFolder);
		formToolkit.paintBordersFor(tabFolder);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("\u65B0\u5EFA\u4E00");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("\u65B0\u5EFA\u4E8C");
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("\u65B0\u5EFA\u4E09");
		
		Button button = new Button(tabFolder, SWT.CHECK);
		button.setText("Check Button");
		
		composite_3 = new Composite(sashForm_1, SWT.H_SCROLL | SWT.V_SCROLL);
		
		formToolkit.adapt(composite_3);
		formToolkit.paintBordersFor(composite_3);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		formToolkit.adapt(lblNewLabel_3, true, true);
		lblNewLabel_3.setText("");
		sashForm_1.setWeights(new int[] {19, 323});
		
		Composite composite_5 = new Composite(sashForm, SWT.NONE);
		composite_5.setToolTipText("");
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_5, SWT.NONE);
		formToolkit.adapt(sashForm_2);
		formToolkit.paintBordersFor(sashForm_2);
		
		Label lblNewLabel = new Label(sashForm_2, SWT.BORDER | SWT.CENTER);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("1/1");
		
		Label lblNewLabel_2 = new Label(sashForm_2, SWT.BORDER | SWT.CENTER);
		formToolkit.adapt(lblNewLabel_2, true, true);
		lblNewLabel_2.setText("\u5927\u5C0F\uFF1A1366*728");
		
		Label lblNewLabel_1 = new Label(sashForm_2, SWT.CENTER);
		formToolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("\u7F29\u653E\uFF1A100%");
		
		Label label = new Label(sashForm_2, SWT.NONE);
		label.setAlignment(SWT.RIGHT);
		formToolkit.adapt(label, true, true);
		label.setText("\u5494\u5693\u5DE5\u4F5C\u5BA4\u7248\u6743\u6240\u6709");
		sashForm_2.setWeights(new int[] {127, 141, 161, 490});
		sashForm.setWeights(new int[] {50, 346, 22});
		
		
		
		
		
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6587\u4EF6\u5B50\u56FE\u6807/\u6587\u4EF6.\u65B0\u5EFA.jpg"));
		mntmNewItem.setText("\u65B0\u5EFA");
		
		mntmNewItem_2 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
				//Canvas c=new Canvas(shlFaststone,SWT.BALLOON);
				
				FileDialog dialog = new FileDialog(shlFaststone,SWT.OPEN);  
				dialog.setFilterPath(System.getProperty("user_home"));//设置初始路径
				dialog.setFilterNames(new String[] {"文本文档(*txt)","所有文档"});   
				dialog.setFilterExtensions(new String[]{"*.exe","*.xls","*.*"});
				String path=dialog.open();
				String s=null;
				File f=null;
				if(path==null||"".equals(path)) {
					return ;
				}
				try{
			    f=new File(path);
				byte[] bs=Fileutil.readFile(f);
			    s=new String(bs,"UTF-8");
				}catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
					MessageDialog.openError(shlFaststone, "出错了", "打开"+path+"出错了");
					return ;
				}
			    
				text = new Text(composite_4, SWT.BORDER | SWT.WRAP
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL
						| SWT.MULTI);
				text.setText(s);
				composite_1.layout();
				shlFaststone.setText(shlFaststone.getText()+"\t"+f.getName());
					
				File f1=new File(path);
				ImageData imageData;
				try {
					imageData = new ImageData(  new FileInputStream(  f1));
					Image image=new Image(shlFaststone.getDisplay(),imageData);
					lblNewLabel_3.setImage(image);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		mntmNewItem_2.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6587\u4EF6\u5B50\u56FE\u6807/\u6587\u4EF6.\u6253\u5F00.jpg"));
		mntmNewItem_2.setText("\u6253\u5F00");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.setText("\u4ECE\u526A\u8D34\u677F\u5BFC\u5165");
		
		MenuItem mntmNewItem_3 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_3.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6587\u4EF6\u5B50\u56FE\u6807/\u6587\u4EF6.\u53E6\u5B58\u4E3A.jpg"));
		mntmNewItem_3.setText("\u53E6\u5B58\u4E3A");
		
		 mntmNewItem_5 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 boolean result=MessageDialog.openConfirm(shlFaststone,"退出","是否确认退出");
				 if(result) {
					 System.exit(0);
				 }

			}
		});
		mntmNewItem_5.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6587\u4EF6\u5B50\u56FE\u6807/\u6587\u4EF6.\u4FDD\u5B58.jpg"));
		mntmNewItem_5.setText("\u5173\u95ED");
		event2=new Event();
		event2.widget=mntmNewItem_5;

		
		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("\u6355\u6349");
		
		Menu menu_2 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_2);
		
		MenuItem mntmNewItem_6 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_6.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349\u6D3B\u52A8\u7A97\u53E3.jpg"));
		mntmNewItem_6.setText("\u6355\u6349\u6D3B\u52A8\u7A97\u53E3");
		
		MenuItem mntmNewItem_7 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_7.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349.\u6355\u6349\u7A97\u53E3\u6216\u5BF9\u8C61.jpg"));
		mntmNewItem_7.setText("\u6355\u6349\u7A97\u53E3\u5BF9\u8C61");
		
		MenuItem mntmNewItem_8 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_8.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349.jpg"));
		mntmNewItem_8.setText("\u6355\u6349\u77E9\u5F62\u533A\u57DF");
		
		MenuItem mntmNewItem_9 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_9.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349.\u624B\u7ED8\u533A\u57DF.jpg"));
		mntmNewItem_9.setText("\u6355\u6349\u624B\u7ED8\u533A\u57DF");
		
		MenuItem mntmNewItem_10 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_10.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349.\u6574\u4E2A\u5C4F\u5E55.jpg"));
		mntmNewItem_10.setText("\u6355\u6349\u6574\u4E2A\u5C4F\u5E55");
		
		MenuItem mntmNewItem_11 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_11.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349\u6EDA\u52A8\u7A97\u53E3.jpg"));
		mntmNewItem_11.setText("\u6355\u6349\u6EDA\u52A8\u7A97\u53E3");
		
		MenuItem mntmNewItem_12 = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem_12.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349.\u56FA\u5B9A\u5927\u5C0F\u533A\u57DF.jpg"));
		mntmNewItem_12.setText("\u6355\u6349\u56FA\u5B9A\u5927\u5C0F\u533A\u57DF");
		
		MenuItem menuItem_1 = new MenuItem(menu_2, SWT.NONE);
		menuItem_1.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u6355\u6349/\u6355\u6349.\u91CD\u590D\u4E0A\u6B21\u6355\u6349.jpg"));
		menuItem_1.setText("\u91CD\u590D\u4E0A\u6B21\u6355\u6349");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.CASCADE);
		menuItem_2.setText("\u7F16\u8F91");
		
		Menu menu_3 = new Menu(menuItem_2);
		menuItem_2.setMenu(menu_3);
		
		MenuItem mntmNewItem_14 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_14.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u5DE6\u64A4\u9500.jpg"));
		mntmNewItem_14.setText("\u64A4\u9500");
		
		MenuItem mntmNewItem_13 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_13.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u53F3\u64A4\u9500.jpg"));
		mntmNewItem_13.setText("\u91CD\u505A");
		
		MenuItem mntmNewItem_15 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_15.setText("\u9009\u62E9\u5168\u90E8");
		
		MenuItem mntmNewItem_16 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_16.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u7F16\u8F91.\u88C1\u526A.jpg"));
		mntmNewItem_16.setText("\u88C1\u526A");
		
		MenuItem mntmNewItem_17 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_17.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u526A\u5207.jpg"));
		mntmNewItem_17.setText("\u526A\u5207");
		
		MenuItem mntmNewItem_18 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_18.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u590D\u5236.jpg"));
		mntmNewItem_18.setText("\u590D\u5236");
		
		MenuItem menuItem_4 = new MenuItem(menu_3, SWT.NONE);
		menuItem_4.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u7C98\u8D34.jpg"));
		menuItem_4.setText("\u7C98\u8D34");
		
		MenuItem mntmNewItem_19 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_19.setText("\u5220\u9664");
		
		MenuItem menuItem_3 = new MenuItem(menu, SWT.CASCADE);
		menuItem_3.setText("\u7279\u6548");
		
		Menu menu_4 = new Menu(menuItem_3);
		menuItem_3.setMenu(menu_4);
		
		MenuItem mntmNewItem_20 = new MenuItem(menu_4, SWT.NONE);
		mntmNewItem_20.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7279\u6548.\u6C34\u5370.jpg"));
		mntmNewItem_20.setText("\u6C34\u5370");
		
		PanelPic ppn = new PanelPic();
		MenuItem mntmNewItem_21 = new MenuItem(menu_4, SWT.NONE);
		mntmNewItem_21.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flag[0]=true;
				flag[1]=false;

			}

		});
		
		down.addMouseListener(new MouseAdapter(){
			public void mouseDown(MouseEvent e)
			{
				mouseDown=true;
				pt=new Point(e.x,e.y);
				if(flag[1])
				{
					rect=new Composite(down,SWT.BORDER);
					rect.setLocation(e.x, e.y);
					n++;
				}
			}
			public void mouseUp(MouseEvent e)
			{
				mouseDown=false;
				if(flag[1]&&dirty)
				{
					rexx[n-1]=rect.getBounds();
					rect.dispose();
					down.redraw();
					dirty=false;
				}
			}
		});
		down.addMouseMoveListener(new MouseMoveListener(){
			@Override
			public void mouseMove(MouseEvent e) {
              if(mouseDown)
            {
            	 dirty=true;
				if(flag[0])
			  {
            	  GC gc=new GC(down);
                  gc.drawLine(pt.x, pt.y, e.x, e.y);
                  list.add(new int[]{pt.x,pt.y,e.x,e.y});
                  pt.x=e.x;pt.y=e.y;
			  }
              else if(flag[1])
              {
            	  if(rect!=null)
            	  rect.setSize(rect.getSize().x+e.x-pt.x, rect.getSize().y+e.y-pt.y);
//            	  down.redraw();
            	  pt.x=e.x;pt.y=e.y;
              }
            }
			}
			
		});
		down.addPaintListener(new PaintListener(){
			@Override
			public void paintControl(PaintEvent e) {
				for(int i=0;i<list.size();i++)
				{
					int a[]=list.get(i);
					e.gc.drawLine(a[0], a[1], a[2], a[3]);
				}
				for(int i=0;i<n;i++)
				{
					if(rexx[i]!=null)
						e.gc.drawRectangle(rexx[i]);
				}
			}});

		
		mntmNewItem_21.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7279\u6548.\u6587\u5B57.jpg"));
		mntmNewItem_21.setText("\u753B\u7B14");
		
		MenuItem mntmNewSubmenu_1 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_1.setText("\u67E5\u770B");
		
		Menu menu_5 = new Menu(mntmNewSubmenu_1);
		mntmNewSubmenu_1.setMenu(menu_5);
		
		MenuItem mntmNewItem_24 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_24.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u67E5\u770B.\u653E\u5927.jpg"));
		mntmNewItem_24.setText("\u653E\u5927");
		
		MenuItem mntmNewItem_25 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_25.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u67E5\u770B.\u7F29\u5C0F.jpg"));
		mntmNewItem_25.setText("\u7F29\u5C0F");
		
		MenuItem mntmNewItem_26 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_26.setText("\u5B9E\u9645\u5C3A\u5BF8\uFF08100%\uFF09");
		
		MenuItem mntmNewItem_27 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_27.setText("\u9002\u5408\u7A97\u53E3");
		
		MenuItem mntmNewItem_28 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_28.setText("100%");
		
		MenuItem mntmNewItem_29 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_29.setText("200%");
		
		MenuItem mntmNewSubmenu_2 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_2.setText("\u8BBE\u7F6E");
		
		Menu menu_6 = new Menu(mntmNewSubmenu_2);
		mntmNewSubmenu_2.setMenu(menu_6);
		
		MenuItem menuItem_5 = new MenuItem(menu, SWT.CASCADE);
		menuItem_5.setText("\u5E2E\u52A9");
		
		Menu menu_7 = new Menu(menuItem_5);
		menuItem_5.setMenu(menu_7);
		
		MenuItem menuItem_6 = new MenuItem(menu_7, SWT.NONE);
		menuItem_6.setText("\u7248\u672C");
		
		MenuItem mntmNewItem_23 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_23.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u5DE6\u64A4\u9500.jpg"));
		
		MenuItem mntmNewItem_30 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_30.setImage(SWTResourceManager.getImage(Edit.class, "/\u622A\u56FE\u5DE5\u5177/\u56FE\u7247\u8D44\u6E90/\u7F16\u8F91\u5B50\u56FE\u6807/\u53F3\u64A4\u9500.jpg"));

	}
}
