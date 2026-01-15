package repo;

import model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class UserRepository {

    private final Map<Integer, User> users = new HashMap<>();
    private int idCounter = 1;

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void save(User user) {
        int id = idCounter++;
        users.put(id, new User(id, user.getFname(), user.getLname(), user.getAge()));
    }

    public void update(int id, User updatedUser) {
        if (users.containsKey(id)) {
            users.put(id, new User(id, updatedUser.getFname(), updatedUser.getLname(), updatedUser.getAge()));
        }
    }

    public void delete(int id) {
        users.remove(id);
    }
}
