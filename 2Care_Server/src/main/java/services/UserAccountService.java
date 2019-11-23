package services;

import domain.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    public abstract UserAccount getUserAccountById(Integer id);
    public abstract void saveUserAccount(UserAccount employee);
    public abstract void updateUserAccount(UserAccount employee);
    public abstract void deleteUserAccount(int id);
    public abstract List<UserAccount> getAllUserAccounts();
}
