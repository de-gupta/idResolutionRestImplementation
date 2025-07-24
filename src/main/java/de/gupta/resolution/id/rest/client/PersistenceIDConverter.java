package de.gupta.resolution.id.rest.client;

import de.gupta.resolution.id.api.IdentifierConverter;
import de.gupta.resolution.id.api.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.UUID;

final class PersistenceIDConverter implements IdentifierConverter<UUID, String>
{
	private final RestClient restClient;
	private final String apiIDFetcherEndpoint;
	private final TokenProvider tokenProvider;

	@Override
	public String convert(final UUID uuid)
	{
		final String endPointWithParameter = apiIDFetcherEndpoint + "/" + uuid;
		return restClient.get()
						 .uri(endPointWithParameter)
						 .accept(MediaType.APPLICATION_JSON)
						 .headers(headers -> headers.setBearerAuth(tokenProvider.token()))
						 .retrieve()
						 .toString();
	}

	PersistenceIDConverter(final RestClient restClient,
						   @Value("${id.resolution.apiIDFetcherEndpoint}") final String apiIDFetcherEndpoint,
						   final TokenProvider tokenProvider)
	{
		this.restClient = restClient;
		this.apiIDFetcherEndpoint = apiIDFetcherEndpoint;
		this.tokenProvider = tokenProvider;
	}
}