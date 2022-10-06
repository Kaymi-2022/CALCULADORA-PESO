package CONTROLADOR;

import INTERFACE.CRUD;
import VISTA.VentanaCalculadoras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class LoginController implements CRUD {

    Conexion estado = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean buscarUsuario(String user, String pass) {
        String usuario = null;
        String clave = null;
        String nombre;
        String apellidos;
        boolean rpta = false;
        try {
            VentanaCalculadoras principal = new VentanaCalculadoras();
            String sql = "SELECT * FROM USUARIOS";
            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = rs.getString(2);
                clave = rs.getString(3);
                nombre = rs.getString(4);
                apellidos = rs.getString(5);
                if (usuario.equals(user) && clave.equals(pass)) {
                    JOptionPane.showMessageDialog(null, "LOGIN EXITOSO", "MENSAJE", 2);
                    rpta = true;
                    principal.datos[0] = nombre;
                    principal.datos[1] = apellidos;
                }
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
        return rpta;
    }

    @Override
    public List listar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int registrar(Object[] obj) {
        int r = 0;
        String usuario;
        boolean existe = false;
        try {
            String sql = "SELECT * FROM USUARIOS";
            con = estado.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = rs.getString(2);
                if (usuario.equals(obj[0])) {
                    JOptionPane.showMessageDialog(null, "USUARIO YA EXISTE", "MENSAJE", 2);
                    existe = true;
                }
            }
            if (existe!=true) {
                try {
                    sql = "INSERT INTO USUARIOS VALUES (?,?,?,?,?)";
                    con = estado.conectar();
                    ps = con.prepareStatement(sql);
                    ps.setObject(1, obj[0]);
                    ps.setObject(2, obj[1]);
                    ps.setObject(3, obj[2]);
                    ps.setObject(4, obj[3]);
                    ps.setObject(5, obj[4]);
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
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return r;
    }

    @Override
    public int modificar(Object[] obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
