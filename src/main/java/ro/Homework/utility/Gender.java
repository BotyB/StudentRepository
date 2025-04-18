package ro.Homework.utility;

public enum Gender {
    MALE("M"), FEMALE("F");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    public static Gender fromString(String input) throws StudentValidationException {
        if (input == null || input.trim().isEmpty()) {
            throw new StudentValidationException("Gender cannot be empty");
        }

        String normalized = input.trim().toUpperCase();
        for (Gender gender : values()) {
            if (gender.code.equals(normalized)) {
                return gender;
            }
        }
        throw new StudentValidationException("Invalid gender. Use M/F");
    }
}