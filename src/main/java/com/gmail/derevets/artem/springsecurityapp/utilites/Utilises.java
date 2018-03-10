package com.gmail.derevets.artem.springsecurityapp.utilites;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utilises {


    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }


    public static String timeStamp() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = getCurrentTimeStamp().toString();
        Date dateTime = format.parse(timeStamp);
        String t = format.format(dateTime);
        return t;
    }
    private static Timestamp getCurrentTimeStamp() {

        Date today = new Date();

        return new Timestamp(today.getTime());

    }


}
