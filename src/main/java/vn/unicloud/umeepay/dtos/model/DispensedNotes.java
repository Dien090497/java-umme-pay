package vn.unicloud.umeepay.dtos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class DispensedNotes {

    private int note50;
    private int note100;
    private int note200;
    private int note500;

}
