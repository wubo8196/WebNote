package com.web.po;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * 收藏夹实体
 * @author 吴波
 *
 */
public class NoteFavorite implements Serializable{

	private static final long serialVersionUID = -6649040016764626327L;
	
	/**
	 * 收藏夹id
	 */
	private Long favoriteId;
	
	/**
	 * 一个收藏夹维护多个笔记
	 */
	private Set<OneNote> oneNoteSet = new TreeSet<>();

	/**
	 * 空参构造
	 */
	public NoteFavorite() {
	}

	/**
	 * 全参构造
	 * @param favoriteId
	 * @param oneNoteSet
	 */
	public NoteFavorite(Long favoriteId, Set<OneNote> oneNoteSet) {
		super();
		this.favoriteId = favoriteId;
		this.oneNoteSet = oneNoteSet;
	}

	public Long getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}

	public Set<OneNote> getOneNoteSet() {
		return oneNoteSet;
	}

	public void setOneNoteSet(Set<OneNote> oneNoteSet) {
		this.oneNoteSet = oneNoteSet;
	}
}
