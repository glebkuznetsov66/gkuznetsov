package ru.job4j.map;

import java.util.Calendar;

/**
 * Class for creating User.
 *
 * @author gkuznetsov.
 * @version 0.1.
 * @since 06.10.2017.
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * User children.
     */
    private int children;
    /**
     * User birthday.
     */
    private Calendar birthday;

    /**
     * User constructor.
     * @param name - user name.
     * @param children -  user children.
     * @param birthday - user birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Equals method.
     * @param o - object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }

    /**
     * Hash code.
     * @return int.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
