package de.gupta.resolution.id.rest.client;

import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.api.TokenProvider;
import de.gupta.resolution.id.rest.configuration.ClientProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@AutoConfiguration
@EnableConfigurationProperties(ClientProperties.class)
public class ConverterAutoConfiguration
{
	@Bean
	public RestClient restClient(@Value("${id.resolution.baseURL}") final String baseURL)
	{
		return RestClient.builder().baseUrl(baseURL).build();
	}

	@Bean
	@ConditionalOnMissingBean(name = "apiIDConverter")
	public IdentifierConverter<String, UUID> apiIDConverter(final RestClient restClient,
															final ClientProperties properties,
															final TokenProvider tokenProvider
	)
	{
		return new APIIDConverter(restClient, properties.getPersistenceIDFetcherEndpoint(), tokenProvider);
	}

	@Bean
	@ConditionalOnMissingBean(name = "persistenceIDConverter")
	public IdentifierConverter<UUID, String> persistenceIDConverter(final RestClient restClient,
																	final ClientProperties properties,
																	final TokenProvider tokenProvider
	)
	{
		return new PersistenceIDConverter(restClient, properties.getApiIDFetcherEndpoint(), tokenProvider);
	}

}