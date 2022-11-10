package vn.unicloud.umeepay.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.unicloud.umeepay.core.BaseResponseData;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageResponse<T> extends BaseResponseData {
    private int pageNumber = 0;
    private int pageSize = 0;
    private Long totalSize = 0L;
    private int totalPage = 0;
    private List<T> items = new ArrayList<>();
}
