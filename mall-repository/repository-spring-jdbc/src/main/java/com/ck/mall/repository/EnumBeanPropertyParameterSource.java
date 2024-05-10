package com.ck.mall.repository;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.sql.Types;

/**
 * @author Qnxy
 */
public class EnumBeanPropertyParameterSource extends BeanPropertySqlParameterSource {

    /**
     * Create a new BeanPropertySqlParameterSource for the given bean.
     *
     * @param object the bean instance to wrap
     */
    public EnumBeanPropertyParameterSource(Object object) {
        super(object);
    }

    public static EnumBeanPropertyParameterSource enumValueSource(Object source) {
        return new EnumBeanPropertyParameterSource(source);
    }

    @Override
    public Object getValue(String paramName) throws IllegalArgumentException {
        final Object value = super.getValue(paramName);

        if (value instanceof Enum<?> e) {

            // 设置当前值对应数据库中的类型
            registerSqlType(paramName, Types.TINYINT);

            // 返回枚举序号
            return e.ordinal();
        } else {
            return value;
        }
    }
}
