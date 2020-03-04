/**
 * A class for printing a basic comand line table on the console.
 * Example Output: 
   +-------------------------------+
   | Name        | George Heinz    |
   | Country     | Mozambique      |
   | Age         | 21              |
   +-------------------------------+
   | Is Approved | Yes             |
   +-------------------------------+
 *
 * @author Kishan Nareshpal [https://github.com/kishannareshpal]
 * @version 1.0
 */

import java.util.*;
import java.util.List;
import models.Row;
import models.Dimension;

public class VerticalComandLineTable {
    // Static Vars
    private static String WHITESPACE = " ";
    private static String TABLE_UI_HORIZONTAL_SEP = "-";
    private static String TABLE_UI_CORNER = "+";
    private static String TABLE_UI_VERTICAL_SEP = "|";

    // Variables
    List<Row> rowsList;
    int gap = 3; // used to add some whitespace at the end of the description column on every row before closing the table with TABLE_UI_HORIZONTAL_SEP.

    // Constructor
    public VerticalComandLineTable() {
        this.rowsList = new ArrayList<>();
    }

    // Methods
    /**
     * Add a new Row with title and description;
     * Outputs:
       +------------------------+
       | TITLE | DESCRIPTION    |
       +------------------------+

     * @param title the title. first column.
     * @param description the description. second column.
     */
    public VerticalComandLineTable addRow(String title, String description) {
        Row row = new Row(title, description);
        rowsList.add(row);
        return this;
    }

    /**
     * Add a new Row with description only;
     * Outputs:
       +------------------------+
       | DESCRIPTION ONLY       |
       +------------------------+

     * @param description the description. second column.
     */
    public VerticalComandLineTable addRow(String description) {
        Row row = new Row(description);
        rowsList.add(row);
        return this;
    }

    /**
     * Add a separator to the table;
     * Outputs:
       +------------------------+
     */
    public VerticalComandLineTable addSeparator() {
        Row row = new Row(true);
        rowsList.add(row);
        return this;
    }

    /**
     * Prints the table to the console.
     * Must be called last. Or after an update to the table's row.
     */
    public VerticalComandLineTable show() {
        Dimension tableDimen = getTableDimensions();
        int tableWidth = tableDimen.getWidth();
        String table_ui_line = TABLE_UI_CORNER + repeater(tableWidth - 2, TABLE_UI_HORIZONTAL_SEP) + TABLE_UI_CORNER; // -2 vertical seps: at the start and at the end of the row, to be replaced with the TABLE_UI_CORNER.

        Dimension rawDimen = calculateTableDimension();
        int titleWidth = rawDimen.getTitleWidth();
        int descWidth  = rawDimen.getDescWidth();

        System.out.println(table_ui_line);
        for (Row row: rowsList) {
            // print the row
            if (row.isSeparator()) {
                System.out.println(table_ui_line);
                continue;
            }

            String title = row.getTitle();
            String titleCol;
            if (title != null) {
                titleCol = " " + title + repeater((titleWidth) - title.length(), WHITESPACE) + " ";

            } else {
                // has only description set. title is null/not set.
                titleCol = repeater((titleWidth) + 3, WHITESPACE); 
                // +2 (out of +3) to compensate the start and end whitespaces {@see the if statement start and end " "}
                // and +1 to compensate for the TABLE_UI_VERTICAL_SEP, used to separate the title and description.
            }

            String desc  = row.getDescription();
            String descCol  = " " + desc + repeater((descWidth+gap) - desc.length(), WHITESPACE) + " ";

            String theRow;
            if (title != null) {
                // has title and description set.
                theRow = TABLE_UI_VERTICAL_SEP + titleCol + TABLE_UI_VERTICAL_SEP + descCol + TABLE_UI_VERTICAL_SEP;

            } else {
                // has only description set. title is null/not set.
                theRow = TABLE_UI_VERTICAL_SEP + descCol + titleCol + TABLE_UI_VERTICAL_SEP;
            }
            System.out.println(theRow);
        }
        System.out.println(table_ui_line);
        return this;
    }


    /**
     * Returns the final table dimens (eif
     * @return the dimensions of the final table.
     */
    public Dimension getTableDimensions() {
        Dimension rawDimen = calculateTableDimension();
        int titleColWidth = 1 + rawDimen.getTitleWidth() + 1; // +1 whitespaces, at the start and in the end of the column. ignores the vertical sep.
        int descColWidth  = 1 + rawDimen.getDescWidth() + gap + 1; // +1 whitespaces: at the start at in the end; +gap whitespace: at the end of the desc column. ignores the vertical sep.

        int width = 1 + titleColWidth + 1 + descColWidth + 1; // +1 vertical seps: at the end, at the middle, and at the end of the row.
        int height = rowsList.size();

        return new Dimension(width, height);
    }


    /**
     * ONLY TO BE USED INTERNALLY, FOR CREATING ROWS.
     * Calculates the raw Dimension of the table's columns width, and its height.
     * Unit: Chars.
     *
     * @return
     */
    private Dimension calculateTableDimension() {
        int titleWidth = 0;
        int descWidth = 0;

        for (Row row: rowsList) {
            if (row.isSeparator()) continue;

            int newTitleWidth = (row.getTitle() != null) ? row.getTitle().length() : 0;
            int newDescWidth  = row.getDescription().length();

            if (newTitleWidth > titleWidth) {
                // if this title has more chars than the last maximum, update the number of chars.
                titleWidth = newTitleWidth;
            }

            if (newDescWidth > descWidth) {
                // if this title has more chars than the last maximum, update the number of chars.
                descWidth = newDescWidth;
            }
        }

        int height = rowsList.size();
        return new Dimension(titleWidth, descWidth, height);
    }

    /**
     * Generates n number of String.
     *
     * @param numOfRepetitions number of times to repeat
     * @param strToRepeat the string to be repeated
     * @return repeated string.
     */
    private static String repeater(int numOfRepetitions, String strToRepeat) {
        StringBuilder chars = new StringBuilder();
        for (int i = 0; i < numOfRepetitions; i++) {
            chars.append(strToRepeat);
        }
        return chars.toString();
    }

}