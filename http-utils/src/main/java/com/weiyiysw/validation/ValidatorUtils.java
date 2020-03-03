package com.weiyiysw.validation;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * 参考文档：https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/?v=6.1
 *
 * @author weiyiysw
 */
public class ValidatorUtils {

    private static final Validator validator;

    private static final String VALIDATOR_BUNDLE = "i18n/validation_common";

    static {
        validator = Validation.byDefaultProvider().configure().messageInterpolator(
                new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator(VALIDATOR_BUNDLE)))
                .buildValidatorFactory().getValidator();
    }

    public static void validateEntity(Object object)
            throws Exception {
        // locale存储在session中，从session获取，下面这个是Spring的代码
        // Locale.setDefault(LocaleContextHolder.getLocale());
        // Locale.setDefault(new Locale("zh", "CN"));
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            throw new Exception(constraint.getMessage());
        }
    }

    /**
     * 分组校验
     * @param object
     * @param groups
     * @throws Exception
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws Exception {
//        Locale.setDefault(LocaleContextHolder.getLocale());
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            throw new Exception(constraint.getMessage());
        }
    }
}