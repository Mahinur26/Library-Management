import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

//This class is used to create a helper method for the createTable() method in the LibraryManager class
/*
All the values in the table are rendered in the default way, except for the status column
If the status = "Available" then that cell background is set to a light green color
If the status = "Available" then that cell background is set to a light red color
*/
//This makes the table visually appealing and easier to understand
class StatusCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //Uses the default renderer so the cell behaves the same as the rest of the cells in the JTable
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Checks the value in the cell and sets the background color accordingly
        if ("Available".equals(value)) {
            // Light green background color
            cell.setBackground(new Color(144, 238, 144));
            // Text color
            cell.setForeground(Color.BLACK);
        } else if ("Unavailable".equals(value)) {
            // Light red background
            cell.setBackground(new Color(255, 102, 102));
            // Text color
            cell.setForeground(Color.BLACK);
  }

        // Changes the colors of the status cell in the row
        if (isSelected) {
            cell.setBackground(table.getSelectionBackground());
            cell.setForeground(table.getSelectionForeground());
        }
        //Returns the component
        return cell;
    }
}
