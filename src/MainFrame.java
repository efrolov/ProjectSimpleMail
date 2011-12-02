import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MainFrame extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 2269971701250845501L;
	private ContactTableModel myTableModel;
	private JContactTable myTable;

	public MainFrame()
	{
		super("SimpleMail");
		myTableModel = new ContactTableModel();
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the window.
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
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
		f1.setActionCommand("exit");
		f1.addActionListener(this);
		fileM.add(f1);
		configM = new JMenu("Configuration");
		configM.setMnemonic(KeyEvent.VK_C);
		menuBar.add(configM);
		c1 = new JMenuItem("Configure...",
				KeyEvent.VK_C);
		c1.setActionCommand("config");
		c1.addActionListener(this);
		configM.add(c1);
		helpM = new JMenu("Help");
		helpM.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpM);
		h1 = new JMenuItem("About",
				KeyEvent.VK_A);
		h1.setActionCommand("about");
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
		myTable = createTable();
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane sP = new JScrollPane(myTable);
		tablePane.add(sP,BorderLayout.NORTH);
		
		//Create the buttons panel
		JPanel buttons = new JPanel();
		
		//Create an ActionListener
		ActionListener aL = this;
		
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
	
	private JContactTable createTable()
	{
		JContactTable t = new JContactTable(myTableModel);
		return t;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();
		if(s.compareTo("exit")==0)
		{
			dispose();
		}
		else if(s.compareTo("config")==0)
		{
			Configuration c = DataStore.getInstance().getMyConfiguration();
			ConfigurationDialog confD = new ConfigurationDialog(c);
		}
		else if(s.compareTo("about")==0)
		{
			//Some more good stuff should happen here
		}
		else if(s.compareTo("add")==0)
		{
			Contact b = new Contact();
			ContactEditingDialog cEdit = new ContactEditingDialog(b);
		}
		else if(s.compareTo("edit")==0)
		{
			try{
			Contact c = myTable.getContactAtRow(myTable.getSelectedRow());
			ContactEditingDialog cEdit = new ContactEditingDialog(c);
			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("You have not selected a contact to be edited. " +
						"Please select a contact and try again.");
				
			}
		}
		else if(s.compareTo("delete")==0)
		{
			try{
				Contact c = myTable.getContactAtRow(myTable.getSelectedRow());
				DataStore.getInstance().removeContact(c);
				} catch (ArrayIndexOutOfBoundsException e){
					System.out.println("You have not selected a contact to be deleted. " +
							"Please select a contact and try again.");
				}
		}
		else
		{
			System.err.println("Unhandled action.  DO SOMETHING!");
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		DataStore d = DataStore.getInstance();
		d.save();
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {	
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {	
	}
}
