package vn.unicloud.umeepay.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationAndSortingRequest {
    private int size = 10;
    private int page = 0;

    private String sortBy = "id";

    private String sortDir = "ASC";
    public PaginationAndSortingRequest(int page,int size){
        this.page = page;
        this.size = size;
    }
}
