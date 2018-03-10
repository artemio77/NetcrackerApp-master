package com.gmail.derevets.artem.springsecurityapp.utilites;


public class Queries {

    public static String selectIdQuery() {
        return "select object_id from objects where name = ?";
    }


    public static String insertIntoObjectQuery() {
        return "INSERT INTO OBJECTS(NAME, OBJECT_TYPE_ID) VALUES(?, ?)";
    }

    public static String deleteObjectQuery() {
        return "delete from OBJECTS where OBJECT_ID = ?";
    }

    public static String deleteParamsQuery() {
        return "delete from PARAMS where OBJECT_ID = ?";
    }

    public static String createNumberValueQuery() {
        return "INSERT INTO PARAMS (NUMBER_VALUE, ATTR_ID, OBJECT_ID) VALUES (?, ?, ?)";

    }

    public static String createTextValueQuery() {
        return "INSERT INTO PARAMS (TEXT_VALUE, ATTR_ID, OBJECT_ID) VALUES (?, ?, ?)";

    }

    public static String createDateValueParamsQuery() {
        return "INSERT INTO PARAMS (DATA_VALUE, ATTR_ID, OBJECT_ID) VALUES " +
                "                (TO_DATE( ?, 'YYYY-MM-DD HH24:MI:SS') , ?, ?)";
    }

    public static String updateObjectNameQuery() {
        return "UPDATE OBJECTS SET NAME = ?"
                + "WHERE OBJECT_ID = ? AND OBJECT_TYPE_ID = ?";
    }

    public static String updateTextValueQuery() {
        return "UPDATE PARAMS SET TEXT_VALUE = ? "
                + "WHERE OBJECT_ID = ? AND ATTR_ID = ?";
    }

    public static String updateNumberValueQuery() {
        return "UPDATE PARAMS SET NUMBER_VALUE = ? "
                + "WHERE OBJECT_ID = ? AND ATTR_ID = ?";
    }

    public static String updateDataValueQuery() {
        return "UPDATE PARAMS SET DATA_VALUE = ? "
                + "WHERE OBJECT_ID = ? AND ATTR_ID = ?";
    }

    public static String selectByObjectId() {
        return "select  o.object_id, attr.attr_id, o.NAME OBJECT_NAME,o.OBJECT_TYPE_ID, attr.name ATTR_NAME , (NVL(p.TEXT_VALUE ,NVL (TO_CHAR(p.NUMBER_VALUE), NVL(TO_CHAR(p.DATA_VALUE),null)))) VALUE\n" +
                "                          from objects o\n" +
                "                                         inner join attributes attr on attr.object_type_id = o.OBJECT_TYPE_ID\n" +
                "                                                left join params p on p.attr_id = attr.attr_id\n" +
                "                                                and p.object_id = o.object_id\n" +
                "                                                where o.OBJECT_ID =";
    }

    public static String selectByObjectType() {
        return "select  o.object_id, attr.attr_id, o.NAME OBJECT_NAME,o.OBJECT_TYPE_ID, attr.name ATTR_NAME ,\n" +
                "                 (NVL(p.TEXT_VALUE ,NVL (TO_CHAR(p.NUMBER_VALUE), NVL(TO_CHAR(p.DATA_VALUE,'yyyy-MM-dd HH:mm:ss'),null)))) VALUE\n" +
                "                                          from objects o\n" +
                "                                                         inner join attributes attr on attr.object_type_id = o.OBJECT_TYPE_ID\n" +
                "                                                                left join params p on p.attr_id = attr.attr_id\n" +
                "                                                                and p.object_id = o.object_id\n" +
                "                                                                where  o.OBJECT_TYPE_ID =?\n" +
                "                                                    ORDER BY o.OBJECT_ID, attr.ATTR_ID";
    }

    public static String selectObjectType() {
        return "select  o.object_id, attr.attr_id, o.NAME OBJECT_NAME,o.OBJECT_TYPE_ID, attr.name ATTR_NAME ,\n" +
                "                 (NVL(p.TEXT_VALUE ,NVL (TO_CHAR(p.NUMBER_VALUE), NVL(TO_CHAR(p.DATA_VALUE,'yyyy-MM-dd HH:mm:ss'),null)))) VALUE\n" +
                "                                          from objects o\n" +
                "                                                         inner join attributes attr on attr.object_type_id = o.OBJECT_TYPE_ID\n" +
                "                                                                left join params p on p.attr_id = attr.attr_id\n" +
                "                                                                and p.object_id = o.object_id\n" +
                "                                                                where  o.OBJECT_TYPE_ID =1\n" +
                "                                                    ORDER BY o.OBJECT_ID, attr.ATTR_ID";
    }

    public static String SelectIdQuery() {
        return "SELECT object_id \n" +
                "from OBJECTS\n" +
                "where NAME = ?";
    }

    public static String selectByName() {
        return "select  o.object_id, attr.attr_id, o.NAME OBJECT_NAME,o.OBJECT_TYPE_ID, attr.name ATTR_NAME , " +
                "(NVL(p.TEXT_VALUE ,NVL (TO_CHAR(p.NUMBER_VALUE), NVL(TO_CHAR(p.DATA_VALUE),null)))) VALUE\n" +
                "          from objects o\n" +
                "                         inner join attributes attr on attr.object_type_id = o.OBJECT_TYPE_ID\n" +
                "                                left join params p on p.attr_id = attr.attr_id\n" +
                "                                and p.object_id = o.object_id\n" +
                "                                where o.OBJECT_TYPE_ID = 3 and o.NAME =";

    }

    public static String selectCountOfObjects() {
        return "select COUNT(*) count\n" +
                "from OBJECTS\n" +
                "where OBJECT_TYPE_ID = ";
    }
}
