<?php

class usuario {

	private $matricula;
	public $nome;
	private $fone;
	protected $email;

	function __construct($matricula="", $nome="", $email="", $fone="") {
		$this->id = $matricula;
		$this->nome = $nome;
		$this->email = $email;
		$this->fone = $fone;
	}
	
	function __set($prop, $valor) {
		$this->$prop = $valor;
	}

	function __get($prop) {
		return $this->$prop;
	}

}

?>