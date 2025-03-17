package com.sachintha.restapi.service.impl;

import com.sachintha.restapi.dto.ExpenseDTO;
import com.sachintha.restapi.entity.ExpenseEntity;
import com.sachintha.restapi.repository.ExpenseRepository;
import com.sachintha.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private ModelMapper modelMapper;

    @Override
    public List<ExpenseDTO> getAllExpenses() {
        //Call the repository method
        List<ExpenseEntity> list = expenseRepository.findAll();
        //convert the entity object to DTO object
        List<ExpenseDTO> listOfExpenses = list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());
        //return the list
        return listOfExpenses;
    }

    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }
}
