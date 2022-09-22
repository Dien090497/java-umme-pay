package vn.unicloud.umeepay.model;

import lombok.Data;

@Data
public class TransactionCallback {
    private EventListener eventListener;

    public void registerEvent(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void deregisterEvent() {
        this.eventListener = null;
    }
}
