package com.employee.management.employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Employee", description = "Employee management APIs")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Operation(
            summary = "Retrieve all employees",
            description = "Get all employees. The response is html.",
            tags = { "employees", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("allemplist", employeeRepository.findAll());
        return  "home";
    }

    @GetMapping("/addnew")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-user";
    }

    @Operation(
            summary = "Add/Update new employee",
            description = "Add/update new employee. The response is home page html.",
            tags = { "add/update employee", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @PostMapping("/save")
    public String saveEmployee(@RequestBody @ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
        if(result.hasErrors()){
            return "add-user";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);
        return "update-user";
    }

    @Operation(
            summary = "Deletes an employee by id",
            description = "delete an employee by id. The response is home page html.",
            tags = { "delete an employee", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/deleteEmployee/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        employeeRepository.deleteById(id);
        return "redirect:/";

    }

    @Operation(
            summary = "Get an employee by id",
            description = "Get an employee by id. The response is home page html.",
            tags = { "get an employee by id", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/html") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/showUser/{id}")
    public String viewUser(@PathVariable(value = "id") Long id, Model model){
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);
        return "view-user";
    }

}
