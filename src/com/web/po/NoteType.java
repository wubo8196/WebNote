package com.web.po;

import java.io.Serializable;

/**
 * 笔记本类型实体
 * 
 * @author 吴波
 *
 */
public class NoteType implements Serializable {

	private static final long serialVersionUID = -7506617070335344725L;

	/**
	 * 笔记本类型id
	 */
	private Long typeId;

	/**
	 * 笔记本类型名
	 */
	private String typeName;

	/**
	 * 笔记本类型说明
	 */
	private String typeDesc;

	/**
	 * 空参构造
	 */
	public NoteType() {
	}

	/**
	 * 全参构造
	 * @param typeId
	 * @param typeName
	 * @param typeDesc
	 */
	public NoteType(Long typeId, String typeName, String typeDesc) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeDesc = typeDesc;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

}
