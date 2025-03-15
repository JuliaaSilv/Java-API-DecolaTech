package decolatech.api;

import decolatech.api.entity.User;

import java.lang.reflect.Field;

public class MethodsAdapter {
    public static User AtualizarDados(User oldUser, User newUser) {
        var properties = User.class.getDeclaredFields();

        for (Field field : properties ) {
            try {
                Object oldValue = field.get(oldUser);
                Object newValue = field.get(newUser);

                if (field.getName().equals("id")) continue;
                if (!oldValue.equals(newValue)) {
                    field.set(oldUser, newValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return oldUser;
    }
}
