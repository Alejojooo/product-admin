package com.enpresa.productadmin.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Alejo
 */
public class Mapper {

    public static Map<String, String> getMap(Class clase) {
        Map<String, String> mapRepresentation = new HashMap<>();

        List<String> atributos = Arrays.stream(clase.getDeclaredFields())
                .map(field -> field.getName())
                .collect(Collectors.toList());
        for (String atributo : atributos) {
            mapRepresentation.put(atributo, "");
        }
        return mapRepresentation;
    }

}
