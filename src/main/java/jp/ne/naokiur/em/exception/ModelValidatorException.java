package jp.ne.naokiur.em.exception;

import java.util.List;

import jp.ne.naokiur.em.common.Messages;

public class ModelValidatorException extends Exception {
    List<Messages> messagesList;

    /** */
    private static final long serialVersionUID = 1L;

    public ModelValidatorException(Throwable cause) {
        super(cause);
    }

    public ModelValidatorException(String message) {
        super(message);
    }

    public ModelValidatorException(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }

    public List<Messages> getMessagesList() {
        return messagesList;
    }
}
