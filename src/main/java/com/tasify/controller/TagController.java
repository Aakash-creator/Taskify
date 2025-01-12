package com.tasify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasify.entity.Tag;
import com.tasify.entity.Task;
import com.tasify.serviceInterface.ITagService;

@RestController
public class TagController 
{	
	@Autowired
	private ITagService tagService;
	
	// Creates a new tag under a specific task using the provided task ID and tag details
	@PostMapping("/createtag/{id}")
	public ResponseEntity<Task> createTag(@RequestBody Tag tag, @PathVariable Long id)
	{
	    Task newTag = tagService.addTagToTask(id, tag);
	    return new ResponseEntity<>(newTag, HttpStatus.OK);
	}

	// Retrieves all tags from the database
	@GetMapping("/getalltag")
	public ResponseEntity<List<Tag>> getAllTag() 
	{
	    List<Tag> newTag = tagService.getAllTags();
	    return new ResponseEntity<>(newTag, HttpStatus.OK);
	}

	@GetMapping("/getalltagbyname/{tagname}")
	public ResponseEntity<List<Task>> getAllTagByname(@PathVariable String tagname) 
	{
	    List<Task> newTag = tagService.getAllTasksByTagName(tagname);
	    return new ResponseEntity<>(newTag, HttpStatus.OK);
	}

	@GetMapping("/getalltagbyid/{tagid}")
	public ResponseEntity<Tag> getAllTagByid(@PathVariable Long tagid) 
	{
	    Tag newTag = tagService.getTagById(tagid);
	    return new ResponseEntity<>(newTag, HttpStatus.OK);
	}

	@PutMapping("/updatetag/{tagId}")
	public ResponseEntity<Tag> updateTag(@PathVariable Long tagId, @RequestBody Tag updatedTag) 
	{
	    Tag updated = tagService.updateTag(tagId, updatedTag);
	    return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping("/deletetag/{tagId}")
	// Deletes a tag by its ID
	public ResponseEntity<String> deleteTag(@PathVariable Long tagId) 
	{
	    tagService.deleteTag(tagId);
	    String msg = "Tag deleted with id " + tagId;
	    return new ResponseEntity<>(msg,HttpStatus.OK);
	}

	@GetMapping("/findbyname/{name}")
	public ResponseEntity<Tag> findTagByName(@PathVariable String name) 
	{
	    Tag tag = tagService.findByName(name);
	    return new ResponseEntity<>(tag, HttpStatus.OK);
	}
	
}
