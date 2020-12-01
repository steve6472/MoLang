package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.InfixParselet;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.expression.AssignExpression;
import com.bedrockk.molang.parser.tokenizer.Token;

public class AssignParselet implements InfixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        return new AssignExpression(leftExpr, parser.parseExpression());
    }
}
