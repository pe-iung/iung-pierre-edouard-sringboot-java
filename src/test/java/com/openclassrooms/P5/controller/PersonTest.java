package com.openclassrooms.P5.controller;


import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class PersonTest {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();

    // given an invalid data for Person
    // when when add a person
    // then a validation error is raised


//    @Autowired
//    jakarta.validation.Validator validator;

//    private final Validator validator = (Validator) Validation.buildDefaultValidatorFactory().getValidator();
//    /**
//     * Simulates the behaviour of bean-validation e.g. @NotNull
//     */
//    private void validateBean(Object bean) throws AssertionError {
//        Optional<ConstraintViolation<Object>> violation = validator.validate(bean).stream().findFirst();
//        if (violation.isPresent()) {
//            throw new ValidationException(violation.get().getMessage());
//        }
//    }
}
