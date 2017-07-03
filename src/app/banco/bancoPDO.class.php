<?php
/*
/descrição: classe de conexao ao banco de dados
/criada em: 12/11/2014
/criada por: Mário Prederigo
/última atualização em: 12/11/2014
/alterada por: Mário Prederigo
*/

class BancoPDO {
	public $tipo = "mysql";
	public $host = "localhost";
	public $cliente = "root";
	public $senha = "";
	public $banco = "biblioteca";
	public $con = null;

	public function conexao() {
		try {
			//tipo:host=localhost;dbname=biblioteca
			
			$this->con = new PDO($this->tipo.":host=".$this->host.";dbname=".$this->banco,$this->cliente,$this->senha);
			return $this->con;

		} catch (PDOException $e) {
			echo "Erro: ".$e->getMessage();
		}
	}
}

?>