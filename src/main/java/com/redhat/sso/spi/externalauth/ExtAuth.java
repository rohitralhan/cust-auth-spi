package com.redhat.sso.spi.externalauth;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.*;


public class ExtAuth implements Authenticator {

	
    private static final Logger logger = Logger.getLogger(ExtAuth.class);
    public static final String DEFAULT_APPLICATION_ID = "application-id";
	
	@Override
	public void authenticate(AuthenticationFlowContext context) {

        if (context.getAuthenticatorConfig() != null) {
        	context.getAuthenticatorConfig().getConfig()
        	.forEach((k, v) -> {
        			if(v == null) { 
        				context.failure(AuthenticationFlowError.ACCESS_DENIED); 
        				logger.info("Mandatory config value(s) not set.");
        			} 
        		});
        }
        
        // Send HTTP request to external Service
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.SERVICE_URL)))
                .header("content-type", context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.CONTENT_TYPE))
                .header("Ocp-Apim-Subscription-Key", context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.OCP_APIM_SUBSCRIPTION_KEY))
                .header("UNICA-Application", context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.UNICA_APPLICATION))
                .header("UNICA-PID", context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.UNICA_PID))
                .header("UNICA-ServiceId", context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.UNICA_SERVICEID))
                .header("UNICA-User", context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.UNICA_USER))
                .POST(HttpRequest.BodyPublishers.ofString(context.getAuthenticatorConfig().getConfig().get(ExtAuthFactory.DATA)))
                .build();
        HttpResponse<String> response = null;
        
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			//logger.info(response.body());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Add/Update the below user Session Notes as needed and the built-in Client "User Session Note" mapper will pick these up 
        context.getAuthenticationSession().setUserSessionNote("user-session-note1", "user-session-note1-value");
        context.getAuthenticationSession().setUserSessionNote("user-session-note2", "user-session-note2-value");
        context.getAuthenticationSession().setUserSessionNote("user-session-note3", "user-session-note3-value");
        
        //context.getAuthenticationSession().getUserSessionNotes().forEach((k, v) -> logger.info("Key: " + k + ", Value: " + v));
        
        
        context.success();
	}

	
	@Override
	public void action(AuthenticationFlowContext context) {
		// TODO Auto-generated method stub
		authenticate(context);
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public boolean requiresUser() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
		// TODO Auto-generated method stub
		
	}
	

}
