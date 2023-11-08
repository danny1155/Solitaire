package interface_adapter.Setup;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SetupViewModel extends ViewModel {
    private String cardsShown;
    private SetupState state = new SetupState();

    public SetupViewModel() {
        super("setup");
    }

    public String getCardsShown() {
        return cardsShown;
    }

    public void setCardsShown(String cardsShown) {
        this.cardsShown = cardsShown;
    }

    public void setState(SetupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        // Implement property change notification logic here
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // Implement adding property change listener here
        support.addPropertyChangeListener(listener);
    }

    public SetupState getState() {
        return state;
    }
}
