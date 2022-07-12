package by.academy.it.reunova.implementations;

import by.academy.it.reunova.dto.BuyerDto;
import by.academy.it.reunova.dto.converter.implementations.BuyerConverterImpl;
import by.academy.it.reunova.dto.converter.interfaces.BuyerConverter;
import by.academy.it.reunova.entity.Buyer;
import by.academy.it.reunova.interfaces.BuyerService;
import by.academy.it.reunova.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private BuyerConverter buyerConverter = new BuyerConverterImpl();
    private final BuyerRepository buyerRepository;

    @Override
    public void saveBuyer(BuyerDto buyerDto) {
        buyerRepository.save(buyerConverter.toBuyer(buyerDto));
    }

    @Override
    public void deleteBuyer(Integer buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow();
        buyerRepository.delete(buyer);
    }

    @Override
    public BuyerDto findBuyerById(Integer buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId).orElseThrow();
        return buyerConverter.toBuyerDto(buyer);
    }

    @Override
    public List<BuyerDto> findAllBuyerDto() {
        return StreamSupport
                .stream(buyerRepository.findAll().spliterator(), false)
                .map(buyerConverter::toBuyerDto)
                .collect(Collectors.toList());
    }

}
