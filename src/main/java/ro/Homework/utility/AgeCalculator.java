package ro.Homework.utility;

import java.time.LocalDate;
import java.time.Period;

public final class AgeCalculator {
    public static int calculateAge(LocalDate birthDate) throws StudentValidationException {
        if (birthDate == null) {
            throw new StudentValidationException("Birth date cannot be null");
        }

        LocalDate currentDate = LocalDate.now();
        if (birthDate.isAfter(currentDate)) {
            throw new StudentValidationException("Birth date cannot be in the future");
        }

        return Period.between(birthDate, currentDate).getYears();
    }
}
