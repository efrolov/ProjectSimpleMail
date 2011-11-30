import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


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
	
	public ContactEditingDialog(Contact c)
	{
		super();
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
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if(action.compareTo("cancel")==0)
		{
			dispose();
		}
		else if(action.compareTo("save")==0)
		{
			DataStore d = DataStore.getInstance();
			myContact.setMyFirst(firstTF.getText());
			myContact.setMyMI(miTF.getText());
			myContact.setMyLast(lastTF.getText());
			myContact.setMyCity(cityTF.getText());
			myContact.setMyStreetAddress(adTF.getText());
			myContact.setMyZIP(zipTF.getText());
			myContact.setMyPhone(phoneTF.getText());
			myContact.setMyState(stateTF.getText());
			myContact.setMyEmail(emailTF.getText());
			if(d.containsContact(myContact))
			{
				d.updateContact(myContact);
			}
			else
			{
				d.addContact(myContact);
			}
			dispose();
		}
	}

}
