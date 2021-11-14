package com.bedrockk.molang.runtime.value;

import java.util.Objects;

public class DoubleValue implements MoValue {

    public final static DoubleValue ZERO = new DoubleValue(0.0);
    public final static DoubleValue ONE = new DoubleValue(1.0);

    private final double value;

    public DoubleValue(Object value) {
        if (value instanceof Boolean) {
            this.value = (boolean) value ? 1.0 : 0.0;
        } else if (value instanceof Number) {
            this.value = (double) value;
        } else {
            this.value = 1.0;
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DoubleValue that = (DoubleValue) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }

    @Override
    public Double value() {
        return value;
    }

    @Override
    public String asString() {
        return Double.toString(value);
    }

    @Override
    public double asDouble() {
        return value;
    }

    @Override
    public String toString()
    {
        return "DoubleValue{" + "value=" + value + '}';
    }
}
