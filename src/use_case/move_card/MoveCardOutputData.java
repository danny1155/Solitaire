package use_case.move_card;

import entity.Card;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveCardOutputData {
    private boolean canBeMoved;
    private int movedColumn;
    public MoveCardOutputData(boolean canBeMoved, int movedColumn) {
        this.canBeMoved = canBeMoved;
        this.movedColumn = movedColumn;
    };

    public boolean getCanBeMoved() {return canBeMoved;}
    public void setCanBeMoved(boolean canBeMoved) {this.canBeMoved = canBeMoved;}
    public int getMovedColumn() {return movedColumn;}
}
