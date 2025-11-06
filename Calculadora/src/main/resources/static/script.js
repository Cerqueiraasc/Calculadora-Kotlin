let displayValue = '0';
let primeiroNumero = '';
let operacaoAtual = null;
let aguardandoSegundoNumero = false;
let historicoOperacoes = [];

const display = document.getElementById('display');

function atualizarDisplay() {
    display.innerText = displayValue;
    if (displayValue.length > 9) {
        display.style.fontSize = '1.8em';
    } else {
        display.style.fontSize = '2.8em';
    }
}

function limpar() {
    displayValue = '0';
    primeiroNumero = '';
    operacaoAtual = null;
    aguardandoSegundoNumero = false;
    historicoOperacoes = [];
    atualizarDisplay();
}

function adicionarDigito(digito) {
    if (aguardandoSegundoNumero) {
        displayValue = digito;
        aguardandoSegundoNumero = false;
    } else {
        if (displayValue === '0' && digito !== '.') displayValue = digito;
        else if (digito === '.' && displayValue.includes('.')) return;
        else if (digito === '00' && displayValue === '0') return;
        else displayValue += digito;
    }
    atualizarDisplay();
}

function adicionarOperacao(proximaOperacao) {
    const valorAtual = parseFloat(displayValue);

    if (operacaoAtual && aguardandoSegundoNumero) {
        operacaoAtual = proximaOperacao;

        if(historicoOperacoes.length > 0) {
            historicoOperacoes[historicoOperacoes.length - 1].tipo = proximaOperacao;
        }
        return;
    }

    if (historicoOperacoes.length === 0) {

        historicoOperacoes.push({
            tipo: "SOMA",
            numeros: [valorAtual]
        });
    } else {

        historicoOperacoes.push({
            tipo: operacaoAtual,
            numeros: [valorAtual]
        });
    }

    operacaoAtual = proximaOperacao;
    aguardandoSegundoNumero = true;
}

async function calcularResultado() {
    const valorFinal = parseFloat(displayValue);

    if (operacaoAtual === null) {
        return;
    }

    historicoOperacoes.push({
        tipo: operacaoAtual,
        numeros: [valorFinal]
    });

    const corpoRequisicao = {
        operacoes: historicoOperacoes
    };

    try {
        const url = `http://localhost:8080/calcular`;
        const resposta = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(corpoRequisicao)
        });

        if (!resposta.ok) {

            const erro = await resposta.json();
            displayValue = erro.mensagem || 'Erro!';
        } else {

            const resultado = await resposta.json();
            displayValue = resultado.toString();
        }

    } catch (error) {
        displayValue = 'Erro de rede!';
    } finally {

        atualizarDisplay();
        operacaoAtual = null;
        aguardandoSegundoNumero = false;
        primeiroNumero = '';
        historicoOperacoes = [];
    }
}