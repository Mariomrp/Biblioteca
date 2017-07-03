<?php

include "bancoPDO.class.php";

class usuarioDAO extends bancoPDO {

	public function __construct() {
		$this->conexao = bancoPDO::conexao();
	}

	public function inserir($usuario) {
		try {
		
			//$this->conexao->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_WARNING );
			
			$stm = $this->conexao->prepare("SELECT email FROM tb_usuario WHERE email = ?");
			$stm->bindValue(1,$usuario->email);
			$verifica = $stm->execute();
			
			if($stm->rowCount() > 0) {
				echo "<h3 class='alert alert-danger'>e-mail já existente</h3>";
				echo "<a href='../html/cadastroCliente.php'><input type='button' class='btn btn-danger dropdown-toggle' name='volta' id='voltar' value='Voltar'></a>  ";
			} else {
				$stm = $this->conexao->prepare("INSERT INTO tb_usuario (nome, telefone, email)
												VALUES (?, ?, ?)");

				$stm->bindValue(2, $usuario->nome);
				$stm->bindValue(3, $usuario->fone);
				$stm->bindValue(4, $usuario->email);
				$stm->execute();
				echo "<h3 class='alert alert-danger'>Usuário Cadastrado com Sucesso</h3>";
				echo "<a href='../html/cadastroCliente.php'><input type='button' class='btn btn-danger dropdown-toggle' name='volta' id='voltar' value='Voltar'></a>  ";
			}
		} catch(PDOException $e) {
			echo "Erro: ".$e->getMessage();
		}
	}

	
	//Pagina de consulta de cliente - Pesquisar no banco um cliente pelo nome
	public function visualizarPorNome($nome) {
		try {
			//$stm = $this->conexao->prepare(" SELECT * FROM cliente WHERE nome like '%".$nome."%' ");
			$stm = $this->conexao->prepare(" SELECT * FROM tb_usuario WHERE nome = ? ");
			$stm->bindValue(1,$nome);
			$stm->execute();
			
			if($stm->rowCount() == 0) {
				echo "<h3 align='center' class='alert alert-danger'>Nenhum cliente encontrado com este nome, tente pesquisar por outro campo !</h3>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' name='volta' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'></a>  ";
				
			} else {
				echo "
				<div id='pesquisaNome'>
					<h3 class='alert alert-danger'>Cliente Encontrado</h3>
					<table class='table table-striped' id='tabelaConsulta'>
						<tr>
							<th>Matricula</th>
							<th>Nome</th>
							<th>Telefone</th>
							<th>EDITAR</th>
							<th>EXCLUIR</th>
						</tr>";
				
				while($dados = $stm->fetch(PDO::FETCH_OBJ)) {
				
					echo "
						<tr>
							<td>".$dados->matricula."</td>
							<td>".$dados->nome."</td>
							<td>".$dados->fone."</td>
							<td>".$dados->email."</td>
							<td><a href='editarCliente.php?id=".$dados->matricula."'>Editar</a></td>
							<td><a href='excluirCliente.php?id=".$dados->matricula."'>Excluir</a></td>							
						</tr>
					</div>
					";
				}
				
				echo "</table>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'/>";
			}
		} catch(PDOException $e) {
			echo "Erro: ".$e->getMessage();
		}		
	}
	
	
	//Pagina de consulta de cliente - Pesquisar no banco um cliente pelo email
	public function visualizarPorEmail($email) {
		try {
			$stm = $this->conexao->prepare(" SELECT * FROM tb_usuario WHERE email = ? ");
			$stm->bindValue(1,$email);
			$stm->execute();
			
			if($stm->rowCount() == 0) {
				echo "<h3 align='center' class='alert alert-danger'>Nenhum cliente encontrado com este nome, tente pesquisar por outro campo !</h3>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' name='volta' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'></a>  ";
				
			} else {
				echo "
				<div id='pesquisaNome'>
					<h3 class='alert alert-danger'>Cliente Encontrado</h3>
					<table class='table table-striped' id='tabelaConsulta'>
						<tr>
							<th>Matricula</th>
							<th>Nome</th>
							<th>Telefone</th>
							<th>Email</th>
							<th>EDITAR</th>
							<th>EXCLUIR</th>
						</tr>";
				
				while($dados = $stm->fetch(PDO::FETCH_OBJ)) {
				
					echo "
						<tr>
							<td>".$dados->matricula."</td>
							<td>".$dados->nome."</td>
							<td>".$dados->fone."</td>
							<td>".$dados->email."</td>
							<td><a href='editarCliente.php?id=".$dados->matricula."'>Editar</a></td>
							<td><a href='excluirCliente.php?id=".$dados->matricula."'>Excluir</a></td>							
						</tr>
					</div>
					";
				}
				
				echo "</table>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'/>";
			}
		} catch(PDOException $e) {
			echo "Erro: ".$e->getMessage();
		}		
	}
	
	
	//BUSCAR CLIENTE PARA ALTERAÇÃO
	public function buscarDadosCliente($matricula) {
		try {	
	
			$stm = $this->conexao->prepare("SELECT * FROM tb_usuario WHERE matricula = ? ");
			$stm->bindValue(1, $matricula);
			
			$query = $stm->execute();
			
			if($query) {
				$dados = $stm->fetch(PDO::FETCH_OBJ);
				return $dados;
			}
		} catch (PDOException $e) {
			echo "ERRO: ".$e->getMessage();
		}
	}
	
	
	//ALTERAR CLIENTE
	public function alterarCliente($usuario) {
		try {	
	
			$stm = $this->conexao->prepare("UPDATE tb_usuario set nome = ?, fone = ?, email = ?	WHERE matricula = ? ");
			
			$stm->bindValue(1, $usuario->nome);
			$stm->bindValue(2, $usuario->fone);
			$stm->bindValue(3, $usuario->email);
			$stm->bindValue(4, $usuario->matricula);
			
			$query = $stm->execute();
			
			if($query) {
				echo "<h3 align='center' class='alert alert-danger'>Dados atualizados com sucesso !</h3>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' name='volta' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'></a>  ";
				return true;
			} else {
				return false;
			}
		} catch (PDOException $e) {
			echo "ERRO: ".$e->getMessage();
		}
	}
	
	
	//EXCLUIR CLIENTE
	public function excluirCliente($matricula) {
		try {
			$stm = $this->conexao->prepare("DELETE FROM tb_usuario WHERE matricula = ? ");
			$stm->bindValue(1, $matricula);
			
			$query = $stm->execute();
			
			if($query) {
				echo "<h3 align='center' class='alert alert-danger'>Cliente excluido da base de dados !</h3>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' name='volta' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'></a>  ";
				return true;
			} else {
				return false;
				echo "Erro";
			}
		} catch (PDOException $e) {
			echo "ERRO: ".$e->getMessage();
		}
	}
	
	
}//fim da classe
?>