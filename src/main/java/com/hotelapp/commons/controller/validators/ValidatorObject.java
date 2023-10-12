package com.hotelapp.commons.controller.validators;

public class ValidatorObject {
    private String object;
    private String message;
    private String field;

    public String getObject() {
        return object;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    public static final class ValidatorObjectBuilder {
        private String object;
        private String message;
        private String field;

        public ValidatorObjectBuilder() {
        }

        public static ValidatorObjectBuilder aValidatorObject() {
            return new ValidatorObjectBuilder();
        }

        public ValidatorObjectBuilder object(String object) {
            this.object = object;
            return this;
        }

        public ValidatorObjectBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ValidatorObjectBuilder field(String field) {
            this.field = field;
            return this;
        }

        public ValidatorObject build() {
            ValidatorObject validatorObject = new ValidatorObject();
            validatorObject.field = this.field;
            validatorObject.object = this.object;
            validatorObject.message = this.message;
            return validatorObject;
        }
    }
}
