package jp.ne.naokiur.em.exception;

public class ModelValidatorException extends Exception {

    /** */
    private static final long serialVersionUID = 1L;

    public ModelValidatorException(Throwable cause) {
        super(cause);
    }

    public ModelValidatorException(String message) {
        super(message);
    }
}
