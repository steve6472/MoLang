package com.bedrockk.molang.runtime;

import com.bedrockk.molang.runtime.struct.MoStruct;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Value
public class MoLangEnvironment implements MoValue {

    ConcurrentMap<String, MoStruct> structs = new ConcurrentHashMap<>();

    public MoValue getValue(String name) {
        return getValue(name, MoParams.EMPTY);
    }

    public MoValue getValue(String name, MoParams params) {
        int indexOfDot = name.indexOf('.');
        String main;
        if (indexOfDot == -1)
            main = name;
        else
            main = name.substring(0, indexOfDot);

        if (structs.containsKey(main)) {
            return structs.get(main).get(name.substring(indexOfDot + 1), params);
        }

        return new DoubleValue(0.0);
    }

    public void setValue(String name, MoValue value) {
        int indexOfDot = name.indexOf('.');
        String main;
        if (indexOfDot == -1)
            main = name;
        else
            main = name.substring(0, indexOfDot);

        if (structs.containsKey(main)) {
            structs.get(main).set(name.substring(indexOfDot + 1), value);
        }
    }

    @Override
    public Object value() {
        return this;
    }
}
