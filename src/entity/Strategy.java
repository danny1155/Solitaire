package entity;

import use_case.move_card.MoveCardOutputData;

public interface Strategy {
    MoveCardOutputData execute();
}
