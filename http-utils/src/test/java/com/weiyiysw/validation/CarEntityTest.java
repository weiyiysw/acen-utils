package com.weiyiysw.validation;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author yishiwei
 * @Date 2020/3/2
 */
public class CarEntityTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        Locale.setDefault(Locale.ENGLISH);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void manufacturerIsNull() {
        Locale.setDefault(new Locale("zh", "CN"));
        CarEntity carEntity = new CarEntity( null, "DD-AB-123", 4 );
        try {
            ValidatorUtils.validateEntity(carEntity);
        } catch (Exception e) {
            assertEquals("车辆制造商不能为空！！！", e.getMessage());
        }

//        Set<ConstraintViolation<CarEntity>> constraintViolations =
//                validator.validate( carEntity );
//
//        assertEquals( 1, constraintViolations.size() );
//        assertEquals( "must not be null", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void licensePlateTooShort() {
        CarEntity carEntity = new CarEntity( "Morris", "D", 4 );

        Set<ConstraintViolation<CarEntity>> constraintViolations =
                validator.validate( carEntity );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 2 and 14",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void seatCountTooLow() {
        CarEntity carEntity = new CarEntity( "Morris", "DD-AB-123", 1 );

        Set<ConstraintViolation<CarEntity>> constraintViolations =
                validator.validate( carEntity );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "must be greater than or equal to 2",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void carIsValid() {
        CarEntity carEntity = new CarEntity( "Morris", "DD-AB-123", 2 );

        Set<ConstraintViolation<CarEntity>> constraintViolations =
                validator.validate( carEntity );

        assertEquals( 0, constraintViolations.size() );
    }
}