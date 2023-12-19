package newLog.newLog.exception;

import newLog.newLog.dto.AddressSearchDto;

public class RequestValidator {

    public static void validateSearchDto(AddressSearchDto exampleDto) {
        if (exampleDto == null) {
            throw new IllegalArgumentException("In case of search the request body cannot be empty!");
        }

    }
}
