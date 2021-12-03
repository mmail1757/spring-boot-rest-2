package app.test.storage;

import app.test.dto.User;
import app.test.exceptions.UserAlreadyExistsException;
import app.test.exceptions.UserNotFoundException;
import app.test.exceptions.TechnicalErrorException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

    JdbcTemplate jdbcTemplate;

    JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject("select * from users_tbl u where u.login = ?", userRowMapper, login);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new TechnicalErrorException(e.getMessage());
        }
    }

    @Override
    public void createUser(User user) {
        try {
            jdbcTemplate.update("insert into users_tbl (login, passwd, balance) values (?, ?, ?)", user.getLogin(), user.getPassword(), 0);
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            throw new TechnicalErrorException(e.getMessage());
        }

    }

    RowMapper<User> userRowMapper = (rs,rowNum) -> {
        User u = new User();
        u.setLogin(rs.getString("login"));
        u.setPassword(rs.getString("passwd"));
        u.setBalance(rs.getBigDecimal("balance"));
        return u;
    };



}
