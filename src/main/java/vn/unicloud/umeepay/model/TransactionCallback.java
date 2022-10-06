package vn.unicloud.umeepay.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;;

@Getter
@Setter
@ToString
public class TransactionCallback {
    private EventListener eventListener;

    public void registerEvent(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void deregisterEvent() {
        this.eventListener = null;
    }
}
