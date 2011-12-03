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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class EmailTransmissionDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 3865567629290751230L;
	private JTextField mySource, myAddressees, mySubject;
	private JTextArea myBody;

	public EmailTransmissionDialog(Contact initialAddressee)
	{
		super();
		this.setTitle("Send Email");
		
		JPanel contentPane = new JPanel();
		BoxLayout contLay = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(contLay);
		JPanel sourcePane = new JPanel(new GridLayout(1,1));
		JPanel addressPane = new JPanel(new GridLayout(1,1));
		JPanel subjectPane = new JPanel(new GridLayout(1,1));
		JPanel bodyPane = new JPanel(new GridLayout(1,1));
		JPanel buttonPane = new JPanel();
		
		JLabel srcTag = new JLabel("Source:");
		mySource = new JTextField(
				DataStore.getInstance().getMyConfiguration().getMyEmail()
				);
		JLabel addTag = new JLabel("Send to:");
		myAddressees = new JTextField(initialAddressee.getMyEmail()+",");
		JLabel subTag = new JLabel("Subject:");
		mySubject = new JTextField();
		myBody = new JTextArea(20,20);
		
		sourcePane.add(srcTag);
		sourcePane.add(mySource);
		addressPane.add(addTag);
		addressPane.add(myAddressees);
		subjectPane.add(subTag);
		subjectPane.add(mySubject);
		bodyPane.add(myBody);
		
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
		setContentPane(contentPane);
		setAlwaysOnTop(true);
		setModal(true);
		setSize(400,500);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.compareTo("cancel")==0)
		{
			dispose();
		}
		else if(s.compareTo("send")==0)
		{
			try {
				DataStore d = DataStore.getInstance();
				Properties props = System.getProperties();
				props.put("mail.smtp.host", d.getMyConfiguration().getMySMTP().getHostName());

				Session session = Session.getDefaultInstance(props, null);

				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(mySource.getText()));

				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(myAddressees.getText()));
				msg.setSubject(mySubject.getText());
				msg.setText(myBody.getText());

				Transport.send(msg);
			} catch (Exception exc) {
				AlertDialog a = new AlertDialog("Error: " + exc.getMessage());    
			}
			dispose();
		}
	}

}
