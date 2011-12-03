import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class SystemInformationDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -2737180714288832047L;
	
	public SystemInformationDialog()
	{
		super();
		this.setTitle("System Information");
		
		GridBagLayout textGridLayout = new GridBagLayout();
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel textPane = new JPanel();
		textPane.setLayout(textGridLayout);
		JPanel buttonPane = new JPanel();
		
		JLabel appTag = new JLabel("Application Name: ");
		JLabel appVal = new JLabel("SimpleMail");
		JLabel verTag = new JLabel("Version No.: ");
		JLabel verVal = new JLabel("Version 1.0; checksum 31c812544f8492bc26368c854b7b60f7c054092e");
		JLabel licTag = new JLabel("License Info: ");
		JLabel licVal = new JLabel("This software is released under the GPL(v3) license." +
				"  All code is made publicly available at http://github.com/efrolov/ProjectSimpleMail");
		
		textPane.add(appTag);
		textPane.add(appVal);
		textPane.add(verTag);
		textPane.add(verVal);
		textPane.add(licTag);
		textPane.add(licVal);
		
		textGridLayout.layoutContainer(textPane);
		contentPane.add(textPane, BorderLayout.PAGE_START);
		
		ActionListener aL = (this);
		
		Button cancel = new Button("Ok");
		cancel.setSize(100, 50);
		cancel.setActionCommand("ok");
		cancel.addActionListener(aL);
		buttonPane.add(cancel);
		
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		setContentPane(contentPane);
		setAlwaysOnTop(true);
		setModal(true);
		setSize(250,150);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();
		if(s.compareTo("ok")==0)
		{
			dispose();
		}
	}

}
