package capture;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;

public class About {

	protected Shell shlKaca;
	private Text txtKacav;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			About window = new About();
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
		shlKaca.open();
		shlKaca.layout();
		while (!shlKaca.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlKaca = new Shell();
		shlKaca.setImage(SWTResourceManager.getImage(About.class, "/image/all.png"));
		shlKaca.setSize(373, 245);
		shlKaca.setText("kaca");
		shlKaca.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlKaca, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		txtKacav = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		txtKacav.setText("\r\n\u7248\u672C\u53F7\uFF1A\tkaca  --v1.0\r\n\r\n\u5F00\u53D1\u5C0F\u7EC4\uFF1A\t\u6708\u9ED1\u98CE\u9AD8\u591C\r\n\r\n\u5C0F\u7EC4\u6210\u5458\uFF1A  \u8096\u66E6\u3001\u6B27\u83C1\u3001\u5370\u658C\u3001\u9AD8\u6653\u5CF0\u3001\u9648\u601D\u601D\r\n\r\n\u8054\u7CFB\u65B9\u5F0F\uFF1AQQ\uFF1A1412037600");

	}
}
