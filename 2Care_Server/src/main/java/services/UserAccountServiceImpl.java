package services;

import domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserAccountRepository;

import java.util.List;

@Component
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Transactional
    public UserAccount getUserAccountById(int id) {
        return userAccountRepository.findOne(id);
    }


    @Transactional
    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Transactional
    public void updateUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Transactional
    public void deleteUserAccount(int id) {
        userAccountRepository.delete(id);
    }

    @Transactional
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }
}
