package org.svgroz.vacationdb.datastore.api.model.cell;

/**
 * Basic cells interface
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public sealed interface Cell extends Comparable<Cell> permits BooleanCell, LongCell, DoubleCell, StringCell, EmptyCell {

}
