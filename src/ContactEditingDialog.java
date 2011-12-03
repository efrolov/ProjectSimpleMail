import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * <p>
 * A child of {@code JDialog} that implements {@code ActionListener} to create a
 * dialog for editing objects of type {@code Contact}.
 * </p>
 * 
 * @see ActionListener, Contact, JDialog
 */
public class ContactEditingDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 6991911125167356556L;
	private JTextField adTF;
	private JTextField cityTF;
	private JTextField emailTF;
	private JTextField firstTF;
	private JTextField lastTF;
	private JTextField miTF;
	private Contact myContact;
	private JTextField phoneTF;
	private JTextField stateTF;
	private JTextField zipTF;

	/**
	 * <p>
	 * Creates a new {@code ContactEditingDialog} initialized with the values
	 * from the {@code Contact} passed as a parameter. The parameter must be
	 * non-null.
	 * </p>
	 * 
	 * @param c
	 *            a {@code Contact} to be edited by this dialog
	 */
	public ContactEditingDialog(Contact c) {
		super();
		this.setTitle("Add/Edit Contact");
		this.myContact = c;
		GridLayout textGridLayout = new GridLayout(9, 2);
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel textPane = new JPanel();
		textPane.setLayout(textGridLayout);
		JPanel buttonPane = new JPanel();

		JLabel first = new JLabel("First Name:");
		this.firstTF = new JTextField(this.myContact.getMyFirst());
		JLabel mi = new JLabel("MI:");
		this.miTF = new JTextField(this.myContact.getMyMI());
		JLabel last = new JLabel("Last Name:");
		this.lastTF = new JTextField(this.myContact.getMyLast());
		JLabel ad = new JLabel("Street Address:");
		this.adTF = new JTextField(this.myContact.getMyStreetAddress());
		JLabel city = new JLabel("City:");
		this.cityTF = new JTextField(this.myContact.getMyCity());
		JLabel state = new JLabel("State:");
		this.stateTF = new JTextField(this.myContact.getMyState());
		JLabel zip = new JLabel("ZIP:");
		this.zipTF = new JTextField(this.myContact.getMyZIP());
		JLabel phone = new JLabel("Phone:");
		this.phoneTF = new JTextField(this.myContact.getMyPhone());
		JLabel email = new JLabel("E-Mail:");
		this.emailTF = new JTextField(this.myContact.getMyEmail());

		textPane.add(first);
		textPane.add(this.firstTF);
		textPane.add(mi);
		textPane.add(this.miTF);
		textPane.add(last);
		textPane.add(this.lastTF);
		textPane.add(ad);
		textPane.add(this.adTF);
		textPane.add(city);
		textPane.add(this.cityTF);
		textPane.add(state);
		textPane.add(this.stateTF);
		textPane.add(zip);
		textPane.add(this.zipTF);
		textPane.add(phone);
		textPane.add(this.phoneTF);
		textPane.add(email);
		textPane.add(this.emailTF);

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
		this.setSize(400, 250);
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
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if (action.compareTo("cancel") == 0) {
			this.dispose();
		} else if (action.compareTo("save") == 0) {
			Contact oldRef = this.myContact;
			DataStore d = DataStore.getInstance();
			if (!this.validateFields()) {
				AlertDialog aD = new AlertDialog(
						"One or more of your"
								+ " entries was not of the desired format.\n"
								+ "Phone numbers should be of the format: xxx-xxx-xxxx.\n"
								+ "Email addresses should be of the format: [username]@[host].[extension].\n"
								+ "Zip codes should be of the format: xxxxx.");
				return;
			}
			this.myContact = new Contact();
			this.myContact.setMyFirst(this.firstTF.getText());
			this.myContact.setMyMI(this.miTF.getText());
			this.myContact.setMyLast(this.lastTF.getText());
			this.myContact.setMyCity(this.cityTF.getText());
			this.myContact.setMyStreetAddress(this.adTF.getText());
			this.myContact.setMyZIP(this.zipTF.getText());
			this.myContact.setMyPhone(this.phoneTF.getText());
			this.myContact.setMyState(this.stateTF.getText());
			this.myContact.setMyEmail(this.emailTF.getText());
			if ((!oldRef.isBlank()) && d.containsContact(oldRef)) {
				d.removeContact(oldRef);
				d.addContact(this.myContact);
			} else {
				d.addContact(this.myContact);
			}
			this.dispose();
		}
	}

	private boolean validateFields() {
		return ((this.zipTF.getText().matches("\\d{5}") || (this.zipTF
				.getText().compareTo("") == 0))
				&& (this.phoneTF.getText().matches("\\d{3}-\\d{3}-\\d{4}") || (this.phoneTF
						.getText().compareTo("") == 0)) && this.emailTF
				.getText().matches("\\w+@\\w+.\\w+.\\w{3}"));
	}

}
