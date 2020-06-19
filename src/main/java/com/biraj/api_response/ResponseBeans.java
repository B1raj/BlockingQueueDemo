package com.biraj.api_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ResponseBeans {
	private DataBeans data = new DataBeans();
	private AdBeans ad = new AdBeans();
	
	public DataBeans getData() {
		return data;
	}

	public void setData(DataBeans data) {
		this.data = data;
	}

	public AdBeans getAd() {
		return ad;
	}

	public void setAd(AdBeans ad) {
		this.ad = ad;
	}

	@Override
	public String toString() {
		return "["+"data="+data+",ad="+ad+"]";
	}
}
