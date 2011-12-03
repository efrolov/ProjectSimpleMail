import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.Timer;


public class JContactTable extends JTable implements MouseListener{

	private static final long serialVersionUID = 118736475082270287L;
	private int selectionIndex;
	
	public JContactTable(ContactTableModel myTableModel) {
		super(myTableModel);
		this.setDragEnabled(false);
		this.addMouseListener(this);
	}
	
    private Timer timer = new Timer(300, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            timer.stop();
        }

    });
	
	public Contact getContactAtRow(int row)
	{
		Contact c = new Contact();
		for(int i = 0; i<9; i++)
		{
			Object v = this.getValueAt(row, i);
			switch(i)
			{
				case 0:
					c.setMyFirst((String)v);
					break;
				case 1:
					c.setMyMI((String)v);
					break;
				case 2:
					c.setMyLast((String)v);
					break;
				case 3:
					c.setMyEmail((String)v);
					break;
				case 4:
					c.setMyPhone((String)v);
					break;
				case 5:
					c.setMyStreetAddress((String)v);
					break;
				case 6:
					c.setMyCity((String)v);
					break;
				case 7:
					c.setMyState((String)v);
					break;
				case 8:
					c.setMyZIP((String)v);
					break;
				default:
					System.err.println("Column number requested does not exist.");
			}
		}
		return c;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(timer.isRunning())
		{
			if(selectionIndex==this.getSelectedRow())
			{
				EmailTransmissionDialog eTrans = new EmailTransmissionDialog(
						this.getContactAtRow(selectionIndex)
						);
				selectionIndex=-1;
			}
			timer.stop();
		}
		else
		{
			selectionIndex = this.getSelectedRow();
			timer.restart();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
