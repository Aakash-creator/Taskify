package com.tasify.serviceInterface;

import java.util.List;

import com.tasify.entity.Tag;
import com.tasify.entity.Task;

public interface ITagService 
{
//	CREATE
//	Create a tag under a specific task
	Task addTagToTask(Long taskId, Tag tag);

//	 READ
	public Tag findByName(String name);
	
	Tag getTagById(Long tagId);
	
	List<Tag> getAllTags();
	
//	 Association with Task
	List<Task> getAllTasksByTagName(String tagName);

//	 UPDATE
	Tag updateTag(Long tagId, Tag updatedTag);

//	 DELETE
	void deleteTag(Long tagId);

}
