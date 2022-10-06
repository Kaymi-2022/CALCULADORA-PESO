
package INTERFACE;

import java.util.List;

public interface CRUD {
    public List listar(String texto);
    public int registrar(Object obj[]);
    public int modificar(Object obj[]);
    public void eliminar(int id);
    public List buscar (String texto);
}
