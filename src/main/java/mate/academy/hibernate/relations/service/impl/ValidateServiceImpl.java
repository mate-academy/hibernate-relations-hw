package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.service.ValidateService;

public class ValidateServiceImpl implements ValidateService {
    @Override
    public void validateNotNull(Object value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
