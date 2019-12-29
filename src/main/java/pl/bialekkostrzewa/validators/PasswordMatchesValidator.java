package pl.bialekkostrzewa.validators;

import pl.bialekkostrzewa.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        User user = (User) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}