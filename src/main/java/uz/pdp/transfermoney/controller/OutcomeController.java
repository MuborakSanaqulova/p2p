package uz.pdp.transfermoney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.transfermoney.common.ResponseData;
import uz.pdp.transfermoney.entity.Card;
import uz.pdp.transfermoney.entity.Users;
import uz.pdp.transfermoney.payload.OutcomeDto;
import uz.pdp.transfermoney.service.CardService;
import uz.pdp.transfermoney.service.OutcomeService;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/outcome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;

    @Autowired
    CardService cardService;

    @PostMapping
    public ResponseEntity<ResponseData<OutcomeDto>> sendTo(@Valid @RequestBody OutcomeDto outcomeDto) {

        Optional<Card> fromCard = cardService.findById(outcomeDto.getFromCard().getId());
        Optional<Card> toCard = cardService.findById(outcomeDto.getToCard().getId());

        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (fromCard.isEmpty() || toCard.isEmpty())
            return ResponseData.response("FromCard or ToCard don't exist", HttpStatus.BAD_REQUEST);

//        if (!fromCard.get().getUserId().equals(users.getId()))
//            return ResponseData.response("its not your card baby", HttpStatus.BAD_REQUEST);

        if (!cardService.canTransfer(fromCard.get().getId(), outcomeDto.getAmount()) || Objects.equals(fromCard.get().getId(), toCard.get().getId()))
            return ResponseData.response("(doesnt enough money) or(same cards toCard and fromCard)", HttpStatus.BAD_REQUEST);

        Optional<OutcomeDto> savedOutcome = outcomeService.sendTo(outcomeDto, fromCard.get(), toCard.get());

        return savedOutcome.map(ResponseData::response).orElseGet(
                () -> ResponseData.response("Transaction failed", HttpStatus.BAD_REQUEST));

    }

    @GetMapping("/userOutcomes/{userId}")
    public ResponseEntity<ResponseData<Page<OutcomeDto>>> findAllByUserId(@PathVariable Long userId, @PageableDefault(
            sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseData.response(outcomeService.findOutcomesByUserId(userId, pageable));
    }

    @GetMapping("/userOutcome")
    public ResponseEntity<ResponseData<OutcomeDto>> findOutcomeUserId(@RequestParam Long id, @RequestParam Long userId) {
        Optional<OutcomeDto> outcomeByUserId = outcomeService.findOutcomeByUserId(id, userId);
        if (outcomeByUserId.isEmpty())
            return ResponseData.response("user or outcome not found", HttpStatus.NOT_FOUND);
        return ResponseData.response(outcomeByUserId.get());
    }


    @GetMapping
    public ResponseEntity<ResponseData<Page<OutcomeDto>>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseData.response(outcomeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<OutcomeDto>> findByOutcomeId(@PathVariable Long id){
        Optional<OutcomeDto> byOutcomeId = outcomeService.findByOutcomeId(id);
        if (byOutcomeId.isEmpty())
            return ResponseData.response("outcome not found", HttpStatus.NOT_FOUND);
        return ResponseData.response(byOutcomeId.get());
    }
}
