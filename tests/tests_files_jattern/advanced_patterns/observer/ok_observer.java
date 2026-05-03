import java.util.ArrayList;
import java.util.Observer;

class EventManager {
    ArrayList<Observer> observerList;

    // constructor method
    EventManager() {
        observerList = new ArrayList<>();
    }

    void attach(Observer observer) {
        observerList.add(observer);
    }

    void notify(String message) {
        for (int i = 0; i < observerList.size(); i++) {
            observerList.get(i).update(null, message);
        }
    }
    
}