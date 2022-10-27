package be.stackoverflow.user.service;

import be.stackoverflow.user.entity.User;
import be.stackoverflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public User createUser(User user) {
        verifyExistsUserName(user.getUserName());
        verifyExistsEmail(user.getUserEmail());

        user.setUserStatus(true);

        user.setRole(User.Role.USER);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User findUser(Long userId) {
        return findVerifiedUser(userId);
    }

    public List<User> findUserAll() {
        List<User> userAll = userRepository.findAll();
        return userAll;
    }

    // ACCESS와 MEMBERSTATUS는 설정하고, 바꾸는 권한은 뒤에 admin 완성시 구성 예정 / 상태 : undo
    public User updateUser(Long userId, User user) {
        User chosenUser = this.findUser(userId);

        Optional.ofNullable(user.getUserEmail()).ifPresent(email -> chosenUser.setUserEmail(email));
        Optional.ofNullable(user.getPassword()).ifPresent(password -> chosenUser.setPassword(password));

        return userRepository.save(chosenUser);
    }

    public void deleteUser(Long memberId) {
        User chosenUser = findVerifiedUser(memberId);

        userRepository.delete(chosenUser);
    }

    public User findVerifiedUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser = optionalUser.orElseThrow(() -> new RuntimeException("회원을 조회할 수 없습니다"));
        return findUser;
    }
    public void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByUserEmail(email);
        if (user.isPresent()) {
            throw new RuntimeException("같은 이메일로 등록된 회원이 이미 있습니다.");
        }
    }
    public void verifyExistsUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent()) {
            throw new RuntimeException("같은 이름으로 등록된 회원이 이미 있습니다.");
        }
    }
}
