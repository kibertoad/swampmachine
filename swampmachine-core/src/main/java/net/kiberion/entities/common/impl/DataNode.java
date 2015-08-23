/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kiberion.entities.common.impl;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

/**
 * @author kibertoad
 */
public class DataNode extends EntityModel {

    public String group;
    public String subGroup;

    public String groupID;

    public int rating = -1;

    public ArrayList<String> tags = new ArrayList<String>();

    private String description; 
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String subGroupID;

    public DataNode() {
    }

    public DataNode(String setName, String setCode) {
        setName (setName);
        setId(setCode);
    }

    @Override
    public String toString() {
        return getMetadata().getName();
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public boolean hasTag(String theTag) {
        return tags.contains(theTag);
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void outputTags() {
        for (String s : tags) {
            Gdx.app.log("debug", "Tag: " + s);
        }
    }

    @Override
    public ArrayList<String> getTags() {
        return tags;
    }

    @Override
    public String getName() {
        if (getMetadata().getName() != null) {
            return getMetadata().getName();
        } else {
            return super.getName();
        }
    }

    @Override
    public void setGroup(String toGroup) {
        group = toGroup;
    }

    @Override
    public void setSubGroup(String toGroup) {
        subGroup = toGroup;
    }

    @Override
    public void setName(String name) {
        this.getMetadata().setName(name);
    }

    public void setImage(String imageCode) {

    }
}
