package vn.unicloud.vietqr.dtos.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DispensedNotes {

    private int note50;
    private int note100;
    private int note200;
    private int note500;

}
