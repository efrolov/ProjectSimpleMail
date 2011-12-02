import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ConfigurationDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 654591847880778195L;
	private Configuration myConf;
	private JTextField myEmail;
	private JTextField myIP;
	
	public ConfigurationDialog(Configuration c)
	{
		super();
		myConf = c;
		this.setTitle("Edit Configuration");
		
		GridLayout textGridLayout = new GridLayout(2,2);
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel textPane = new JPanel();
		textPane.setLayout(textGridLayout);
		JPanel buttonPane = new JPanel();
		
		JLabel ip = new JLabel("SMTP Server IP:");
		myIP = new JTextField(myConf.getMySMTP().getHostName());
		JLabel em = new JLabel("Main Email Address:");
		myEmail = new JTextField(myConf.getMyEmail());
		
		textPane.add(ip);
		textPane.add(myIP);
		textPane.add(em);
		textPane.add(myEmail);
		
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
		setSize(250,150);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.compareTo("cancel")==0)
		{
			dispose();
		}
		else if(s.compareTo("save")==0)
		{
			DataStore d = DataStore.getInstance();
			myConf.setMyEmail(myEmail.getText());
			try {
				myConf.setMySMTP(Inet4Address.getByName(myIP.getText()));
			} catch (UnknownHostException e1) {
				System.err.println("SMTP Host did not validate.");
			}
			d.setConfiguration(myConf);
			dispose();
		}
	}

}
