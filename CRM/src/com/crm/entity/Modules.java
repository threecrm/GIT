package com.crm.entity;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Modules {
	public Integer id;
	public String text;
	public String iconCls;
	public String state;
	public Boolean checked;
	


	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<Modules> children;

	public String url;
	public Integer Weight;
	public Integer ParentId;
	
	private Integer mid;
	private String ModuleName;
	private String Path;
	
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getModuleName() {
		return ModuleName;
	}

	public void setModuleName(String moduleName) {
		ModuleName = moduleName;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	


	public List<Modules> getChildren() {
		return children;
	}

	public void setChildren(List<Modules> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getWeight() {
		return Weight;
	}

	public void setWeight(Integer weight) {
		Weight = weight;
	}

	public Integer getParentId() {
		return ParentId;
	}

	public void setParentId(Integer parentId) {
		ParentId = parentId;
	}

	@Override
	public String toString() {
		return "Modules [id=" + id + ", text=" + text + ", iconCls=" + iconCls
				+ ", state=" + state + ", checked=" + checked + ", children="
				+ children + ", url=" + url + ", Weight=" + Weight
				+ ", ParentId=" + ParentId + ", mid=" + mid + ", ModuleName="
				+ ModuleName + ", Path=" + Path + "]";
	}

	

	
}
