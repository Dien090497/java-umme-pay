package vn.unicloud.umeepay.common;

public interface IAuthorization {

    boolean authorize(String loggedInId, String ...actions);

}
