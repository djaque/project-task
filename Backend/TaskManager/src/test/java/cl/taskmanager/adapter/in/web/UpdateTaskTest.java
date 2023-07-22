package cl.taskmanager.adapter.in.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateTaskTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void subjectCannotBeBlank() {
        // Arrange
        UpdateTask update = new UpdateTask();
        update.setSubject(""); // Set a blank subject

        // Act
        Set<ConstraintViolation<UpdateTask>> violations = validator.validate(update);

        // Assert
        assertEquals(2, violations.size());
    }

    @Test
    void subjectMustHaveBetween2And30Chars() {
        // Arrange
        UpdateTask update = new UpdateTask();
        update.setSubject("A"); // Set a subject with less than 2 characters

        // Act
        Set<ConstraintViolation<UpdateTask>> violations = validator.validate(update);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("Subject must have between 2 and 30 chars", violations.iterator().next().getMessage());

        // Arrange
        update.setSubject("This subject has more than 30 characters, it's too long"); // Set a subject with more than 30 characters

        // Act
        violations = validator.validate(update);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("Subject must have between 2 and 30 chars", violations.iterator().next().getMessage());
    }

    @Test
    void validSubjectShouldPassValidation() {
        // Arrange
        UpdateTask update = new UpdateTask();
        update.setSubject("Valid Subject");

        // Act
        Set<ConstraintViolation<UpdateTask>> violations = validator.validate(update);

        // Assert
        assertEquals(0, violations.size());
    }
}
