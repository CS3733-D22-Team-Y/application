package edu.wpi.cs3733.d22.teamY;

public abstract class DBObject {
    public String tableName;
    public String keyAttributeName;

    public abstract String getKey();

    public abstract String getInsertQuery();

    /**
     * Returns the string needed to remove this object from the database
     * @return String SQL query to remove this object from the database
     */
    public String getRemoveQuery(){
        return "DELETE FROM " + tableName + " WHERE "+keyAttributeName+"=" + "'" + getKey() + "'";
    }

    public abstract DBObject getClone();

    public DBObject(String tableName, String keyAttributeName) {
        this.tableName = tableName;
        this.keyAttributeName = keyAttributeName;
    }

}
