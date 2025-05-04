package mate.academy.hibernate.relations.service.impl;

import mate.academy.hibernate.relations.service.ValidateService;

public abstract class AbstractService {
    protected final ValidateService validateService;

    protected AbstractService() {
        this.validateService = new ValidateServiceImpl();
    }
}
