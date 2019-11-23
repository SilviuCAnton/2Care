package services;

import domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserAccountRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Transactional
    @Override
    public UserAccount getUserAccountById(Integer id) {
        Optional<UserAccount> result = userAccountRepository.findById(id);
        return result.get();
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
        userAccountRepository.deleteById(id);
    }

    @Transactional
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }
}
