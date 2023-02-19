package estm.dsic.webby.Services;

import java.sql.SQLException;
import java.util.List;

import estm.dsic.webby.DAL.AdminDAO;
import estm.dsic.webby.Errors.AdminError;
import estm.dsic.webby.Models.Account;

public class Admin {
    public static List<Account> users() throws AdminError {
        List<Account> accs;

        try {
            accs = AdminDAO.users();
        } catch (SQLException e) {
            throw new AdminError("Can't contact database: "+e);
        }

        return accs;
    }

    public static void update_user(String id, String email) throws AdminError {
        int num_id;

        if (id == null || email == null || id == "" || email == "") {
            throw new AdminError("Get values are empty?");
        }

        try {
            num_id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new AdminError("ID is not a number?");
        }

        try {
            if(!AdminDAO.update_user(num_id, email)) {
                throw new AdminError("Can't update user: "+email);
            }
        } catch (SQLException e) {
            throw new AdminError("Can't contact database: "+e);
        }
    }

    public static void delete_user(String id) throws AdminError {
        int num_id;

        if (id == null || id == "") {
            throw new AdminError("Get values are empty?");
        }

        try {
            num_id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new AdminError("ID is not a number?");
        }

        try {
            if(!AdminDAO.delete_user(num_id)) {
                throw new AdminError("Can't update user with id: "+num_id);
            }
        } catch (SQLException e) {
            throw new AdminError("Can't contact database: "+e);
        }
    }
}
