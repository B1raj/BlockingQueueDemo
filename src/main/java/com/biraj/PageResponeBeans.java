package com.biraj;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PageResponeBeans {
	private String page;
	private String per_page;
	private String total;
	private String total_pages;

	private DataBeans data = new DataBeans();
	private AdBeans ad = new AdBeans();

/*	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ResponeBeans that = (ResponeBeans) obj;
		return Objects.equals(getPage(), that.getPage()) && Objects.equals(getPerPage(), that.getPerPage())
				&& Objects.equals(getTotal(), that.getTotal()) && Objects.equals(getTotalPages(), that.getTotalPages())
				&& Objects.equals(getData().getId(), that.getData().getId())
				&& Objects.equals(getData().getEmail(), that.getData().getEmail())
				&& Objects.equals(getData().getFirstName(), that.getData().getFirstName())
				&& Objects.equals(getData().getLastName(), that.getData().getLastName())
				&& Objects.equals(getData().getAvatar(), that.getData().getAvatar())
				&& Objects.equals(getAd().getCompany(), that.getAd().getCompany())
				&& Objects.equals(getAd().getUrl(), that.getAd().getUrl())
				&& Objects.equals(getAd().getText(), that.getAd().getText());
	}

	@Override
	public int hashCode() {
		return Objects.hash(page, per_page, total, total_pages, data, ad);
	}*/

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}


	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPer_page() {
		return per_page;
	}

	public void setPer_page(String per_page) {
		this.per_page = per_page;
	}

	public String getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(String total_pages) {
		this.total_pages = total_pages;
	}

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
		return "page=" + page + ",per_page=" + per_page + ", total=" + total + ", total_pages=" + total_pages + ",data="
				+ data + ",ad=" + ad + "]";
	}
}
