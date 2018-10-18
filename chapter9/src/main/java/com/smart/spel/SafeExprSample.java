package com.smart.spel;

import com.smart.PlaceOfBirth;
import com.smart.User;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;

public class SafeExprSample {
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("tom");
        user.setLastVisit(new Date());
        user.setCredits(100);
        user.setPlaceOfBirth(new PlaceOfBirth("中国","厦门"));

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(user);
        String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
        System.out.println(city); // 厦门
        user.setPlaceOfBirth(null);
        //即便是PlaceOfBirth设置为null,也不会报出空指针异常,和groovy一样,不会报出空指针异常,前提得有'?'符号才行
        city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
        System.out.println(city); // null
    }
}
