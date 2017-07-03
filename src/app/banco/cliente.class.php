<?php

class cliente {

	private $id;
	public $nome;
	private $telefone;
	private $cpf;
	private $rg;
	private $endereco;
	private $cidade;
	protected $email;
	private $senha;

	
	function __construct($id="", $nome="", $telefone="", $cpf="", $rg="", $endereco="", $cidade="", $email="", $senha="") {
		$this->id = $id;
		$this->nome = $nome;
		$this->telefone = $telefone;
		$this->cpf = $cpf;
		$this->rg = $rg;
		$this->endereco = $endereco;
		$this->cidade = $cidade;
		$this->email = $email;
		$this->senha = $senha;
	}
	
	function __set($prop, $valor) {
		$this->$prop = $valor;
	}

	function __get($prop) {
		return $this->$prop;
	}

}

?>