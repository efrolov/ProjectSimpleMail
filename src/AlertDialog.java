import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class AlertDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 604279692725179164L;
	
	public AlertDialog(String message)
	{
		super();
		this.setTitle("Alert!");
		JTextArea msg = new JTextArea(message);
		msg.setSize(400, 150);
		this.getContentPane().add(msg, BorderLayout.PAGE_START);
		Button cont = new Button("Ok");
		cont.setActionCommand("ok");
		cont.addActionListener(this);
		this.getContentPane().add(cont, BorderLayout.SOUTH);
		this.setSize(400,200);
		this.setLocation(40, 40);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
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
