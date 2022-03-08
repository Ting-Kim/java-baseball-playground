package converter;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class BooleanConverter implements ArgumentConverter {
    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                    "The argument should be a string: " + source);
        }
        try {
            boolean isTrue = Boolean.parseBoolean((String)source);
            return isTrue;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}
