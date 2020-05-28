package com.epam.brest.courses.web.validators;


import com.epam.brest.courses.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.epam.brest.courses.constants.CarConstants.CAR_MODEL_SIZE;

@Component
public class CarValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "model", "model.empty");
        Car car = (Car) target;

        if (StringUtils.hasLength(car.getModel())
                && car.getModel().length() > CAR_MODEL_SIZE) {
            errors.rejectValue("model", "model.maxSize");
        }
    }
}
