import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.compareTo("add")==0)
		{
			// Construct a ContactEditingDialog and handle it
		}
		else if(action.compareTo("edit")==0)
		{
			// Construct a ContactEditingDialog and handle it
		}
		else if(action.compareTo("delete")==0)
		{
			//  Construct a ContactEditingDialog and handle it (use the dataStore)
		}
		else
		{
			System.err.println("Unhandled action.  DO SOMETHING!");
		}
	}
}
