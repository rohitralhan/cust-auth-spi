package com.redhat.sso.spi.externalauth;

import static org.keycloak.provider.ProviderConfigProperty.STRING_TYPE;

import java.util.Arrays;
import java.util.List;

import org.keycloak.Config.Scope;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;


public class ExtAuthFactory implements AuthenticatorFactory {

	
    public static final String ID = "external-authenticator";
	public static final String DISPLAY_NAME = "External Application Authenticator";
	public static final String SERVICE_URL = "srvcUrl";
	public static final String OCP_APIM_SUBSCRIPTION_KEY = "ocpApimSubscriptionKey";
	public static final String UNICA_APPLICATION = "unicaaApplication";
	public static final String UNICA_PID = "unciaPid";
	public static final String UNICA_SERVICEID = "unciaServiceId";
	public static final String UNICA_USER = "unciaUser";
	public static final String CONTENT_TYPE = "contentType";
	public static final String DATA = "data";

	
    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED//,
            //AuthenticationExecutionModel.Requirement.DISABLED
    };	
	
	
	@Override
	public Authenticator create(KeycloakSession session) {
		// TODO Auto-generated method stub
		return new ExtAuth();
	}

	@Override
	public void init(Scope config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public String getDisplayType() {
		// TODO Auto-generated method stub
		return DISPLAY_NAME;
	}

	@Override
	public String getReferenceCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConfigurable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		// TODO Auto-generated method stub
		ProviderConfigProperty prop1 = new ProviderConfigProperty(SERVICE_URL, "Service URL", "External Authentication Service URL",
				STRING_TYPE, "https://apimngr-genesis-cert.azure-api.net/api-ne-enterprise-authenticationapi-oc/v1/authentication/internal/login?g-recaptcha-response=null");

        ProviderConfigProperty prop2 = new ProviderConfigProperty(OCP_APIM_SUBSCRIPTION_KEY, "Ocp Apim Subscription Key", "External Authentication Subscription Key",
                STRING_TYPE, "c1cc27240ce84d4480dcdec4a55e4d73");
        
		ProviderConfigProperty prop3 = new ProviderConfigProperty(UNICA_APPLICATION, "UNICA Application", "UNICA Application",
				STRING_TYPE, "WAPPE");

        ProviderConfigProperty prop4 = new ProviderConfigProperty(UNICA_PID, "UNICA PID", "UNICA PID",
                STRING_TYPE, "550e8400-e29b-41d4-a716-446655440000");        
        
		ProviderConfigProperty prop5 = new ProviderConfigProperty(UNICA_SERVICEID, "UNICA ServiceId", "UNICA ServiceId",
				STRING_TYPE, "550e8400-e29b-41d4-a716-446655440000");

        ProviderConfigProperty prop6 = new ProviderConfigProperty(UNICA_USER, "UNICA User", "UNICA User",
                STRING_TYPE, "mcrisant");
        
		ProviderConfigProperty prop7 = new ProviderConfigProperty(CONTENT_TYPE, "Content Type", "Content Type of the Data",
				STRING_TYPE, "application/json");

        ProviderConfigProperty prop8 = new ProviderConfigProperty(DATA, "Data", "Data in JSON format { \"key\": \"value\" }",
                STRING_TYPE, "{  \"username\": \"OTROS-ADMINISTR\", \"password\": \"Peru123.\", \"remember\": false, \"documentTypeCode\": \"OTROS\", \"documentNumber\": \"ADMINISTR\"}");
        
        
       return Arrays.asList(prop1, prop2, prop3, prop4, prop5, prop6, prop7, prop8);
	}	
	
	@Override
	public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
		// TODO Auto-generated method stub
		return REQUIREMENT_CHOICES;
	}

	@Override
	public boolean isUserSetupAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return null;
	}

}
