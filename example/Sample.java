import com.kishannareshpal.commandlinetable.VerticalComandLineTable;

public class Sample {
    public static void main(String[] args) {
        VerticalComandLineTable vclt = new VerticalComandLineTable();
        vclt.addRow("GEORGE HEINZ RESULT");
        vclt.addSeparator();
        vclt.addRow("Name", "George Heinz");
        vclt.addRow("Country", "Mozambique");
        vclt.addRow("Age", "21");
        vclt.addSeparator();
        vclt.addRow("Is Approved", "Yes");
        vclt.show();

    }
}