import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;


public class ContactEditingDialogButtonListener implements ActionListener {
	
	private JDialog myD;

	public ContactEditingDialogButtonListener(JDialog j)
	{
		myD = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if(action.compareTo("cancel")==0)
		{
			myD.dispose();
		}
		else if(action.compareTo("save")==0)
		{
			// Please do something good here.
			// I'm guessing DataStore would be a good place to start
		}
	}

}
