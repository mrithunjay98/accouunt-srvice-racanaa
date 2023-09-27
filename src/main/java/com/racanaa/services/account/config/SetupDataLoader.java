package com.racanaa.services.account.config;

import com.racanaa.services.account.constant.ApiConstant;
import com.racanaa.services.account.persistance.model.Account;
import com.racanaa.services.account.persistance.model.Privilege;
import com.racanaa.services.account.persistance.model.Role;
import com.racanaa.services.account.persistance.model.User;
import com.racanaa.services.account.persistance.repository.AccountRepository;
import com.racanaa.services.account.persistance.repository.PrivilegeRepository;
import com.racanaa.services.account.persistance.repository.RoleRepository;
import com.racanaa.services.account.persistance.repository.UserRepository;
import com.racanaa.services.account.util.PasswordUtil;
import com.racanaa.services.account.util.StringUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component("SetupDataLoader")
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        // Account setup
        Account rootAccount = createAccount("Racanaa", ApiConstant.ACCOUNT_TYPE_ROOT, null, "6e399983-862e-4435-808a-4056ebc4f841");
        Account clientAccount = createAccount("Evolve Studio", ApiConstant.ACCOUNT_TYPE_CLIENT, rootAccount, null);
        Account agencyAccount = createAccount("Carbon Happy", ApiConstant.ACCOUNT_TYPE_AGENCY, rootAccount, null);
        Account clientAccount2 = createAccount("Beans Coffee", ApiConstant.ACCOUNT_TYPE_CLIENT, agencyAccount, null);

        // Privilege setup
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ", rootAccount.getId());
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE", rootAccount.getId());
        Privilege updatePrivilege
                = createPrivilegeIfNotFound("UPDATE", rootAccount.getId());
        Privilege deletePrivilege
                = createPrivilegeIfNotFound("DELETE", rootAccount.getId());

        // Role setup
        List<Privilege> superAdminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege, updatePrivilege, deletePrivilege);
        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);

        Role superAdminRole = createRoleIfNotFound("SUPER_ADMIN", superAdminPrivileges, rootAccount.getId());
        Role adminRole = createRoleIfNotFound("ADMIN", adminPrivileges, rootAccount.getId());

        Role userRole = createRoleIfNotFound("USER", Arrays.asList(readPrivilege), rootAccount.getId());
        List<Role> adminRoles = Arrays.asList(adminRole, userRole);

        // User Setup
        createUser("Racanaa@r.com", "Racanaa", rootAccount, Arrays.asList(superAdminRole));
        createUser("Evolve_Studio@r.com", "Evolve Studio", clientAccount, Arrays.asList(userRole));
        createUser("Carbon_Happy@r.com", "Carbon Happy", agencyAccount, adminRoles);
        createUser("Beans_Coffee@r.com", "Beans Coffee", clientAccount2, Arrays.asList(userRole));
        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name, String accountId) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege.setTenantId(accountId);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges, String accountId) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setTenantId(accountId);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
    public Account createAccount(String name, String type, Account acc, String apiKey) {
        Account account = accountRepository.findByName(name);
        if (account == null) {
            Account newAccount = new Account();
            if (acc == null) {
                newAccount.setId("402881888abbf1f3018abbf1f6a00007");
            }
            newAccount.setName(name);
            newAccount.setType(type);
            newAccount.setTenantId(acc != null ? acc.getId() : null);
            newAccount.setEmail("support@racanaa.com");
            newAccount.setAddressLine1("WhiteField");
            newAccount.setCountry("India");
            newAccount.setState("Karnataka");
            newAccount.setDistrict("Bangalore");
            newAccount.setPinCode("560078");
            newAccount.setCountryCode("+91");
            newAccount.setContact("12333312121l");
            if (StringUtil.isNotEmpty(apiKey)) {
                newAccount.setApiKey(apiKey);
            } else {
                newAccount.setApiKey(UUID.randomUUID().toString());
            }
            account = accountRepository.save(newAccount);
        }
        return account;
    }

    public User createUser(String emailId, String name, Account account, Collection<Role> roles) {
        User user = userRepository.findByEmail(emailId);
        if (user == null) {
            User newUser = new User();
            newUser.setEmail(emailId);
            newUser.setName(name);
            newUser.setPasswordSalt(PasswordUtil.generateSalt("test"));
            newUser.setPassword(PasswordUtil.encodePassword("test"));
            newUser.setRoles(roles);
            newUser.setTenantId(account.getId());
            user = userRepository.save(newUser);
        }
        return user;
    }
}
