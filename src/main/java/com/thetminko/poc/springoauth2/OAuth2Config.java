package com.thetminko.poc.springoauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public OAuth2Config(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("foo")
        .secret(passwordEncoder.encode("bar"))
        .authorizedGrantTypes("client_credentials", "refresh_token", "password")
        .scopes("user_info")
        .autoApprove(true);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.passwordEncoder(passwordEncoder)
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }
}
