/**
 * POJO Model for the table dimension.
 * 
 * @author Kishan Nareshpal [https://github.com/kishannareshpal]
 * @version 1.0
 */
class Dimension {
    // Unit: Chars
    private int titleWidth, descWidth;
    private int width; // full width;
    private int height; // full height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(int titleWidth, int descWidth, int height) {
        this.width = titleWidth + descWidth;
        this.titleWidth = titleWidth;
        this.descWidth = descWidth;
        this.height = height;
    }

    public int getTitleWidth() {
        return titleWidth;
    }

    public int getDescWidth() {
        return descWidth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}