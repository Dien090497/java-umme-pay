package vn.unicloud.umeepay.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Using to trim String data in request body
 * Author: lnson
 * Usage:
 *
 * @JsonDeserialize(using = TrimString.class)
 * private String email;
 *
 */
public class TrimString extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctx)
            throws IOException {
        String str = p.getText();
        try {
            return str.trim();
        } catch (Exception e) {
            return null;
        }
    }
}