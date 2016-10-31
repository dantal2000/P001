package GUI001_f.Result002_f;

/**
 * Created by Даниил on 30.10.2016.
 */
class TableRow {
    private String option, value;

    public TableRow() {
    }

    public TableRow(String option, String value) {

        this.option = option;
        this.value = value;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
