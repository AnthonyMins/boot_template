package boot.jpa.template.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boot.jpa.template.jpa.entity.UserDao;

public interface UserInterface extends JpaRepository<UserDao, Integer> {

}
