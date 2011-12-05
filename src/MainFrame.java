import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

/**
 * <p>
 * A child of {@code JFrame} that implements {@code ActionListener} and 
 * {@code WindowListener} to create a frame to display the {@code ContactTableModel}.
 * </p>
 * 
 * @see ActionListener, ContactTableModel, JFrame, WindowListener 
 */
public class MainFrame extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 2269971701250845501L;
	private JContactTable myTable;
	private ContactTableModel myTableModel;

	public MainFrame() {
		super("SimpleMail");
		this.myTableModel = new ContactTableModel();
		try {
			this.setIconImage(ImageIO.read(new File(DataStore.getMyFolderPath()
					+ "icon.png")));
		} catch (IOException e) {
			AlertDialog a = new AlertDialog("Failed to read icon image.");
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create and set up the window.
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		JMenuBar menuBar;
		JMenu fileM, configM, helpM;
		JMenuItem f1, c1, h1;
		menuBar = new JMenuBar();
		fileM = new JMenu("File");
		fileM.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileM);
		// Creating the MenuItems
		f1 = new JMenuItem("Exit", KeyEvent.VK_X);
		f1.setActionCommand("exit");
		f1.addActionListener(this);
		fileM.add(f1);
		configM = new JMenu("Configuration");
		configM.setMnemonic(KeyEvent.VK_C);
		menuBar.add(configM);
		c1 = new JMenuItem("Configure...", KeyEvent.VK_C);
		c1.setActionCommand("config");
		c1.addActionListener(this);
		configM.add(c1);
		helpM = new JMenu("Help");
		helpM.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpM);
		h1 = new JMenuItem("About", KeyEvent.VK_A);
		h1.setActionCommand("about");
		h1.addActionListener(this);
		helpM.add(h1);
		this.setJMenuBar(menuBar);

		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());

		// Create the JPanel to hold the table
		JPanel tablePane = new JPanel(new BorderLayout());
		tablePane.setOpaque(true);
		this.setContentPane(contentPane);

		// Create the table and add it to its container
		this.myTable = this.createTable();
		this.myTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		JScrollPane sP = new JScrollPane(this.myTable);
		tablePane.add(sP, BorderLayout.NORTH);

		// Create the buttons panel
		JPanel buttons = new JPanel();

		// Create an ActionListener
		ActionListener aL = this;

		// Create the buttons and add them to their container
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

		// Add the panels to the content pane
		contentPane.add(buttons, BorderLayout.SOUTH);
		contentPane.add(tablePane, BorderLayout.NORTH);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	/**
	 * <p>
	 * A method of ActionListener that catches {@code ActionEvent}s created by
	 * the menu and buttons of this dialog and reacts accordingly.
	 * </p>
	 * 
	 * @param arg0
	 *            the {@code ActionEvent} for which a reaction is necessary
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();
		if (s.compareTo("exit") == 0) {
			this.dispose();
		} else if (s.compareTo("config") == 0) {
			Configuration c = DataStore.getInstance().getMyConfiguration();
			ConfigurationDialog confD = new ConfigurationDialog(c);
		} else if (s.compareTo("about") == 0) {
			SystemInformationDialog sID = new SystemInformationDialog();
		} else if (s.compareTo("add") == 0) {
			Contact b = new Contact();
			ContactEditingDialog cEdit = new ContactEditingDialog(b);
			while (cEdit.isValid()) {
				;
			}
			this.myTable.revalidate();
			this.myTable.repaint();
		} else if (s.compareTo("edit") == 0) {
			try {
				Contact c = this.myTable.getContactAtRow(this.myTable
						.getSelectedRow());
				ContactEditingDialog cEdit = new ContactEditingDialog(c);
				while (cEdit.isValid()) {
					;
				}
				this.myTable.revalidate();
				this.myTable.repaint();
			} catch (ArrayIndexOutOfBoundsException e) {
				AlertDialog a = new AlertDialog("You have not selected a contact to be edited. Please try again.");

			}
		} else if (s.compareTo("delete") == 0) {
			try {
				Contact c = this.myTable.getContactAtRow(this.myTable
						.getSelectedRow());
				DataStore.getInstance().removeContact(c);
				this.myTable.revalidate();
				this.myTable.repaint();
			} catch (ArrayIndexOutOfBoundsException e) {
				AlertDialog a = new AlertDialog("You have not selected a contact to be deleted. Please try again.");
			}
		} else {
			System.err.println("Unhandled action.  DO SOMETHING!");
		}
	}

	private JContactTable createTable() {
		JContactTable t = new JContactTable(this.myTableModel);
		return t;
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
		this.dispose();
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
