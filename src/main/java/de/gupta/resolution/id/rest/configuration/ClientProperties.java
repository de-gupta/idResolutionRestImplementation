package de.gupta.resolution.id.rest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "id.resolution")
public final class ClientProperties
{
	private String baseURL;
	private String persistenceIDFetcherEndpoint;
	private String apiIDFetcherEndpoint;

	public String getBaseURL()
	{
		return baseURL;
	}

	public void setBaseURL(String baseURL)
	{
		this.baseURL = baseURL;
	}

	public String getPersistenceIDFetcherEndpoint()
	{
		return persistenceIDFetcherEndpoint;
	}

	public void setPersistenceIDFetcherEndpoint(final String persistenceIDFetcherEndpoint)
	{
		this.persistenceIDFetcherEndpoint = persistenceIDFetcherEndpoint;
	}

	public String getApiIDFetcherEndpoint()
	{
		return apiIDFetcherEndpoint;
	}

	public void setApiIDFetcherEndpoint(final String apiIDFetcherEndpoint)
	{
		this.apiIDFetcherEndpoint = apiIDFetcherEndpoint;
	}
}