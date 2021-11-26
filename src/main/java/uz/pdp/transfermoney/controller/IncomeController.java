package uz.pdp.transfermoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.transfermoney.common.ResponseData;
import uz.pdp.transfermoney.payload.IncomeDto;
import uz.pdp.transfermoney.service.IncomeService;

import java.util.Optional;

@RestController
@RequestMapping("api/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    @GetMapping
    public ResponseEntity<ResponseData<Page<IncomeDto>>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseData.response(incomeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<IncomeDto>> findById(@PathVariable Long id){
        Optional<IncomeDto> incomeDto = incomeService.finById(id);

        if (incomeDto.isEmpty())
            return ResponseData.response("income not found", HttpStatus.NOT_FOUND);

        return ResponseData.response(incomeDto.get());
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<ResponseData<Page<IncomeDto>>> findAllByUserId(@PathVariable Long userId,
                                                                         @PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
         return ResponseData.response(incomeService.findAllByUserId(userId, pageable));
    }

}


