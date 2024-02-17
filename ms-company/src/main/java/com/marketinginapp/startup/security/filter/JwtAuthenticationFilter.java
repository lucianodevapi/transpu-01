package com.marketinginapp.startup.security.filter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketinginapp.startup.handler.ErrorMessage;
import com.marketinginapp.startup.handler.exception.MessageException;
import com.marketinginapp.startup.security.jwt.JwtUtils;
import com.marketinginapp.startup.utils.Constant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;
    //private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        com.marketinginapp.startup.domain.entity.User userEntity = new com.marketinginapp.startup.domain.entity.User();
        String email = "";
        String password = "";
        try{
            userEntity = new ObjectMapper().readValue(request.getInputStream(), com.marketinginapp.startup.domain.entity.User.class);
            email = userEntity.getEmail();
            password = userEntity.getPassword();
        } catch (StreamReadException exception) {
            throw new MessageException(String.format(Constant.EXCEPTION_TOKEN_INVALID_FORMAT, exception));
        } catch (DatabindException exception) {
            throw new MessageException(String.format(Constant.EXCEPTION_TOKEN_INVALID_FORMAT, exception));
        } catch (IOException exception) {
            throw new MessageException(String.format(Constant.EXCEPTION_TOKEN_INVALID_FORMAT, exception));
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(user.getUsername());

        response.addHeader("Authorization", token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Authentication correct");
        httpResponse.put("Username", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        var messageException = new ErrorMessage(request, HttpStatus.UNAUTHORIZED, String.format("Unsuccessful Authentication: %s", exception.getLocalizedMessage()));
        ObjectMapper objectMapper = new ObjectMapper();
        var msgJson = objectMapper.writeValueAsString(messageException);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(msgJson);
        response.getWriter().flush();
    }
}
