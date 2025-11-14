package Validators;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds validation results
 * Similar to Laravel's Validator class that holds errors
 */
public class ValidationResult {
    private final List<String> errors;

    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors); // Return a copy for immutability
    }

    public String getErrorMessage() {
        return String.join(", ", errors);
    }
}

