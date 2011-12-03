import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SystemInformationDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -2737180714288832047L;

	public SystemInformationDialog() {
		super();
		this.setTitle("System Information");

		JPanel contentPane = new JPanel(new GridLayout(4, 1));
		JPanel appPane = new JPanel();
		JPanel verPane = new JPanel();
		JPanel licPane = new JPanel();
		JPanel buttonPane = new JPanel();

		JLabel appTag = new JLabel("Application Name: ");
		JLabel appVal = new JLabel("SimpleMail");
		JLabel verTag = new JLabel("Version No.: ");
		JLabel verVal = new JLabel("Version 1.0");
		JLabel licTag = new JLabel("License Info: ");
		JLabel licVal = new JLabel("GPL(v3)");

		appPane.add(appTag);
		appPane.add(appVal);
		verPane.add(verTag);
		verPane.add(verVal);
		licPane.add(licTag);
		licPane.add(licVal);

		contentPane.add(appPane);
		contentPane.add(verPane);
		contentPane.add(licPane);

		ActionListener aL = (this);

		Button cancel = new Button("Ok");
		cancel.setSize(100, 50);
		cancel.setActionCommand("ok");
		cancel.addActionListener(aL);
		buttonPane.add(cancel);

		contentPane.add(buttonPane, BorderLayout.SOUTH);
		this.setContentPane(contentPane);
		this.setAlwaysOnTop(true);
		this.setModal(true);
		this.setSize(250, 150);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();
		if (s.compareTo("ok") == 0) {
			this.dispose();
		}
	}

}
