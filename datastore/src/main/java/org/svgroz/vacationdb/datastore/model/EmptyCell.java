package org.svgroz.vacationdb.datastore.model;

/**
 * Cell that represents empty values
 */
public class EmptyCell implements CellType {

    private static final EmptyCell INSTANCE = new EmptyCell();

    private EmptyCell() {
    }

    public static EmptyCell getInstance() {
        return INSTANCE;
    }

    /**
     * @param cell instance of {@link CellType }
     * @return true if cell is empty
     */
    public static boolean isEmpty(CellType cell) {
        return cell == INSTANCE;
    }

    @Override
    public int compareTo(final CellType o) {
        return isEmpty(o) ? 0 : -1;
    }

    @Override
    public String toString() {
        return "EmptyCell{}";
    }
}
