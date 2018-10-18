package com.smart.spel;


import com.smart.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class CompilerSample {

    public static void main(String[] args) {
        User user = new User();
        //创建解析配置
        /* 默认情况下， SpEL 表达式只有在求值时才会进行表达式计算，所以表达式可以在运行时进行动态修改。但如果一个表达式被重复调用的次数很多，
        那么就必须使用SpelCompiler 编译器来保证性能。 */
        SpelParserConfiguration configuration = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, //立即启用编译,实际上只有在第二次getValue启用
                CompilerSample.class.getClassLoader());
        //创建解析器
        EvaluationContext context = new StandardEvaluationContext(user);
        //创建取值上下文
        SpelExpressionParser parser = new SpelExpressionParser(configuration);
        //表达式
        String expression = "isVipMember('tom') && isVipMember('jony')";
        //解析表达式
        SpelExpression spelExpression = parser.parseRaw(expression);
        System.out.println(spelExpression.getValue(context));
        System.out.println(spelExpression.getValue(context));
    }
}
