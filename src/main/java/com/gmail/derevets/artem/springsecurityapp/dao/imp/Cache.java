package com.gmail.derevets.artem.springsecurityapp.dao.imp;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Cache {

    private static HashMap<String, Object> hashMap = new LinkedHashMap<>();


    public static void addObjectToCache(Object object) {
        try {
            String id =  object.getClass().getMethod("getId").invoke(object).toString();
            hashMap.put(id, object);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    public static void removeFromCache(String object_id) {
        hashMap.remove(object_id);
    }

    public static Object getObjectFromCache(String id) {
        return hashMap.get(id);

    }

    public static HashMap<String, Object> getHashMap() {
        return hashMap;
    }

}
