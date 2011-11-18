import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		setJMenuBar(menuBar);
		
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		
		//Create the JPanel to hold the table
		JPanel tablePane = new JPanel(new BorderLayout());
		tablePane.setOpaque(true);
		setContentPane(contentPane);
		
		//Create the table and add it to its container
		JTable tTab = createTable();
		tTab.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane sP = new JScrollPane(tTab);
		tablePane.add(sP,BorderLayout.NORTH);
		
		//Create the buttons panel
		JPanel buttons = new JPanel();
		
		//Create an ActionListener
		ActionListener aL = new MainFrameButtonListener();
		
		//Create the buttons and add them to their container
		Button add = new Button("Add");
		add.setSize(100, 50);
		add.setActionCommand("add");
		add.addActionListener(aL);
		add.setBackground(Color.GREEN);
		Button edit = new Button("Edit...");
		edit.setSize(100, 50);
		edit.setActionCommand("edit");
		edit.addActionListener(aL);
		edit.setBackground(Color.YELLOW);
		Button delete = new Button("Delete");
		delete.setSize(100, 50);
		delete.setActionCommand("delete");
		delete.addActionListener(aL);
		delete.setBackground(Color.RED);
		buttons.add(add);
		buttons.add(edit);
		buttons.add(delete);
		
		//Add the panels to the content pane
		contentPane.add(buttons, BorderLayout.SOUTH);
		contentPane.add(tablePane, BorderLayout.NORTH);
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
