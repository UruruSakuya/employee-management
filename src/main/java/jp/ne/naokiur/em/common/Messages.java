package jp.ne.naokiur.em.common;

import java.text.MessageFormat;

import jp.ne.naokiur.em.code.MessageResource;

public class Messages {
    private final MessageResource messageResource;
    private final String[] replatement;
    private final String message;

    public Messages(MessageResource messageResource, String[] replatement) {
        this.messageResource = messageResource;
        this.replatement = replatement;
        this.message = new MessageFormat(this.messageResource.load()).format(this.replatement);
    }

    public Messages(MessageResource messageResource) {
        this.messageResource = messageResource;
        this.replatement = new String[]{};
        this.message = this.messageResource.load();
    }

    public MessageResource getMessageResource() {
        return messageResource;
    }

    public String[] getReplatement() {
        return replatement;
    }

    public String getMessage() {
        return message;
    }
}
