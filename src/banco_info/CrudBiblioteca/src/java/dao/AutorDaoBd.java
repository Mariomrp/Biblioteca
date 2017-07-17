package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Autor;

public class AutorDaoBd implements AutorDao {

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
    public void salvar(Autor autor) {
        int id_autor = 0;
        try {
            String sql = "INSERT INTO tb_autor (nome, origem) "
                    + "VALUES (?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, autor.getNome());
            comando.setString(2, autor.getOrigem());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id_autor = resultado.getInt(1);
                autor.setId_autor(id_autor);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id_autor conforme esperado!");
                throw new BDException("Nao gerou o id_autor conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar autor no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Autor autor) {
        try {
            String sql = "DELETE FROM tb_autor WHERE id_autor = ?";

            conectar(sql);
            comando.setInt(1, autor.getId_autor());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar autor no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(int id_autor) {
        try {
            String sql = "DELETE FROM tb_autor WHERE id_autor = ?";

            conectar(sql);
            comando.setInt(1, id_autor);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar autor no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Autor autor) {
        try {
            String sql = "UPDATE tb_autor SET nome=?, origem=? "
                    + "WHERE id_autor=?";

            conectar(sql);
            comando.setString(1, autor.getNome());
            comando.setString(2, autor.getOrigem());
            comando.setInt(3, autor.getId_autor());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar autor no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Autor> listar() {
        List<Autor> listaAutores = new ArrayList<>();

        String sql = "SELECT id_autor, nome, origem FROM tb_autor";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id_autor = resultado.getInt("id_autor");
                String nome = resultado.getString("nome");
                String origem = resultado.getString("origem");

                Autor aut = new Autor(id_autor, nome, origem);

                listaAutores.add(aut);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os autores do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaAutores);
    }

    @Override
    public Autor procurarPorId_Autor(int id_autor) {
        String sql = "SELECT id_autor, nome, origem FROM tb_autor WHERE id_autor = ?";

        try {
            conectar(sql);
            comando.setInt(1, id_autor);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String origem = resultado.getString("origem");

                Autor aut = new Autor(id_autor, nome, origem);

                return aut;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o autor pelo id_autor do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

}
