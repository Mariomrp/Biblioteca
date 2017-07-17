package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Livro;

public class LivroDaoBd implements LivroDao {

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
    public void salvar(Livro livro) {
        int id_livro = 0;
        try {
            String sql = "INSERT INTO tb_livro (isbn, nome, editora, anopublicacao, id_autor) VALUES (?,?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, livro.getIsbn());
            comando.setString(2, livro.getNome());
            comando.setString(3, livro.getEditora());
            comando.setString(4, livro.getAnopublicacao());
            comando.setInt(5, livro.getId_autor());
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id_livro = resultado.getInt(1);
                livro.setId_livro(id_livro);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id_livro conforme esperado!");
                throw new BDException("Nao gerou o id_livro conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar livro no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Livro livro) {
        try {
            String sql = "DELETE FROM tb_livro WHERE id_livro = ?";

            conectar(sql);
            comando.setInt(1, livro.getId_livro());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar livro no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(int id_livro) {
        try {
            String sql = "DELETE FROM tb_livro WHERE id_livro = ?";

            conectar(sql);
            comando.setInt(1, id_livro);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar autor no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void atualizar(Livro livro) {
        try {
            String sql = "UPDATE tb_livro SET isbn=?, nome=?, editora=?, anopublicacao=?, id_autor=? WHERE id_livro=?";

            conectar(sql);
            comando.setInt(1, livro.getIsbn());
            comando.setString(2, livro.getNome());
            comando.setString(3, livro.getEditora());
            comando.setString(4, livro.getAnopublicacao());
            comando.setInt(5, livro.getId_autor());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar livro no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Livro> listar() {
        List<Livro> listaLivros = new ArrayList<>();

        String sql = "SELECT id_livro, isbn, nome, editora, anopublicacao, id_autor FROM tb_livro";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id_livro = resultado.getInt("id_livro");
                int isbn = resultado.getInt("isbn");
                String nome = resultado.getString("nome");
                String editora = resultado.getString("editora");
                String anopublicacao = resultado.getString("anopublicacao");
                int id_autor = resultado.getInt("id_autor");

                Livro liv = new Livro(id_livro, isbn, nome, editora, anopublicacao, id_autor);

                listaLivros.add(liv);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os livros do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaLivros);
    }

    @Override
    public Livro procurarPorId_Livro(int id_livro) {
        String sql = "SELECT isbn, nome, editora, anopublicacao, id_autor FROM tb_livro WHERE id_livro = ?";

        try {
            conectar(sql);
            comando.setInt(1, id_livro);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int isbn = resultado.getInt("isbn");
                String nome = resultado.getString("nome");
                String editora = resultado.getString("editora");
                String anopublicacao = resultado.getString("anopublicacao");
                int id_autor = resultado.getInt("id_autor");

                Livro liv = new Livro(id_livro, isbn, nome, editora, anopublicacao, id_autor);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o livro pelo isbn do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

}
