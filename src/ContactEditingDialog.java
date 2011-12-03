import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <p>A child of {@code JDialog} that implements {@code ActionListener} 
 * to create a dialog for editing objects of type {@code Contact}.</p>
 * @see ActionListener, Contact, JDialog
 */
public class ContactEditingDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 6991911125167356556L;
	private JTextField firstTF;
	private JTextField miTF;
	private JTextField lastTF;
	private JTextField adTF;
	private JTextField cityTF;
	private JTextField stateTF;
	private JTextField zipTF;
	private JTextField phoneTF;
	private JTextField emailTF;
	private Contact myContact;
	
	/**
	 * <p>Creates a new {@code ContactEditingDialog} initialized with the 
	 * values from the {@code Contact} passed as a parameter.  The 
	 * parameter must be non-null.</p>
	 * @param c a {@code Contact} to be edited by this dialog
	 */
	public ContactEditingDialog(Contact c)
	{
		super();
		this.setTitle("Add/Edit Contact");
		myContact = c;
		GridLayout textGridLayout = new GridLayout(9,2);
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel textPane = new JPanel();
		textPane.setLayout(textGridLayout);
		JPanel buttonPane = new JPanel();
		
		JLabel first = new JLabel("First Name:");
		firstTF = new JTextField(myContact.getMyFirst());
		JLabel mi = new JLabel("MI:");
		miTF = new JTextField(myContact.getMyMI());
		JLabel last = new JLabel("Last Name:");
		lastTF = new JTextField(myContact.getMyLast());
		JLabel ad = new JLabel("Street Address:");
		adTF = new JTextField(myContact.getMyStreetAddress());
		JLabel city = new JLabel("City:");
		cityTF = new JTextField(myContact.getMyCity());
		JLabel state = new JLabel("State:");
		stateTF = new JTextField(myContact.getMyState());
		JLabel zip = new JLabel("ZIP:");
		zipTF = new JTextField(myContact.getMyZIP());
		JLabel phone = new JLabel("Phone:");
		phoneTF = new JTextField(myContact.getMyPhone());
		JLabel email = new JLabel("E-Mail:");
		emailTF = new JTextField(myContact.getMyEmail());
		
		textPane.add(first);
		textPane.add(firstTF);
		textPane.add(mi);
		textPane.add(miTF);
		textPane.add(last);
		textPane.add(lastTF);
		textPane.add(ad);
		textPane.add(adTF);
		textPane.add(city);
		textPane.add(cityTF);
		textPane.add(state);
		textPane.add(stateTF);
		textPane.add(zip);
		textPane.add(zipTF);
		textPane.add(phone);
		textPane.add(phoneTF);
		textPane.add(email);
		textPane.add(emailTF);
		
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
		setContentPane(contentPane);
		setAlwaysOnTop(true);
		setModal(true);
		setSize(400,250);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private boolean validateFields()
	{
		return ((zipTF.getText().matches("\\d{5}") || zipTF.getText().compareTo("")==0) &&
				(phoneTF.getText().matches("\\d{3}-\\d{3}-\\d{4}") || phoneTF.getText().compareTo("")==0) &&
				emailTF.getText().matches("\\w+@\\w+.\\w+.\\w{3}"));
	}
	
	/**
	 * <p>A method of ActionListener that catches {@code ActionEvent}s 
	 * created by the menu and buttons of this dialog and reacts accordingly.
	 * </p>
	 * @param arg0 the {@code ActionEvent} for which a reaction is necessary
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if(action.compareTo("cancel")==0)
		{
			dispose();
		}
		else if(action.compareTo("save")==0)
		{
			Contact oldRef = myContact;
			DataStore d = DataStore.getInstance();
			if(!validateFields())
			{
				AlertDialog aD = new AlertDialog("One or more of your" +
						" entries was not of the desired format.\n" +
						"Phone numbers should be of the format: xxx-xxx-xxxx.\n" +
						"Email addresses should be of the format: [username]@[host].[extension].\n" +
						"Zip codes should be of the format: xxxxx.");
				return;
			}
			myContact = new Contact();
			myContact.setMyFirst(firstTF.getText());
			myContact.setMyMI(miTF.getText());
			myContact.setMyLast(lastTF.getText());
			myContact.setMyCity(cityTF.getText());
			myContact.setMyStreetAddress(adTF.getText());
			myContact.setMyZIP(zipTF.getText());
			myContact.setMyPhone(phoneTF.getText());
			myContact.setMyState(stateTF.getText());
			myContact.setMyEmail(emailTF.getText());
			if((!oldRef.isBlank()) && d.containsContact(oldRef))
			{
				d.removeContact(oldRef);
				d.addContact(myContact);
			}
			else
			{
				d.addContact(myContact);
			}
			dispose();
		}
	}

}
