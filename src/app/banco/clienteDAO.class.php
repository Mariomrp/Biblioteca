<?php

include "bancoPDO.class.php";

class clienteDAO extends bancoPDO {

	public function __construct() {
		$this->conexao = bancoPDO::conexao();
	}

	public function inserir($cliente) {
		try {
		
			//$this->conexao->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_WARNING );
			
			$stm = $this->conexao->prepare("SELECT email FROM cliente WHERE email = ?");
			$stm->bindValue(1,$cliente->email);
			$verifica = $stm->execute();
			
			if($stm->rowCount() > 0) {
				echo "
					<style type='text/css'>
						@import '../css/cliente/cliente.css';
						@import '../css/cliente/clienteBootstrapTheme.css';
						@import '../css/cliente/clienteBootstrap.css';
					</style>
				";
				echo "<h3 class='alert alert-danger'>e-mail já existente</h3>";
				echo "<a href='../html/cadastroCliente.php'><input type='button' class='btn btn-danger dropdown-toggle' name='volta' id='voltar' value='Voltar'></a>  ";
			} else {
				$stm = $this->conexao->prepare("INSERT INTO cliente (nome, telefone, cpf, rg, endereco, cidade, email, senha)
												VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

				$stm->bindValue(1, $cliente->nome);
				$stm->bindValue(2, $cliente->telefone);
				$stm->bindValue(3, $cliente->cpf);
				$stm->bindValue(4, $cliente->rg);
				$stm->bindValue(5, $cliente->endereco);
				$stm->bindValue(6, $cliente->cidade);
				$stm->bindValue(7, $cliente->email);
				$stm->bindValue(8, $cliente->senha);

				$stm->execute();
				echo "
					<style type='text/css'>
						@import '../css/cliente/cliente.css';
						@import '../css/cliente/clienteBootstrapTheme.css';
						@import '../css/cliente/clienteBootstrap.css';
					</style>
				";
				echo "<h3 class='alert alert-danger'>Cliente Cadastrado com Sucesso</h3>";
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
			$stm = $this->conexao->prepare(" SELECT * FROM cliente WHERE nome = ? ");
			$stm->bindValue(1,$nome);
			$stm->execute();
			
			if($stm->rowCount() == 0) {
			echo "
				<style type='text/css'>
					@import '../css/cliente/cliente.css';
					@import '../css/cliente/clienteBootstrapTheme.css';
					@import '../css/cliente/clienteBootstrap.css';
				</style>
			";
				echo "<h3 align='center' class='alert alert-danger'>Nenhum cliente encontrado com este nome, tente pesquisar por outro campo !</h3>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' name='volta' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'></a>  ";
				
			} else {
				echo "
					<style type='text/css'>
						@import '../css/cliente/cliente.css';
						@import '../css/cliente/clienteBootstrapTheme.css';
						@import '../css/cliente/clienteBootstrap.css';
					</style>
				";
				echo "
				<div id='pesquisaNome'>
					<h3 class='alert alert-danger'>Cliente Encontrado</h3>
					<table class='table table-striped' id='tabelaConsulta'>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>Telefone</th>
							<th>CPF</th>
							<th>RG</th>
							<th>Endereco</th>
							<th>Cidade</th>
							<th>Email</th>
							<th>EDITAR</th>
							<th>EXCLUIR</th>
						</tr>";
				
				while($dados = $stm->fetch(PDO::FETCH_OBJ)) {
				
					echo "
						<tr>
							<td>".$dados->idCliente."</td>
							<td>".$dados->nome."</td>
							<td>".$dados->telefone."</td>
							<td>".$dados->cpf."</td>
							<td>".$dados->rg."</td>
							<td>".$dados->endereco."</td>
							<td>".$dados->cidade."</td>
							<td>".$dados->email."</td>
							<td><a href='editarCliente.php?id=".$dados->idCliente."'>Editar</a></td>
							<td><a href='excluirCliente.php?id=".$dados->idCliente."'>Excluir</a></td>							
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
			$stm = $this->conexao->prepare(" SELECT * FROM cliente WHERE email = ? ");
			$stm->bindValue(1,$email);
			$stm->execute();
			
			if($stm->rowCount() == 0) {
				echo "
					<style type='text/css'>
						@import '../css/cliente/cliente.css';
						@import '../css/cliente/clienteBootstrapTheme.css';
						@import '../css/cliente/clienteBootstrap.css';
					</style>
				";
				echo "<h3 align='center' class='alert alert-danger'>Nenhum cliente encontrado com este nome, tente pesquisar por outro campo !</h3>";
				echo "<a href='../html/pesquisaCliente.php'><input type='button' name='volta' id='voltar' value='Voltar' class='btn btn-danger dropdown-toggle'></a>  ";
				
			} else {
				echo "
					<style type='text/css'>
						@import '../css/cliente/cliente.css';
						@import '../css/cliente/clienteBootstrapTheme.css';
						@import '../css/cliente/clienteBootstrap.css';
					</style>
				";
				echo "
				<div id='pesquisaNome'>
					<h3 class='alert alert-danger'>Cliente Encontrado</h3>
					<table class='table table-striped' id='tabelaConsulta'>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>Telefone</th>
							<th>CPF</th>
							<th>RG</th>
							<th>Endereco</th>
							<th>Cidade</th>
							<th>Email</th>
							<th>EDITAR</th>
							<th>EXCLUIR</th>
						</tr>";
				
				while($dados = $stm->fetch(PDO::FETCH_OBJ)) {
				
					echo "
						<tr>
							<td>".$dados->idCliente."</td>
							<td>".$dados->nome."</td>
							<td>".$dados->telefone."</td>
							<td>".$dados->cpf."</td>
							<td>".$dados->rg."</td>
							<td>".$dados->endereco."</td>
							<td>".$dados->cidade."</td>
							<td>".$dados->email."</td>
							<td><a href='editarCliente.php?id=".$dados->idCliente."'>Editar</a></td>
							<td><a href='excluirCliente.php?id=".$dados->idCliente."'>Excluir</a></td>							
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
	public function buscarDadosCliente($id) {
		try {	
	
			$stm = $this->conexao->prepare("SELECT * FROM cliente WHERE idCliente = ? ");
			$stm->bindValue(1, $id);
			
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
	public function alterarCliente($cliente) {
		try {	
	
			$stm = $this->conexao->prepare("UPDATE cliente set nome = ?, telefone = ?, cpf = ?, rg = ?,
											endereco = ?, cidade = ?, email = ?, senha = ?
											WHERE idCliente = ? ");
			
			$stm->bindValue(1, $cliente->nome);
			$stm->bindValue(2, $cliente->telefone);
			$stm->bindValue(3, $cliente->cpf);
			$stm->bindValue(4, $cliente->rg);
			$stm->bindValue(5, $cliente->endereco);
			$stm->bindValue(6, $cliente->cidade);
			$stm->bindValue(7, $cliente->email);
			$stm->bindValue(8, $cliente->senha);
			$stm->bindValue(9, $cliente->id);
			
			$query = $stm->execute();
			
			if($query) {
				echo "
					<style type='text/css'>
						@import '../css/cliente/cliente.css';
						@import '../css/cliente/clienteBootstrapTheme.css';
						@import '../css/cliente/clienteBootstrap.css';
					</style>
				";
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
	public function excluirCliente($id) {
		try {
			$stm = $this->conexao->prepare("DELETE FROM cliente WHERE idCliente = ? ");
			$stm->bindValue(1, $id);
			
			$query = $stm->execute();
			
			if($query) {
								echo "
					<style type='text/css'>
						@import '../css/cliente/cliente.css';
						@import '../css/cliente/clienteBootstrapTheme.css';
						@import '../css/cliente/clienteBootstrap.css';
					</style>
				";
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