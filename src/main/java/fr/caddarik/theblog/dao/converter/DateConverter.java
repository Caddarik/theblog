/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.caddarik.theblog.dao.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.AttributeConverter;

/**
 *
 * @author cedric
 */
public class DateConverter implements AttributeConverter<Date, String> {

    private final String pattern = "yyyy-MM-dd HH:mm:ss";
    
    private final DateFormat format = new SimpleDateFormat(pattern);

    @Override
    public String convertToDatabaseColumn(Date date) {
        if (date == null) {
            return null;
        }
        return format.format(date);
    }

    @Override
    public Date convertToEntityAttribute(String cell) {
        if (cell == null) {
            return null;
        }
        try {
            return format.parse(cell);
        } catch (ParseException ex) {
            throw new IllegalArgumentException(cell + " can't parse with " + pattern, ex);
        }
    }

}
