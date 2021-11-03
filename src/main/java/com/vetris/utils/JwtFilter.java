package com.vetris.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vetris.enums.ErrorCodes;

@Component
public class JwtFilter implements Filter {

	private static final String AUTHORIZATION = "AUTHORIZATION";
	private static final String USERUUID = "id";
	private static final String USERROLES = "roles";
	private static final String MFAENABLE = "mfaEnabled";
	private static final String MFAVALIDATED = "mfaValidated";

	@Autowired
	private JWTSecurityContextUtil jwtSecurityContextUtil;

	@Autowired
	private ObjectMapper objectMapper;

	RestTemplate restTemplate = new RestTemplate();

	@Value("${jwt.decode.url}")
	private String decodeUrl;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (httpServletRequest.getHeader(AUTHORIZATION) == null) {
			httpServletResponse.sendError(401);
			return;
		}

		URI uri = null;
		try {
			uri = new URI(decodeUrl);
		} catch (URISyntaxException e) {
			httpServletResponse.sendError(401);
			return;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION, httpServletRequest.getHeader(AUTHORIZATION));

		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> decodeResponse = null;

		try {
			decodeResponse = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		} catch (RestClientException exc) {
			httpServletResponse.sendError(401);
			return;
		}

		if (Objects.nonNull(decodeResponse)) {
			Map<String, String> filterResult = objectMapper.readValue(decodeResponse.getBody(), Map.class);

			//Check for MFA enable users validated
			if(Objects.nonNull(filterResult.get(MFAENABLE)) && filterResult.get(MFAENABLE).equals("Y")
					&& filterResult.get(MFAVALIDATED).equals("false")) {
				httpServletResponse.sendError(401, ErrorCodes.UN_AUTH_USER.getMessage());
				return;
			}
			
			jwtSecurityContextUtil.setId(filterResult.get(USERUUID));

			List<GrantedAuthority> authorities = null;
			// Set roles for security context
			if (Objects.nonNull(filterResult.get(USERROLES))) {
				authorities = Arrays.stream(filterResult.get(USERROLES).split(","))
						.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					filterResult.get(USERUUID), "", authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			httpServletResponse.setStatus(200);
		}else {
			httpServletResponse.sendError(401);
			return;
		}

		

		chain.doFilter(httpServletRequest, httpServletResponse);

	}

}
