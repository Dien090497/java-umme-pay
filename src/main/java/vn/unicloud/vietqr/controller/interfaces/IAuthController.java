package vn.unicloud.vietqr.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.unicloud.vietqr.core.ResponseBase;
import vn.unicloud.vietqr.dtos.request.*;
import vn.unicloud.vietqr.dtos.response.*;

import javax.validation.Valid;

@Tag(name = "Auth Controller", description = "Thao tác với auth")
@RequestMapping(value = "/api/auth")
public interface IAuthController {

    @Operation(
        summary = "login",
        description = "- login với username là email hoặc số điện thoại",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> userLogin(@Valid @RequestBody LoginRequest request);

    @Operation(
        summary = "Client login",
        description = "- Client login oauth",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
        })
    @RequestMapping(value = "/v1/client", method = RequestMethod.POST)
    ResponseEntity<ResponseBase<AccessTokenResponseCustom>> clientLogin(@Valid @RequestBody ClientLoginRequest request);
}
