package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Parselet;
import com.bedrockk.molang.parser.expression.BreakExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class BreakParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        return new BreakExpression();
    }
}
