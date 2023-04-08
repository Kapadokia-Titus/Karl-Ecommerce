package com.syqu.shop.validator;

import com.syqu.shop.domain.ShopOrder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ShopOrder.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ShopOrder shopOrder = (ShopOrder) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "card_number","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cvv", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.not_empty");

        // Name must have from 2 characters to 32
        if (shopOrder.getFirst_name().length() <= 1) {
            errors.rejectValue("first_name", "product.error.name.less_2");
        }
        if (shopOrder.getFirst_name().length() > 32) {
            errors.rejectValue("first_name", "product.error.name.over_32");
        }
        if (shopOrder.getLast_name().length() <= 1) {
            errors.rejectValue("last_name", "product.error.name.less_2");
        }
        if (shopOrder.getLast_name().length() > 32) {
            errors.rejectValue("last_name", "product.error.name.over_32");
        }

        // card must be between 10 characters to 30
        // Check if the card value is a number
        if (!shopOrder.getCard_number().matches("\\d+")) {
            errors.rejectValue("card_number", "card.error.cardno.number");
        }
        if (shopOrder.getCard_number().length() <= 10) {
            errors.rejectValue("card_number", "card.error.cardno.less_10");
        }
        if (shopOrder.getCard_number().length() > 30) {
            errors.rejectValue("card_number", "card.error.cardno.over_30");
        }
        if (!shopOrder.getCvv().matches("\\d+")) {
            errors.rejectValue("cvv", "card.error.cardno.number");
        }
        if (shopOrder.getCvv().length() != 3 ) {
            errors.rejectValue("cvv", "card.error.cvv");
        }

    }
}