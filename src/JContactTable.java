import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.Timer;

/**
 * <p>
 * A child of {@code JTable} that implements {@code MouseListener} to create a
 * table of objects of type {@code Contact}.
 * </p>
 * 
 * @see Contact, JTable, MouseListener 
 */
public class JContactTable extends JTable implements MouseListener {

	private static final long serialVersionUID = 118736475082270287L;
	private int selectionIndex;

	private Timer timer = new Timer(300, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JContactTable.this.timer.stop();
		}

	});
	
	/**
	 * <p>
	 * Creates a new {@code JContactTable} initialized with the values
	 * from the {@code ContactTableModel} passed as a parameter. The parameter must be
	 * non-null.
	 * </p>
	 * 
	 * @param myTableModel
	 *            a {@code ContactTableModel} to be stored in this table
	 */
	public JContactTable(ContactTableModel myTableModel) {
		super(myTableModel);
		this.setDragEnabled(false);
		this.addMouseListener(this);
	}

	public Contact getContactAtRow(int row) {
		Contact c = new Contact();
		for (int i = 0; i < 9; i++) {
			Object v = this.getValueAt(row, i);
			switch (i) {
			case 0:
				c.setMyFirst((String) v);
				break;
			case 1:
				c.setMyMI((String) v);
				break;
			case 2:
				c.setMyLast((String) v);
				break;
			case 3:
				c.setMyEmail((String) v);
				break;
			case 4:
				c.setMyPhone((String) v);
				break;
			case 5:
				c.setMyStreetAddress((String) v);
				break;
			case 6:
				c.setMyCity((String) v);
				break;
			case 7:
				c.setMyState((String) v);
				break;
			case 8:
				c.setMyZIP((String) v);
				break;
			default:
				System.err.println("Column number requested does not exist.");
			}
		}
		return c;
	}
	
	/**
	 * <p>
	 * A method of MouseListener that catches {@code MouseEvent}s created by
	 * the user and reacts accordingly.
	 * </p>
	 * 
	 * @param arg0
	 *            the {@code MouseEvent} for which a reaction is necessary
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (this.timer.isRunning()) {
			if (this.selectionIndex == this.getSelectedRow()) {
				EmailTransmissionDialog eTrans = new EmailTransmissionDialog(
						this.getContactAtRow(this.selectionIndex));
				this.selectionIndex = -1;
			}
			this.timer.stop();
		} else {
			this.selectionIndex = this.getSelectedRow();
			this.timer.restart();
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
