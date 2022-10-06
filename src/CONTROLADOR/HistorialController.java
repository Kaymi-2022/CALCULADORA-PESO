package CONTROLADOR;

import INTERFACE.CRUD;
import MODELO.Historial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialController implements CRUD {

    Conexion estado = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar(String texto) {
        int orden=1;
        List lista = new ArrayList();
        String sql = "SELECT * FROM historial WHERE APELLIDOS='"+texto+"'";
        try {
            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Historial historial = new Historial();
                historial.setHistorialId(orden++);
//                historial.setHistorialId(rs.getInt(1));
                historial.setNombre(rs.getString(2));
                historial.setApellidos(rs.getString(3));
                historial.setFecha(rs.getString(4));
                historial.setPeso(rs.getDouble(5));
                historial.setIMC(rs.getDouble(6));
                historial.setConsumo_calorias(rs.getDouble(7));
                historial.setQuema_calorias(rs.getDouble(8));
                lista.add(historial);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return lista;
    }

    @Override
    public int registrar(Object[] obj) {
        int r = 0;
        String sql = "INSERT INTO HISTORIAL VALUES (?,?,?,?,?,?,?)";
        try {
            con = estado.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            ps.setObject(5, obj[4]);
            ps.setObject(6, obj[5]);
            ps.setObject(7, obj[6]);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return r;

    }

    @Override
    public int modificar(Object[] obj) {
        int r = 0;
        String sql = "UPDATE HISTORIAL SET"
                + "peso = ?, IMC=?, consumo_calorias=?,quema_calorias=?"
                + "historialId=?)";

        try {
            con = estado.conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, obj[0]);
            ps.setObject(2, obj[1]);
            ps.setObject(3, obj[2]);
            ps.setObject(4, obj[3]);
            ps.setObject(5, obj[4]);
            ps.setObject(6, obj[5]);
            ps.setObject(7, obj[6]);

            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        return r;
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List buscar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
