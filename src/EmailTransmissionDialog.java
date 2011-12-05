import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
* <p>
* A child of {@code JDialog} that implements {@code ActionListener} to create a
* dialog for composing and sending a new email.
* </p>
* 
* @see ActionListener, JDialog
*/
public class EmailTransmissionDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 3865567629290751230L;
	private JTextArea myBody;
	private JTextField mySource, myAddressees, mySubject;
	
	/**
	 * <p>
	 * Creates a new {@code EmailTransmissionDialog} initialized with the values
	 * from the {@code Contact} passed as a parameter. The parameter must be
	 * non-null.
	 * </p>
	 * 
	 * @param initialAddressee
	 *            a {@code Contact} of the source email address
	 */
	public EmailTransmissionDialog(Contact initialAddressee) {
		super();
		this.setTitle("Send Email");

		JPanel contentPane = new JPanel();
		BoxLayout contLay = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(contLay);
		JPanel sourcePane = new JPanel(new GridLayout(1, 1));
		JPanel addressPane = new JPanel(new GridLayout(1, 1));
		JPanel subjectPane = new JPanel(new GridLayout(1, 1));
		JPanel bodyPane = new JPanel(new GridLayout(1, 1));
		JPanel buttonPane = new JPanel();

		JLabel srcTag = new JLabel("Source:");
		this.mySource = new JTextField(DataStore.getInstance()
				.getMyConfiguration().getMyEmail());
		JLabel addTag = new JLabel("Send to:");
		this.myAddressees = new JTextField(initialAddressee.getMyEmail() + ",");
		JLabel subTag = new JLabel("Subject:");
		this.mySubject = new JTextField();
		//TODO Make text area size tame and ensure word wrapping.
		this.myBody = new JTextArea();
		this.myBody.setLineWrap(true);
		this.myBody.setWrapStyleWord(true);

		sourcePane.add(srcTag);
		sourcePane.add(this.mySource);
		addressPane.add(addTag);
		addressPane.add(this.myAddressees);
		subjectPane.add(subTag);
		subjectPane.add(this.mySubject);
		bodyPane.add(this.myBody);

		contentPane.add(sourcePane, BorderLayout.PAGE_START);
		contentPane.add(addressPane);
		contentPane.add(subjectPane);
		contentPane.add(bodyPane, BorderLayout.CENTER);

		ActionListener aL = (this);

		Button cancel = new Button("Cancel");
		cancel.setSize(100, 50);
		cancel.setActionCommand("cancel");
		cancel.addActionListener(aL);
		Button save = new Button("Send");
		save.setSize(100, 50);
		save.setActionCommand("send");
		save.addActionListener(aL);
		buttonPane.add(cancel);
		buttonPane.add(save);

		contentPane.add(buttonPane, BorderLayout.SOUTH);
		this.setContentPane(contentPane);
		this.setAlwaysOnTop(true);
		this.setModal(true);
		this.setSize(400, 500);
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
		} else if (s.compareTo("send") == 0) {
			try {
				DataStore d = DataStore.getInstance();
				Properties props = System.getProperties();
				props.put("mail.smtp.host", d.getMyConfiguration().getMySMTP()
						.getHostName());

				Session session = Session.getDefaultInstance(props, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(this.mySource.getText()));

				msg.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(this.myAddressees.getText()));
				msg.setSubject(this.mySubject.getText());
				msg.setText(this.myBody.getText());

				Transport.send(msg);
			} catch (Exception exc) {
				AlertDialog a = new AlertDialog("Error: " + exc.getMessage());
			}
			this.dispose();
		}
	}

}
