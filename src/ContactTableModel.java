import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * <p>
 * The model for the data to be displayed in the {@code JContactTable} class.
 * </p>
 * 
 * @see JContactTable
 */
public class ContactTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7462013655239078316L;
	private ArrayList<Contact> myContents;

	/**
	 * <p>
	 * Creates a new {@code ContactTableModel} with the data from the
	 * {@code DataStore} singleton in this application environment. The
	 * DataStore object must have been properly initialized prior to the
	 * construction of this {@code ContactTableModel}.
	 * </p>
	 * 
	 * @see DataStore
	 */
	public ContactTableModel() {
		DataStore d = DataStore.getInstance();
		this.myContents = d.getMyContacts();
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public String getColumnName(int col) {
		String[] cNames = { "First", "MI", "Last", "Email Address",
				"Phone Number", "Street Address", "City", "State", "ZIP Code" };
		return cNames[col];
	}

	@Override
	public int getRowCount() {
		return this.myContents.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.myContents.get(rowIndex).getMyFirst();
		case 1:
			return this.myContents.get(rowIndex).getMyMI();
		case 2:
			return this.myContents.get(rowIndex).getMyLast();
		case 3:
			return this.myContents.get(rowIndex).getMyEmail();
		case 4:
			return this.myContents.get(rowIndex).getMyPhone();
		case 5:
			return this.myContents.get(rowIndex).getMyStreetAddress();
		case 6:
			return this.myContents.get(rowIndex).getMyCity();
		case 7:
			return this.myContents.get(rowIndex).getMyState();
		case 8:
			return this.myContents.get(rowIndex).getMyZIP();
		default:
			return new String("AHHHHHHHHH");
		}
	}

}
