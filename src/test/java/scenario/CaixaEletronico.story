import model.Cliente;

//Descrição do teste (opcional)
description "Cliente faz saque "

//Narrativa da estória (opcional)
narrative 'Saque de Cliente', {
    as_a 'cliente'
    i_want 'de sacar dinheiro em caixa eletrônico'
    so_that 'eu não tenha que esperar numa fila de banco'
}
//Método que será executado antes de cada cenário
//Instancia o cliente e cria a variável que irá armazenar o retorno do saque
before_each "",{
    given "um cliente",{
        cliente = new Cliente()
        saqueEfetuado = false
    }
}
//Primeiro Cenário
scenario "cliente especial com saldo negativo",{
    given "um cliente especial com saldo atual de -200 reais",{
        cliente.saldoAtual = -200;
        cliente.clienteEspecial();
    }
    when "for solicitado um saque no valor de 100 reais", {
        saqueEfetuado = cliente.sacar(100);
    }
    then "deve efetuar o saque e atualizar o saldo da conta para -300 reais", {
        saqueEfetuado.shouldBe true
        (cliente.saldoAtual == -300).shouldBe true
    }
}

//Segundo Cenário
scenario "cliente comum com saldo negativo",{

    given "um cliente comum com saldo atual de -300 reais",{
        cliente.saldoAtual = -300;
        cliente.clienteComum();
    }
    when "for solicitado um saque no valor de 200 reais", {
        try{
            cliente.sacar(200)
        } catch (Exception e) {
            msg = e.message
        }
    }
    then "não deve efetuar o saque", {
        saqueEfetuado.shouldBe false
    }

    and "deve retornar a mensagem Saldo Insuficiente",{
        msg.shouldBe 'Saldo Insuficiente'
    }
}