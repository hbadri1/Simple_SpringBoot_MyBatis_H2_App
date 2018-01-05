package org.gso.committee.model;

import java.util.List;

public class Committee {

	private Long code;
	private String name;
	private Long p_code;
	private List<Committee> children;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getP_code() {
		return p_code;
	}

	public void setP_code(Long p_code) {
		this.p_code = p_code;
	}

	public List<Committee> getChildren() {
		return children;
	}

	public void setChildren(List<Committee> children) {
		this.children = children;
	}

}
