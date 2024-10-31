package org.example.services;

import org.example.exception.ServiceException;

import java.util.logging.Logger;

public abstract class ServiceDecorator<T> {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract void performOperation(String operationName, Runnable operation) throws ServiceException;

    protected abstract boolean isValid(T entity);

    protected void logAndValidate(String operationName, T entity, Runnable operation) throws ServiceException {
        logger.info("Performing " + operationName + ": " + entity);
        if (!isValid(entity)) {
            throw new ServiceException("Invalid " + entity.getClass().getSimpleName() + " data");
        }
        performOperation(operationName, operation);
        logger.info(operationName + " completed successfully");
    }
}
