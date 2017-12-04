package pl.gov.coi.blox.validation;

public class ValidationPredicates {

    public static void lenght (Integer min, Integer max, String value, ValidationMessage message){
        if(value == null || value.length() < min || value.length() > max){
            throw new ValidationException(message);
        }
    }

    public static void emailMatcher (String value, ValidationMessage message){
        String matcher = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (!value.matches(matcher)) {
            throw new ValidationException(message);
        }
    }

    public static void passwordMatcher(String password, String confirmedPassword, ValidationMessage message) {
        if (!(password.equals(confirmedPassword))) {
            throw new ValidationException(message);
        }
    }

    public static void passwordRequirementsMatcher(String password, ValidationMessage message) {
        String matcher = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (!(password.matches(matcher))) {
            throw new ValidationException(message);
        }
    }
}
