// Define a grammar called Hello
grammar VQL;

@header{
    package org.svgroz.vacationdb.parser;
}


create_table_name: 'CREATE' 'TABLE' ID;
create_table_columns: '(' (column ',')* column ')';
column: COLUMN_TYPE ID;
create_table: create_table_name create_table_columns ';'? EOF;

expression: create_table;

COLUMN_TYPE: 'BOOLEAN' | 'LONG' | 'DOUBLE' | 'STRING';
ID: [A-Z]+;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
