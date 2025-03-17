package com.sachintha.restapi.controller;


import com.sachintha.restapi.dto.ExpenseDTO;
import com.sachintha.restapi.io.ExpenseResponse;
import com.sachintha.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

//    public ExpenseController(ExpenseService expenseService, ModelMapper modelMapper) {
//        this.expenseService = expenseService;
//        this.modelMapper = modelMapper;
//    }

    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        // Call the service method
        List<ExpenseDTO> list = expenseService.getAllExpenses();
        // Convert the expense DTO to Expense Response
        List<ExpenseResponse> response = list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO)).collect(Collectors.toList());
        //Return the response
        return response;
    }

    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) {
       return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
