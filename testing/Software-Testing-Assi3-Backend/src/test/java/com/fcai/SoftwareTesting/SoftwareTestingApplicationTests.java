package com.fcai.SoftwareTesting;

import com.fcai.SoftwareTesting.todo.Todo;
import com.fcai.SoftwareTesting.todo.TodoCreateRequest;
import com.fcai.SoftwareTesting.todo.TodoService;
import com.fcai.SoftwareTesting.todo.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.util.Lists.list;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SoftwareTestingApplicationTests {

	public TodoServiceImpl todoService;


	@Test //test case for path 1,3,5,7,8,9 (using prime path coverage)
	public void testCreateTodoWithValidData() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test Todo", "This is a test todo");
		TodoService todoService = new TodoServiceImpl();
		Todo todo = todoService.create(todoCreateRequest);
		assertEquals("Test Todo", todo.getTitle());
		assertEquals("This is a test todo", todo.getDescription());
		assertFalse(todo.isCompleted());
	}
	@Test //test case for path 1,2 (using prime path coverage)
	public void testCreateTodoWithNullData() {
		TodoCreateRequest todoCreateRequest = null;
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.create(todoCreateRequest);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo cannot be null", e.getMessage());
		}
	}

	@Test  //test case for path 1,3,5,6 (using prime path coverage)
	public void testCreateTodoWithEMPTYDescription() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test Todo", "");
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.create(todoCreateRequest);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo description cannot be empty", e.getMessage());
		}
	}
	@Test //test case for path 1,3,4 (using prime path coverage)
	public void testCreateTodoWithEmptyTitle() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("", "This is a test todo");
		TodoService todoService = new TodoServiceImpl();

		try {
			todoService.create(todoCreateRequest);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo title cannot be empty", e.getMessage());

		}
	}
	@Test //test case for path 1 ,3,5,6 (using prime path coverage)
	public void testReadTodoWithnonexistenceId() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test", "This is a test todo");
		TodoService todoService = new TodoServiceImpl();
		Todo createdTodo = todoService.create(todoCreateRequest);
        String id = "non-existent-id";
        try {
            todoService.read(id);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Todo not found", e.getMessage());
        }
	}
    @Test //test case for path 1 ,3,5,7 (using prime path coverage)
    public void testReadTodoWithValidId() {
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test", "This is a test todo");
        TodoService todoService = new TodoServiceImpl();
        Todo createdTodo = todoService.create(todoCreateRequest);
        Todo retrievedTodo = todoService.read(createdTodo.getId());
        assertEquals(createdTodo, retrievedTodo);
    }
    @Test ////test case for path 1 ,3,4 (using prime path coverage)
    public void testReadTodoWithEmptyId() {
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test Todo ", "This is a test todo");
        String id = "";
        TodoService todoService = new TodoServiceImpl();
        try {
            todoService.read(id);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Todo id cannot be empty", e.getMessage());
        }
    }
	@Test ////test case for path 1 ,2 (using prime path coverage)
	public void testReadTodoWithNullId() {
		String id = null;
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.read(id);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo id cannot be null", e.getMessage());
		}
	}


	@Test
	public void testUpdateTodoWithValidIdAndCompletedStatus() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test ", "This is a test todo");
		TodoService todoService = new TodoServiceImpl();
		Todo createdTodo = todoService.create(todoCreateRequest);
		boolean newCompletedStatus = true;
		Todo updatedTodo = todoService.update(createdTodo.getId(), newCompletedStatus);
		assertEquals(newCompletedStatus, updatedTodo.isCompleted());
	}

	@Test
	public void testUpdateTodoWithNullId() {
		String id = null;
		boolean completed = true;
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.update(id, completed);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo id cannot be null", e.getMessage());
		}
	}
	@Test
	public void testUpdateTodoWithEmptyId() {
		String id = "";
		boolean completed = true;
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.update(id, completed);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo id cannot be empty", e.getMessage());
		}
	}
	@Test
	public void testUpdateTodoWithNonExistentId() {
		String id = "non-existent-id";
		boolean completed = true;
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.update(id, completed);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo not found", e.getMessage());
		}
	}
	@Test
	public void testDeleteTodoWithValidId() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test ", "This is a test todo");
		TodoService todoService = new TodoServiceImpl();
		Todo createdTodo = todoService.create(todoCreateRequest);
		int initialTodoCount = todoService.list().size();
		todoService.delete(createdTodo.getId());
		int finalTodoCount = todoService.list().size();
		assertEquals(initialTodoCount - 1, finalTodoCount);
	}
	@Test
	public void testDeleteTodoWithNullId() {
		String id = null;
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.delete(id);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo id cannot be null", e.getMessage());
		}
	}
	@Test
	public void testDeleteTodoWithEmptyId() {
		String id = "";
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.delete(id);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo id cannot be empty", e.getMessage());
		}
	}
	@Test
	public void testDeleteTodoWithNonExistentId() {
		String id = "non-existent-id";
		TodoService todoService = new TodoServiceImpl();
		try {
			todoService.delete(id);
			fail("Expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Todo not found", e.getMessage());
		}
	}
	@Test
	public void testListTodos() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test Todo", "This is a test todo");
		TodoService todoService = new TodoServiceImpl();
		Todo createdTodo = todoService.create(todoCreateRequest);
		List<Todo> todoList = todoService.list();
		assertNotNull(todoList);
		assertTrue(todoList.size() > 0);
		assertTrue(todoList.contains(createdTodo));
	}

	@Test
	public void testListCompletedTodos() {
		TodoCreateRequest todo1CreateRequest = new TodoCreateRequest("Test Todo 1", "This is a test todo");
		TodoCreateRequest todo2CreateRequest = new TodoCreateRequest("Test Todo 2", "This is another test todo");
		TodoService todoService = new TodoServiceImpl();
		Todo todo1 = todoService.create(todo1CreateRequest);
		Todo todo2 = todoService.create(todo2CreateRequest);
		todoService.update(todo1.getId(), true);
		List<Todo> completedTodoList = todoService.listCompleted();
		assertNotNull(completedTodoList);
		assertEquals(1, completedTodoList.size());
		assertTrue(completedTodoList.contains(todo1));
		assertFalse(completedTodoList.contains(todo2));
	}
	@Test
	public void testListCompletedTodosWithNoCompletedTodos() {
		TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Test Todo", "This is a test todo");
		TodoService todoService = new TodoServiceImpl();
		todoService.create(todoCreateRequest);
		List<Todo> completedTodoList = todoService.listCompleted();
		assertNotNull(completedTodoList);
		assertEquals(0, completedTodoList.size());
	}








}

