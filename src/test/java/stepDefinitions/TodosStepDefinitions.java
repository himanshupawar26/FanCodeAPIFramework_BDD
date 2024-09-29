package stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.APIUtil;
import utils.ResponseParser;


import static org.junit.Assert.*;

public class TodosStepDefinitions {
	
	 private Response usersResponse;
	    private Response todosResponse;
	    private List<Integer> fancodeUserIds;

	    @Given("the API is available")
	    public void the_api_is_available() {
	        // Base URI is already set in APIUtil's static block
	        System.out.println("API is available and base URI is set.");
	    }

	    @When("I fetch the user data from {string}")
	    public void i_fetch_the_user_data_from(String endpoint) {
	        System.out.println("Fetching user data from endpoint: " + endpoint);
	        usersResponse = APIUtil.getUsers(endpoint);
	    }

	    @When("I fetch the todos data from {string}")
	    public void i_fetch_the_todos_data_from(String endpoint) {
	        System.out.println("Fetching todos data from endpoint: " + endpoint);
	        todosResponse = APIUtil.getTodos(endpoint);
	    }

	    @Then("all users from FanCode city should have more than 50% of their todos completed")
	    public void all_fancode_users_should_have_more_than_50_percent_todos_completed() {
	        // Parse users to get FanCode user IDs
	        fancodeUserIds = ResponseParser.getFanCodeUsers(usersResponse);

	        // Assert that we have FanCode users
	        assertFalse("No users found from FanCode city", fancodeUserIds.isEmpty());

	        // Parse todos and group by userId
	        List<Map<String, Object>> todos = todosResponse.jsonPath().getList("");

	        // For each FanCode user, calculate completion percentage
	        for (Integer userId : fancodeUserIds) {
	            List<Map<String, Object>> userTodos = todos.stream()
	                    .filter(todo -> (int) todo.get("userId") == userId)
	                    .collect(Collectors.toList());

	            if (userTodos.isEmpty()) {
	                fail("User ID " + userId + " has no todos.");
	            }

	            long completedTodos = userTodos.stream()
	                    .filter(todo -> (boolean) todo.get("completed"))
	                    .count();

	            double completionPercentage = (completedTodos * 100.0) / userTodos.size();

	            System.out.println("User ID: " + userId + ", Completion Percentage: " + completionPercentage + "%");

	            assertTrue("User ID " + userId + " has " + completionPercentage + "% tasks completed, which is not greater than 50%",
	                    completionPercentage > 50);
	        }
	    }    

}
