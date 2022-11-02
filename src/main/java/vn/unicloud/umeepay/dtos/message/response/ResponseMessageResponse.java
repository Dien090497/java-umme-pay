package vn.unicloud.umeepay.dtos.message.response;

import lombok.*;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.ResponseMessage;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ResponseMessageResponse extends BaseResponseData {
    private Long id;

    private Integer code;

    private String viContent;

    private String enContent;

    private String description;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;

    public ResponseMessageResponse(ResponseMessage message) {
        if (message == null) {
            return;
        }

        this.id = message.getId();
        this.code = message.getCode();
        this.viContent = message.getViContent();
        this.enContent = message.getEnContent();
        this.description = message.getDescription();
        this.createdAt = message.getCreatedAt();
        this.createdBy = message.getCreatedBy();
        this.modifiedAt = message.getModifiedAt();
        this.modifiedBy = message.getModifiedBy();
    }

}
