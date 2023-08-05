package fun.icpc.iris.irisonlinejudge.controller;

import java.io.IOException;
import java.util.Objects;

import fun.icpc.iris.irisonlinejudge.domain.auth.AuthenticationRequest;
import fun.icpc.iris.irisonlinejudge.domain.auth.AuthenticationResponse;
import fun.icpc.iris.irisonlinejudge.service.AuthenticationService;
import fun.icpc.iris.irisonlinejudge.domain.auth.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisResponse;
import fun.icpc.iris.irisonlinejudge.commons.util.IrisResponseFactory;
import lombok.RequiredArgsConstructor;

/**
 * AuthenticationController
 * <p>
 * This class is the controller for authentication.
 * It handles the requests for authentication.
 * It has three methods:
 *   <ul>
 *     <li>{@link #register(RegisterRequest)}</li>
 *     <li>{@link #authenticate(AuthenticationRequest)}</li>
 *     <li>{@link #refreshToken(String)}</li>
 *   </ul>
 * <p>
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * user register
     *
     * @param request {@link RegisterRequest}
     * @return {@link IrisResponse}<{@link AuthenticationResponse}>
     */
    @PostMapping("/register")
    public IrisResponse<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return IrisResponseFactory.success(service.register(request));
    }

    /**
     * authenticate
     *
     * @param request {@link AuthenticationRequest}
     * @return {@link IrisResponse}<{@link AuthenticationResponse}>
     */
    @PostMapping("/authenticate")
    public IrisResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return IrisResponseFactory.success(service.authenticate(request));
    }


    /**
     * refresh token
     *
     * @param authHeader the auth header
     * @return {@link IrisResponse}<{@link AuthenticationResponse}>
     * @throws IOException if an I/O error occurs.
     */
    @PostMapping("/refresh-token")
    public IrisResponse<AuthenticationResponse> refreshToken(@RequestHeader("Authorization") String authHeader) {
        if (Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            return IrisResponseFactory.fail("Invalid token");
        }
        String refreshToken = authHeader.substring(7);
        return IrisResponseFactory.success(service.refreshToken(refreshToken));
    }


}