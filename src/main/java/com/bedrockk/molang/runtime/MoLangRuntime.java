package com.bedrockk.molang.runtime;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.runtime.struct.ArrayStruct;
import com.bedrockk.molang.runtime.struct.ContextStruct;
import com.bedrockk.molang.runtime.struct.VariableStruct;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;

import java.util.*;

public class MoLangRuntime {
    private final MoLangEnvironment environment = new MoLangEnvironment();

    public MoLangRuntime() {
        environment.getStructs().put("math", MoLangMath.LIBRARY);
        environment.getStructs().put("temp", new VariableStruct());
        environment.getStructs().put("variable", new VariableStruct());
        environment.getStructs().put("array", new ArrayStruct());
    }

    public MoValue execute(Expression expression) {
        return execute(Collections.singletonList(expression));
    }

    public MoValue execute(List<Expression> expressions) {
        return execute(expressions, new HashMap<>());
    }

    public MoValue execute(List<Expression> expressions, Map<String, MoValue> context) {
        environment.getStructs().put("context", new ContextStruct(context));

        MoValue result = DoubleValue.ZERO;
        var scope = new MoScope();
        for (Expression expression : new ArrayList<>(expressions)) {
            if (scope.getReturnValue() != null) {
                break;
            }
            result = expression.evaluate(scope, environment);
        }

        environment.getStructs().get("temp").clear();
        environment.getStructs().remove("context");

        return scope.getReturnValue() != null ? scope.getReturnValue() : result;
    }

    public MoLangEnvironment getEnvironment() {
        return environment;
    }
}
