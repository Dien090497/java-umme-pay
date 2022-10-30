package vn.unicloud.umeepay.dtos.role.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.unicloud.umeepay.core.BaseResponseData;
import vn.unicloud.umeepay.entity.Action;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionResponse extends BaseResponseData {
    private Long id;
    private String name;
    private String description;

    public ActionResponse(Action action) {
        if (action == null) {
            return;
        }
        this.id = action.getId();
        this.name = action.getName();
        this.description = action.getDescription();
    }
}
