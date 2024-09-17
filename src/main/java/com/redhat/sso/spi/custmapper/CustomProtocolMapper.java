package com.redhat.sso.spi.custmapper;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenResponseMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper;
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.IDToken;

import com.redhat.sso.spi.externalauth.ExtAuth;

public class CustomProtocolMapper extends AbstractOIDCProtocolMapper implements OIDCAccessTokenMapper,
OIDCIDTokenMapper, UserInfoTokenMapper, OIDCAccessTokenResponseMapper {

	private static final Logger logger = Logger.getLogger(ExtAuth.class);
    public static final String PROVIDER_ID = "custom-protocol-mapper";
    public static final String DISPLAY_TYPE = "Custom Token Mapper";
    public static final String DISPLAY_CATEGORY = "Token Mapper";
    public static final String HELP_TEXT = "A Custom Token Mapper";
    private static final List<ProviderConfigProperty> configProperties = new ArrayList<>();	
	
    static {
        OIDCAttributeMapperHelper.addTokenClaimNameConfig(configProperties);
        OIDCAttributeMapperHelper.addIncludeInTokensConfig(configProperties, CustomProtocolMapper.class);
    }  
    
    
	@Override
	public String getDisplayCategory() {
		// TODO Auto-generated method stub
		return DISPLAY_CATEGORY;
	}

	@Override
	public String getDisplayType() {
		// TODO Auto-generated method stub
		return DISPLAY_TYPE;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return PROVIDER_ID;
	}

	@Override
	public String getHelpText() {
		// TODO Auto-generated method stub
		return HELP_TEXT;
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		// TODO Auto-generated method stub
		return configProperties;
	}

	
    @Override
    protected void setClaim(IDToken token, ProtocolMapperModel mappingModel,
      UserSessionModel userSession, KeycloakSession keycloakSession,
      ClientSessionContext clientSessionCtx) {
    	/*for(ProviderConfigProperty pcp : configProperties) {
    		logger.info(pcp.getLabel());
    	}*/
    	
    	//Add claim value from the Client Session Note to the custom mapper
    	//token.getOtherClaims().put("note-claim1", clientSessionCtx.getClientSession().getNote("note-claim1"));
    	
    	//Another way to add a claim to the token
    	/*token.getOtherClaims().put("my-claim-name1", "my-claim-value1");
    	token.getOtherClaims().put("my-claim-name2", "my-claim-value2");
    	token.getOtherClaims().put("my-claim-name3", "my-claim-value3");*/
    	
        OIDCAttributeMapperHelper.mapClaim(token, mappingModel, "CustomValue");
    }	
	
	
}
