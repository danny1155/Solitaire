package View;

import interface_adapter.Setup.SetupState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeViewModel extends ViewModel {
    private SetupState state = new SetupState();

    public HomeViewModel() {
        super("Home");
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