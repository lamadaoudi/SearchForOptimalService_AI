package classes;

public class HeuristicNode {
    double value;
    int row;
    int column;

    public HeuristicNode(double value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
    }

    public double getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "HeuristicNode{" +
                "value=" + value +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
