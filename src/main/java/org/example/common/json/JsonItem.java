package org.example.common.json;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Модель Json для ответа сервера клиенту.
 *
 * @param <T>
 */
@Data
public class JsonItem<T> {

    private Map<String, String> errors = new HashMap<>();

    private String message;

    private boolean success;

    private T data;

    private boolean editable;

    private Integer statusCode;

    public JsonItem(boolean success, Map<String, String> errors, T data) {
        this.success = success;
        this.errors = errors;
        this.data = data;
    }

    public JsonItem(boolean success, Map<String, String> errors, T data, boolean editable) {
        this.errors = errors;
        this.success = success;
        this.data = data;
        this.editable = editable;
    }

    public JsonItem(T data, Integer statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }


    public JsonItem(boolean success, Integer statusCode, T data, Map<String, String> errors, String message) {
        this.errors = errors;
        this.success = success;
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public JsonItem() {
    }
}
