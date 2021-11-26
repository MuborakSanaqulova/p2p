package uz.pdp.transfermoney.service.mapper;

import org.springframework.stereotype.Service;
import uz.pdp.transfermoney.entity.Card;
import uz.pdp.transfermoney.payload.CardDto;

@Service
public class CardMapper {

    public CardDto toDto(Card card){
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setUsername(card.getUsername());
        cardDto.setBalance(card.getBalance());
        cardDto.setNumber(card.getNumber());
        cardDto.setExpiredDate(card.getExpireDate());
        cardDto.setActive(card.isActive());
        cardDto.setUserId(card.getUserId());
        return cardDto;
    }

    public Card toEntity(CardDto cardDto){
        Card card = new Card();
        card.setUserId(cardDto.getUserId());
        card.setUsername(cardDto.getUsername());
        card.setExpireDate(cardDto.getExpiredDate());
        return card;
    }

}
