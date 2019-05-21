package moduls.Baza;

import moduls.Artikel;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySqlnternalArticle implements InternalArticleDao {
    final String TABLE_NAME = "InternalArticle";
    final String SQL_GET_BY_ID = "select * from " + TABLE_NAME + " where internal_article_id = ? limit 1";
    final String SQL_GET_ALL = "select * from " + TABLE_NAME;
    final String SQL_INSERT = "insert into " + TABLE_NAME + "(internal_article_id, internal_id, name, price, vat, stock, created, modified) values(?, ?, ?, ?, ?, ?, ?, ?)";
    final String SQL_DELETE = "delete from " + TABLE_NAME + " where internal_article_id = ?";
    final String SQL_UPDATE = "update " + TABLE_NAME + " set internal_id = ?, name = ?, price = ?, vat = ?, stock = ?, modified = ? where internal_article_id = ?";
    @Override
    public Artikel getById(UUID id)
    {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_GET_BY_ID);
            stat.setBytes(1, Util.uuidToBinary(id));
            ResultSet rs = stat.executeQuery();
            if(rs.first())
            {
                return extractFromResultSet(rs);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Artikel> getAll() {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_GET_ALL);
            List<Artikel> artikli = new ArrayList<>();
            ResultSet rs = stat.executeQuery();
            while(rs.next())
            {
                artikli.add(extractFromResultSet(rs));
            }
            return artikli;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(Artikel m)
    {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_INSERT);
            stat.setBytes(1, Util.uuidToBinary(m.getId()));
            stat.setString(2,  (int)(Math.random() * 9999) + "");
            stat.setString(3, m.getNaziv());
            stat.setBigDecimal(4, new BigDecimal(m.getCena()));
            stat.setBigDecimal(5, new BigDecimal(m.getDdv()));
            stat.setInt(6, m.getKolicina());
            stat.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            stat.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            return stat.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Artikel m) {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_UPDATE);
            stat.setString(1,  (int)(Math.random() * 9999) + "");
            stat.setString(2, m.getNaziv());
            stat.setBigDecimal(3, new BigDecimal(m.getCena()));
            stat.setBigDecimal(4, new BigDecimal(m.getDdv()));
            stat.setInt(5, m.getKolicina());
            stat.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stat.setBytes(7, Util.uuidToBinary(m.getId()));
            return stat.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Artikel m) {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_DELETE);
            stat.setBytes(1, Util.uuidToBinary(m.getId()));
            return stat.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Artikel extractFromResultSet(ResultSet rs) throws SQLException {
        UUID id = Util.binaryToUuid(rs.getBytes("internal_article_id"));
        String code = rs.getString("internal_id");
        String name = rs.getString("name");
        BigDecimal price = rs.getBigDecimal("price");
        BigDecimal vat = rs.getBigDecimal("vat");
        int stock = rs.getInt("stock");
        return new Artikel(id, "0000000" + code, name, price.doubleValue(), vat.doubleValue(), stock);
    }
}
