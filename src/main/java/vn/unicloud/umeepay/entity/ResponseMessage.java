package vn.unicloud.umeepay.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import vn.unicloud.umeepay.common.SpringContext;
import vn.unicloud.umeepay.enums.SystemModule;
import vn.unicloud.umeepay.service.VersionTrackingService;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ResponseMessage.COLLECTION_NAME)
@Slf4j
public class ResponseMessage extends Auditable<String>{

    public static final String COLLECTION_NAME = "response_message";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;

    private String viContent;

    private String enContent;

    private String description;

    @PostPersist
    @PostUpdate
    @PostRemove
    public void handleResponseMessagePersist() {
        VersionTrackingService trackingService = SpringContext.getBean(VersionTrackingService.class);
        VersionTracking tracking = trackingService.getByKey(SystemModule.RESPONSE_MESSAGE);
        if(tracking == null) {
            tracking = new VersionTracking()
                    .setVersion(0)
                    .setKey(SystemModule.RESPONSE_MESSAGE);
        }

        tracking.setVersion(tracking.getVersion() + 1);
//        trackingService.save(tracking);
        log.info("Update version tracking for RESPONSE_MESSAGE table");
    }

}
