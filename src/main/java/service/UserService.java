package service;

import dao.RoleRepo;
import dao.UserRepo;
import entity.Role;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepo userRepository,
                       RoleRepo roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepository;
        this.roleRepo = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    @Transactional
    public User findUserByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }
    @Transactional
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepo.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepo.save(user);
    }
////////////////////////////////////////////////////////////////////////////////////////

    @Transactional
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from Users", User.class).getResultList();
        return allUsers;
    }


    @Transactional
    public User getUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Transactional
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("delete from User " + "where id =:userId");
        query.setParameter("userId", id); //задаем имя для входящего параметра "id" и подставляем в запрос в БД
        query.executeUpdate();
    }
}
