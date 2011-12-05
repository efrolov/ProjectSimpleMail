import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/**
* <p>
* A child of {@code JDialog} that implements {@code ActionListener} to create a
* dialog for editing objects of type {@code Configuration}.
* </p>
* 
* @see ActionListener, Configuration, JDialog
*/
public class ConfigurationDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 654591847880778195L;
	private Configuration myConf;
	private JTextField myEmail;
	private JTextField myIP;
	
	/**
	 * <p>
	 * Creates a new {@code ConfigurationDialog} initialized with the values
	 * from the {@code Configuration} passed as a parameter. The parameter must be
	 * non-null.
	 * </p>
	 * 
	 * @param c
	 *            a {@code Configuration} to be edited by this dialog
	 */
	public ConfigurationDialog(Configuration c) {
		super();
		this.myConf = c;
		this.setTitle("Edit Configuration");

		GridLayout textGridLayout = new GridLayout(2, 2);
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel textPane = new JPanel();
		textPane.setLayout(textGridLayout);
		JPanel buttonPane = new JPanel();

		JLabel ip = new JLabel("SMTP Server IP:");
		this.myIP = new JTextField(this.myConf.getMySMTP().getHostName());
		JLabel em = new JLabel("Main Email Address:");
		this.myEmail = new JTextField(this.myConf.getMyEmail());

		textPane.add(ip);
		textPane.add(this.myIP);
		textPane.add(em);
		textPane.add(this.myEmail);

		textGridLayout.layoutContainer(textPane);
		contentPane.add(textPane, BorderLayout.PAGE_START);

		ActionListener aL = (this);

		Button cancel = new Button("Cancel");
		cancel.setSize(100, 50);
		cancel.setActionCommand("cancel");
		cancel.addActionListener(aL);
		Button save = new Button("Save");
		save.setSize(100, 50);
		save.setActionCommand("save");
		save.addActionListener(aL);
		buttonPane.add(cancel);
		buttonPane.add(save);

		contentPane.add(buttonPane, BorderLayout.SOUTH);
		this.setContentPane(contentPane);
		this.setAlwaysOnTop(true);
		this.setModal(true);
		this.setSize(250, 150);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.compareTo("cancel") == 0) {
			this.dispose();
		} else if (s.compareTo("save") == 0) {
			DataStore d = DataStore.getInstance();
			this.myConf.setMyEmail(this.myEmail.getText());
			try {
				this.myConf
						.setMySMTP(InetAddress.getByName(this.myIP.getText()));
			} catch (UnknownHostException e1) {
				System.err.println("SMTP Host did not validate.");
			}
			d.setConfiguration(this.myConf);
			this.dispose();
		}
	}

}
