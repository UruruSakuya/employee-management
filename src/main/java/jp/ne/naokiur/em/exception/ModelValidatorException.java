package jp.ne.naokiur.em.exception;

import jp.ne.naokiur.em.code.Messages;

public class ModelValidatorException extends Exception {
    Messages messages;

    /** */
    private static final long serialVersionUID = 1L;

    public ModelValidatorException(Throwable cause) {
        super(cause);
    }

    public ModelValidatorException(String message) {
        super(message);
    }

    public ModelValidatorException(Messages message) {
        this.messages = message;
    }

    public Messages getMessages() {
        return messages;
    }
}
