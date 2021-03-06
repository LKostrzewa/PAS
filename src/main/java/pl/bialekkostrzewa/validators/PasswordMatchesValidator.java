package pl.bialekkostrzewa.validators;

import pl.bialekkostrzewa.model.MyUserDetails;
import pl.bialekkostrzewa.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        //MyUserDetails user = (MyUserDetails) obj;
        //return user.getPassword().equals(user.getMatchingPassword());
        User user = (User) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
