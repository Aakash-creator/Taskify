package com.tasify.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasify.Exceptions.TagNotFoundException;
import com.tasify.Exceptions.TaskNotFoundException;
import com.tasify.Repo.ITagRepo;
import com.tasify.Repo.ITaskRepo;
import com.tasify.entity.Tag;
import com.tasify.entity.Task;
import com.tasify.serviceInterface.ITagService;

@Service
public class TagServiceImpl implements ITagService 
{

	@Autowired
	private ITaskRepo taskRepo;
	
	@Autowired
	private ITagRepo tagRepo;
	
	// Create a tag under a specific task
	@Override
	public Task addTagToTask(Long taskId, Tag tag) 
	{
		// Fetch the task
	    Task taskExt = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskNotFoundException("Task not found with Id " + taskId));

	    // Check if the tag exists in the tag table
	    Optional<Tag> existingTagOptional = tagRepo.findByName(tag.getName());

	    Tag tagToUse = tag; // Create a new reference to the tag

	    if (existingTagOptional.isPresent()) {
	        // Use the existing tag if it exists
	        tagToUse = existingTagOptional.get();
	    } else {
	        // If tag doesn't exist, save the new tag
	        tagRepo.save(tagToUse);
	    }

	    // Check if the tag is already associated with the task
	    boolean tagAlreadyAssociated = taskExt.getTags().stream()
	            .anyMatch(existingTag -> existingTag.getName().equalsIgnoreCase(tag.getName()));

	    if (!tagAlreadyAssociated) {
	        // Add the tag to the task's tag set if not already associated
	        taskExt.getTags().add(tagToUse);

	        // Save the updated task
	        taskExt = taskRepo.save(taskExt);
	    }

	    return taskExt;	
	    
	}


	@Override
	public Tag updateTag(Long tagId, Tag updatedTag)
	{
	    Optional<Tag> existingTagOptional = tagRepo.findById(tagId);
	    if (existingTagOptional.isPresent()) 
	    {
	        Tag existingTag = existingTagOptional.get();
	        // Update fields in the existing tag with values from the updated tag
	        existingTag.setName(updatedTag.getName()); // Example field
	        // Add additional fields to update as needed
	        return tagRepo.save(existingTag);
	    } 
	    else 
	    {
	        throw new TagNotFoundException("Tag with ID " + tagId + " not found.");
	    }
	}

	@Override
	public void deleteTag(Long tagId)
	{
	    if (tagRepo.existsById(tagId))
	    {
	        tagRepo.deleteById(tagId);
	    } else {
	        throw new TagNotFoundException("Tag with ID " + tagId + " not found.");
	    }
	}


	@Override
	public Tag getTagById(Long tagId) 
	{
		Tag tagAvail = tagRepo.findById(tagId).orElseThrow( ()-> new TagNotFoundException("tag not found with id" + tagId));
		return tagAvail;
	}

	@Override
	public List<Tag> getAllTags() 
	{
		List<Tag> allTags = tagRepo.findAll();
		return allTags;
	}

	@Override
	public List<Task> getAllTasksByTagName(String tagName)
	{
		List<Task> allTags = tagRepo.getAllTasksByTagName(tagName);
		return allTags;
	}

	@Override
	public Tag findByName(String name) 
	{
		return tagRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Tag with name " + name + " not found"));
		
	}
	
}
