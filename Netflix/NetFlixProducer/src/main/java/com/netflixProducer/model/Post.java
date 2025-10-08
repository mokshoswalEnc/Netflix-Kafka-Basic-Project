package com.netflixProducer.model;

public class Post {
	private String title;
	private String description;
	private String genre;
	private String releaseDate;

	public Post() {
	}

	public Post(String title, String description, String genre, String releaseDate) {
		this.title = title;
		this.description = description;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Post{" + "title='" + title + '\'' + ", description='" + description + '\'' + ", genre='" + genre + '\''
				+ ", releaseDate='" + releaseDate + '\'' + '}';
	}
}
