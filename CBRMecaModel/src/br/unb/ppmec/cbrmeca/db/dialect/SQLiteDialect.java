/*
 * The author disclaims copyright to this source code.  In place of
 * a legal notice, here is a blessing:
 * 
 *    May you do good and not evil.
 *    May you find forgiveness for yourself and forgive others.
 *    May you share freely, never taking more than you give.
 *
 */
package br.unb.ppmec.cbrmeca.db.dialect;

import java.sql.Types;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

// TODO: Auto-generated Javadoc
/**
 * The Class SQLiteDialect.
 */
public class SQLiteDialect extends Dialect {
  
  /**
   * Instantiates a new SQ lite dialect.
   */
  public SQLiteDialect() {
    registerColumnType(Types.BIT, "integer");
    registerColumnType(Types.TINYINT, "tinyint");
    registerColumnType(Types.SMALLINT, "smallint");
    registerColumnType(Types.INTEGER, "integer");
    registerColumnType(Types.BIGINT, "bigint");
    registerColumnType(Types.FLOAT, "float");
    registerColumnType(Types.REAL, "real");
    registerColumnType(Types.DOUBLE, "double");
    registerColumnType(Types.NUMERIC, "numeric");
    registerColumnType(Types.DECIMAL, "decimal");
    registerColumnType(Types.CHAR, "char");
    registerColumnType(Types.VARCHAR, "varchar");
    registerColumnType(Types.LONGVARCHAR, "longvarchar");
    registerColumnType(Types.DATE, "date");
    registerColumnType(Types.TIME, "time");
    registerColumnType(Types.TIMESTAMP, "timestamp");
    registerColumnType(Types.BINARY, "blob");
    registerColumnType(Types.VARBINARY, "blob");
    registerColumnType(Types.LONGVARBINARY, "blob");
    // registerColumnType(Types.NULL, "null");
    registerColumnType(Types.BLOB, "blob");
    registerColumnType(Types.CLOB, "clob");
    registerColumnType(Types.BOOLEAN, "integer");

    registerFunction( "concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", "") );
    registerFunction( "mod", new SQLFunctionTemplate( IntegerType.INSTANCE, "?1 % ?2" ) );
    registerFunction( "substr", new StandardSQLFunction("substr", StringType.INSTANCE) );
    registerFunction( "substring", new StandardSQLFunction( "substr", StringType.INSTANCE ) );
  }

  /**
   * Supports identity columns.
   *
   * @return true, if successful
   */
  public boolean supportsIdentityColumns() {
    return true;
  }

  /*
  public boolean supportsInsertSelectIdentity() {
    return true; // As specify in NHibernate dialect
  }
  */

  /**
   * Checks for data type in identity column.
   *
   * @return true, if successful
   */
  public boolean hasDataTypeInIdentityColumn() {
    return false; // As specify in NHibernate dialect
  }

  /*
  public String appendIdentitySelectToInsert(String insertString) {
    return new StringBuffer(insertString.length()+30). // As specify in NHibernate dialect
      append(insertString).
      append("; ").append(getIdentitySelectString()).
      toString();
  }
  */

  /**
   * Gets the identity column string.
   *
   * @return the identity column string
   */
  public String getIdentityColumnString() {
    // return "integer primary key autoincrement";
    return "integer";
  }

  /**
   * Gets the identity select string.
   *
   * @return the identity select string
   */
  public String getIdentitySelectString() {
    return "select last_insert_rowid()";
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#supportsLimit()
   */
  public boolean supportsLimit() {
    return true;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#getLimitString(java.lang.String, boolean)
   */
  protected String getLimitString(String query, boolean hasOffset) {
    return new StringBuffer(query.length()+20).
      append(query).
      append(hasOffset ? " limit ? offset ?" : " limit ?").
      toString();
  }

  /**
   * Supports temporary tables.
   *
   * @return true, if successful
   */
  public boolean supportsTemporaryTables() {
    return true;
  }

  /**
   * Gets the creates the temporary table string.
   *
   * @return the creates the temporary table string
   */
  public String getCreateTemporaryTableString() {
    return "create temporary table if not exists";
  }

  /**
   * Drop temporary table after use.
   *
   * @return true, if successful
   */
  public boolean dropTemporaryTableAfterUse() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#supportsCurrentTimestampSelection()
   */
  public boolean supportsCurrentTimestampSelection() {
    return true;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#isCurrentTimestampSelectStringCallable()
   */
  public boolean isCurrentTimestampSelectStringCallable() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#getCurrentTimestampSelectString()
   */
  public String getCurrentTimestampSelectString() {
    return "select current_timestamp";
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#supportsUnionAll()
   */
  public boolean supportsUnionAll() {
    return true;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#hasAlterTable()
   */
  public boolean hasAlterTable() {
    return false; // As specify in NHibernate dialect
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#dropConstraints()
   */
  public boolean dropConstraints() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#getAddColumnString()
   */
  public String getAddColumnString() {
    return "add column";
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#getForUpdateString()
   */
  public String getForUpdateString() {
    return "";
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#supportsOuterJoinForUpdate()
   */
  public boolean supportsOuterJoinForUpdate() {
    return false;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#getDropForeignKeyString()
   */
  public String getDropForeignKeyString() {
    throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#getAddForeignKeyConstraintString(java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], boolean)
   */
  public String getAddForeignKeyConstraintString(String constraintName,
      String[] foreignKey, String referencedTable, String[] primaryKey,
      boolean referencesPrimaryKey) {
    throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#getAddPrimaryKeyConstraintString(java.lang.String)
   */
  public String getAddPrimaryKeyConstraintString(String constraintName) {
    throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#supportsIfExistsBeforeTableName()
   */
  public boolean supportsIfExistsBeforeTableName() {
    return true;
  }

  /* (non-Javadoc)
   * @see org.hibernate.dialect.Dialect#supportsCascadeDelete()
   */
  public boolean supportsCascadeDelete() {
    return false;
  }
}