package dao;

import services.Card;

public class CardDao extends AbstractJpaDao<Long, Card>{

    public CardDao() {
        super(Card.class);
    }
}