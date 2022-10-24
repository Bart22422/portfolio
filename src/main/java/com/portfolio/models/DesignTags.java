package com.portfolio.models;

public enum DesignTags {
	UI("UI"), UX("UX"), SPA("SPA"), LANDING_PAGE("Landing Page"), ILLUSTRATION("Illustration");

	private String title;

	DesignTags(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
