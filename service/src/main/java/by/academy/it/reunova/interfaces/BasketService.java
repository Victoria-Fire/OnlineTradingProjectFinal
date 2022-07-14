package by.academy.it.reunova.interfaces;

public interface BasketService {
    void addLotToBasket(Integer buyerId, Integer lotId);
    void deleteLotFromBasket(Integer buyerId, Integer lotId);
}
