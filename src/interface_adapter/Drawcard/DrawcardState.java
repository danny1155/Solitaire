package interface_adapter.Drawcard;

public class DrawcardState {
    // Include attributes to represent the state of the draw card feature
    private boolean isDrawn;

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }
}
