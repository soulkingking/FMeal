package com.mj.core.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mj.core.util.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * 自定义GsonConverter,支持String.
 * Created By: Robin Yang
 * Created At: 2015-01-25 15:40
 */

public class CustomGsonConverter extends GsonConverter {
    private boolean isResponseTypeString;

    public CustomGsonConverter(Gson gson) {
        super(gson);
    }

    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        Object output = null;
        L.d(type);
        if (type.equals(new TypeToken<String>() {
        }.getType())) {
            isResponseTypeString = true;
            try {
                output = fromStream(typedInput.in());
            } catch (IOException ignored) {/*NOP*/ }
        } else {
            output = super.fromBody(typedInput, type);
        }


        return output;
    }

    @Override
    public TypedOutput toBody(Object o) {
        if (isResponseTypeString)
            return null;
        else
            return super.toBody(o);
    }

    public static String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}