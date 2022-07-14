package by.academy.it.reunova.implementations;

import by.academy.it.reunova.entity.Buyer;
import by.academy.it.reunova.entity.Lot;
import by.academy.it.reunova.interfaces.BasketService;
import by.academy.it.reunova.repository.BuyerRepository;
import by.academy.it.reunova.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BuyerRepository buyerRepository;
    private final LotRepository lotRepository;

    @Override
    public void addLotToBasket(Integer buyerId, Integer lotId) {
        Buyer buyer = buyerRepository.findById(buyerId).orElse(null);
        Lot lot = lotRepository.findById(lotId).orElse(null);

        buyer.getLots().add(lot);
        buyerRepository.save(buyer);
    }

    @Override
    public void deleteLotFromBasket(Integer buyerId, Integer lotId) {
        Buyer buyer = buyerRepository.findById(buyerId).orElse(null);
        Lot lot = lotRepository.findById(lotId).orElse(null);

        buyer.getLots().remove(lot);
        buyerRepository.save(buyer);
    }
}
