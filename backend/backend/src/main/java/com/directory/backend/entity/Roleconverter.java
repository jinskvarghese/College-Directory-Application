package com.directory.backend.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class Roleconverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.name();  // Convert Enum to String for the database
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Role.valueOf(dbData);  // Convert String from DB to Enum
    }
}