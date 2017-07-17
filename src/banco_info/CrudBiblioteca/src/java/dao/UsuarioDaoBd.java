package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Usuario;

public class UsuarioDaoBd implements UsuarioDao {

    private Connection conexao;
    private PreparedStatement comando;

    public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);

        }

    }

    //Metodo salvar:recebe o id auto-increment e já relaciona 
    //no objeto usuario (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void salvar(Usuario usuario) {
        int id_usuario = 0;
        try {
            String sql = "INSERT INTO tb_usuario (matricula, nome, email, telefone) "
                    + "VALUES (?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, usuario.getMatricula());
            comando.setString(2, usuario.getNome());
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getTelefone());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id(matricula) para o objeto
                id_usuario = resultado.getInt(1);
                usuario.setId_usuario(id_usuario);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id_usuario conforme esperado!");
                throw new BDException("Nao gerou o id_usuario conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Usuario usuario) {
        try {
            String sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";

            conectar(sql);
            comando.setInt(1, usuario.getId_usuario());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(int id_usuario) {
        try {
            String sql = "DELETE FROM tb_usuario WHERE id_usuario = ?";

            conectar(sql);
            comando.setInt(1, id_usuario);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Usuario usuario) {
        try {
            String sql = "UPDATE tb_usuario SET matricula=?, nome=?, email=?, telefone=? WHERE id_usuario=?";

            conectar(sql);
            comando.setInt(1, usuario.getMatricula());
            comando.setString(2, usuario.getNome());
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getTelefone());
            comando.setInt(5, usuario.getId_usuario());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT id_usuario, matricula, nome, email, telefone FROM tb_usuario";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id_usuario = resultado.getInt("id_usuario");
                int matricula = resultado.getInt("matricula");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String telefone = resultado.getString("telefone");

                Usuario usu = new Usuario(id_usuario, matricula, nome, email, telefone);

                listaUsuarios.add(usu);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os usuarios do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaUsuarios);
    }

    @Override
    public Usuario procurarPorId_Usuario(int id_usuario) {
        String sql = "SELECT id_usuario, matricula, nome, email, telefone FROM tb_usuario WHERE id_usuario = ?";

        try {
            conectar(sql);
            comando.setInt(1, id_usuario);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int matricula = resultado.getInt("matricula");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String telefone = resultado.getString("telefone");

                Usuario usu = new Usuario(id_usuario, matricula, nome, email, telefone);

                return usu;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o usuario pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

}
