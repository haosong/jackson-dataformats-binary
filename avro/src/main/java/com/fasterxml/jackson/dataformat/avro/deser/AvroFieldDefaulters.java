package com.fasterxml.jackson.dataformat.avro.deser;

import org.codehaus.jackson.JsonNode;

/**
 * Factory class for various default providers
 */
public class AvroFieldDefaulters
{
    public static AvroFieldWrapper createDefaulter(String name,
            JsonNode defaultAsNode) {
        switch (defaultAsNode.asToken()) {
        case VALUE_TRUE:
            return new ScalarDefaults.BooleanDefaults(name, true);
        case VALUE_FALSE:
            return new ScalarDefaults.BooleanDefaults(name, false);
        case VALUE_NULL:
            return new ScalarDefaults.NullDefaults(name);
        case VALUE_NUMBER_FLOAT:
            switch (defaultAsNode.getNumberType()) {
            case FLOAT:
                return new ScalarDefaults.FloatDefaults(name, (float) defaultAsNode.asDouble());
            case DOUBLE:
            case BIG_DECIMAL: // TODO: maybe support separately?
            default:
                return new ScalarDefaults.DoubleDefaults(name, defaultAsNode.asDouble());
            }
        case VALUE_NUMBER_INT:
            switch (defaultAsNode.getNumberType()) {
            case INT:
                return new ScalarDefaults.FloatDefaults(name, defaultAsNode.asInt());
            case BIG_INTEGER: // TODO: maybe support separately?
            case LONG:
            default:
                return new ScalarDefaults.FloatDefaults(name, defaultAsNode.asLong());
            }
        case VALUE_STRING:
            return new ScalarDefaults.StringDefaults(name, defaultAsNode.asText());
        case START_ARRAY:
            // !!! TODO
            break;
        case START_OBJECT:
            // !!! TODO
            break;
        default:
        }

        // TODO:
        throw new IllegalArgumentException("Unsupported default type: "+defaultAsNode.asText());
    }
}
