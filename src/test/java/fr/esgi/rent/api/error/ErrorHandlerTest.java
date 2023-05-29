package fr.esgi.rent.api.error;

import fr.esgi.rent.exception.NotFoundRentalPropertyException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorHandlerTest {

    @Test
    void shouldHandleNotFoundRentalPropertyException() {
        String message = "Message";

        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleNotFoundRentalPropertyException(new NotFoundRentalPropertyException(message));

        assertThat(errorDto.message()).isEqualTo(message);
    }

}
