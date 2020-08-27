package org.svgroz.vacationdb.datastore.api.model.cell;

import org.svgroz.vacationdb.datastore.api.model.DataType;

/**
 * Basic cells interface
 *
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface Cell extends Comparable<Cell> {

    /**
     * @return supported type
     */
    DataType supportedType();
}
