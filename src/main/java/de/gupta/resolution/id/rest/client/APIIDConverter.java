package de.gupta.resolution.id.rest.client;

import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.api.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.UUID;

final class APIIDConverter implements IdentifierConverter<String, UUID>
{
	private final RestClient restClient;
	private final String persistenceIDFetcherEndpoint;
	private final TokenProvider tokenProvider;

	@Override
	public UUID convert(final String s)
	{
		return restClient.get()
						 .uri(persistenceIDFetcherEndpoint)
						 .accept(MediaType.APPLICATION_JSON)
						 .headers(headers -> headers.setBearerAuth(tokenProvider.token()))
						 .retrieve()
						 .toEntity(UUID.class)
						 .getBody();
	}

	APIIDConverter(final RestClient restClient,
				   @Value("${id.resolution.persistenceIDFetcherEndpoint}") final String persistenceIDFetcherEndpoint,
				   final TokenProvider tokenProvider)
	{
		this.restClient = restClient;
		this.persistenceIDFetcherEndpoint = persistenceIDFetcherEndpoint;
		this.tokenProvider = tokenProvider;
	}
}