package vn.unicloud.umeepay.core;

public interface CqrsBus {
    <T extends RequestData, I extends ResponseData> I execute(T var1);
}
