package moduls.Baza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface DaoCrud<T> {
    T getById(UUID id);
    List<T> getAll();
    boolean insert(T m);
    boolean update(T m);
    boolean delete(T m);
    T extractFromResultSet(ResultSet rs) throws SQLException;
}
