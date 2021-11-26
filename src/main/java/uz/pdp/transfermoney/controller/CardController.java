package uz.pdp.transfermoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.transfermoney.common.ResponseData;
import uz.pdp.transfermoney.entity.Users;
import uz.pdp.transfermoney.payload.CardDto;
import uz.pdp.transfermoney.service.AuthService;
import uz.pdp.transfermoney.service.CardService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/cards")
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    AuthService authService;

    @PostMapping
    public ResponseEntity<ResponseData<CardDto>> addCard(@Valid @RequestBody CardDto cardDto) {
        Users userById = authService.findUserById(cardDto.getUserId());
        if (userById == null)
            return ResponseData.response("user not found", HttpStatus.NOT_FOUND);
        return ResponseData.response(cardService.createCard(cardDto));
    }

    @GetMapping
    public ResponseEntity<ResponseData<Page<CardDto>>> findAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseData.response(cardService.findAllDto(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseData<CardDto>> findOne(@PathVariable Long id) {
        Optional<CardDto> one = cardService.findOne(id);
//        return one.map(ResponseData::response).orElseGet(()->ResponseData.response())
        if (one.isEmpty())
            return ResponseData.response("card not found", HttpStatus.NOT_FOUND);
        return ResponseData.response(one.get());
    }

    @GetMapping("/getUserCard/{userId}")
    public ResponseEntity<ResponseData<Page<CardDto>>> findCardsByUserId(@PathVariable Long userId,
                                                                         @PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){

        return ResponseData.response(cardService.findCardsByUserId(userId, pageable));

    }

}
