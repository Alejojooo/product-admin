package com.enpresa.productadmin.modelo.dto;

import java.lang.reflect.Field;

/**
 *
 * @author Alejo
 */
public abstract class DTO {

    public String[] getAttributeValues() {
        Field[] fields = this.getClass().getDeclaredFields();
        String[] values = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            try {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(this);
                values[i] = value != null ? value.toString() : "";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return values;
    }
}
