package moduls.Baza;

import moduls.Racun;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MySqlInvoice implements InvoiceDao {
    final String TABLE_NAME = "Invoice";
    final String SQL_GET_BY_ID = "select * from " + TABLE_NAME + " where invoice_id = ? limit 1";
    final String SQL_GET_ALL = "select * from " + TABLE_NAME;
    final String SQL_INSERT = "insert into " + TABLE_NAME + "(invoice_id, total, total_vat, created, modified) values(?, ?, ?, ?, ?)";
    final String SQL_DELETE = "delete from " + TABLE_NAME + " where invoice_id = ?";
    final String SQL_UPDATE = "update " + TABLE_NAME + " set total = ?, total_vat = ?, modified = ? where invoice_id = ?";
    @Override
    public Racun getById(UUID id)
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
    public List<Racun> getAll() {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_GET_ALL);
            List<Racun> racuni = new ArrayList<>();
            ResultSet rs = stat.executeQuery();
            while(rs.next())
            {
                racuni.add(extractFromResultSet(rs));
            }
            return racuni;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(Racun m)
    {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_INSERT);
            stat.setBytes(1, Util.uuidToBinary(m.getId()));
            stat.setBigDecimal(2, new BigDecimal(m.getSkupniZnesekEvri()));
            stat.setBigDecimal(3, new BigDecimal(m.getDdv()));
            stat.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stat.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            return stat.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Racun m) {
        try
        {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stat = conn.prepareStatement(SQL_UPDATE);
            stat.setBigDecimal(1, new BigDecimal(m.getSkupniZnesekEvri()));
            stat.setBigDecimal(2, new BigDecimal(m.getDdv()));
            stat.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stat.setBytes(4, Util.uuidToBinary(m.getId()));
            return stat.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Racun m) {
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
    public Racun extractFromResultSet(ResultSet rs) throws SQLException {
        UUID id = Util.binaryToUuid(rs.getBytes("invoice_id"));
        BigDecimal total = rs.getBigDecimal("total");
        BigDecimal total_vat = rs.getBigDecimal("total_vat");
        return new Racun(id, total.doubleValue(), total_vat.doubleValue());
    }
}
