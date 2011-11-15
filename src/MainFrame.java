import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;


public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2269971701250845501L;
	private ContactTableModel myTableModel;

	public MainFrame()
	{
		super("SimpleMail");
		myTableModel = new ContactTableModel();
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the window.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar;
		JMenu fileM, configM, helpM;
		JMenuItem f1, c1, h1;
		menuBar = new JMenuBar();
		fileM = new JMenu("File");
		fileM.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileM);
		// Creating the MenuItems
		f1 = new JMenuItem("Exit",
				KeyEvent.VK_X);
		f1.setName("exit");
		f1.addActionListener(this);
		fileM.add(f1);
		configM = new JMenu("Configuration");
		configM.setMnemonic(KeyEvent.VK_C);
		menuBar.add(configM);
		c1 = new JMenuItem("Configure...",
				KeyEvent.VK_C);
		c1.setName("config");
		c1.addActionListener(this);
		configM.add(c1);
		helpM = new JMenu("Help");
		helpM.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpM);
		h1 = new JMenuItem("About",
				KeyEvent.VK_A);
		h1.setName("about");
		h1.addActionListener(this);
		helpM.add(h1);
		
		// Create the content-pane-to-be.
		JPanel jplContentPane = new JPanel(new BorderLayout());
		jplContentPane.setOpaque(true);
		setJMenuBar(menuBar);
		setContentPane(jplContentPane);
		JTable tTab = createTable();
		tTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		jplContentPane.add(tTab.getTableHeader(), BorderLayout.PAGE_START);
		jplContentPane.add(tTab, BorderLayout.CENTER);
		setSize(800, 600);
		setVisible(true);
	}
	
	private JTable createTable()
	{
		JTable t = new JTable(myTableModel);
		return t;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JMenuItem source = (JMenuItem) (arg0.getSource());
		String s = source.getName();
		if(s.compareTo("exit")==0)
		{
			System.exit(0);
		}
		else if(s.compareTo("config")==0)
		{
			//Do some good stuff
		}
		else if(s.compareTo("about")==0)
		{
			//Some more good stuff should happen here
		}
	}
}
