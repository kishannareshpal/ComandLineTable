/**
 * POJO Model for indual table rows.
 * 
 * @author Kishan Nareshpal [https://github.com/kishannareshpal]
 * @version 1.0
 */
class Row {
    private String title, description;
    private boolean isSeparator;

    public Row(boolean isSeparator) {
        this.isSeparator = isSeparator;
    }

    public Row(String description) {
        this.isSeparator = false;
        this.title = null;
        this.description = description;
    }

    public Row(String title, String description) {
        this.isSeparator = false;
        this.title = title;
        this.description = description;
    }

    public boolean isSeparator() {
        return isSeparator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}