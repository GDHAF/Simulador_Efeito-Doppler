package util;

public class Biblioteca_Matematica {
    public static final float E  = 2.7182818284f; // Constante número de Euler
    public static final float PI = 3.1415926535f; // Constante pi
    
    // Retorna o valor absoluto de um número com vírgula
    public static double abs(double numeroVirgula) {
        return (numeroVirgula >= 0) ? numeroVirgula : 0 - numeroVirgula;
    }
    
    // Retorna o valor absoluto de um número inteiro
    public static long abs(long numeroInt) {
        return (numeroInt >= 0) ? numeroInt : -numeroInt;
    }
    
    // Retorna a exponenciação de um número com vírgula
    public static double exp(double valor, int expoente) {
        // Bloco switch-case para casos especiais de 0 ou 1
        switch (expoente) {
            case 0:
                return 1;
            case 1:
                return valor;
        }
        
        if (expoente > 1) { // Caso o expoente seja positivo diferente de 0 ou 1
            while (expoente > 1) {
                valor *= valor;
                expoente--;
            }
            
            return valor;
        } else { // Caso o expoente seja negativo
            /*
                Inverte o sinal do número do expoente dado, assim o algorítmo
                seguirá o mesmo funcionamento do caso em que o expoente é
                positivo.
            */
            expoente *= -1;
            
            while (expoente > 1) {
                valor *= 1/valor;
                expoente--;
            }
            
            return valor;
        }
    }
    
    // Retorna a exponenciação de um número inteiro
    public static double exp(long valor, int expoente) {
        //System.out.println("valor: " + valor + "- expoente: " + expoente);
        // Bloco switch-case para casos especiais de 0 ou 1
        switch (expoente) {
            case 0:
                return 1;
            case 1:
                return valor;
        }

        if (expoente > 1) { // Caso o expoente seja positivo diferente de 0 ou 1
            while (expoente > 1) {
                valor = valor*valor;
                expoente--;

            }

            return valor;
        } else { // Caso o expoente seja negativo
            /*
                Inverte o sinal do número do expoente dado, assim o algorítmo
                seguirá o mesmo funcionamento do caso em que o expoente é
                positivo.
            */
            expoente *= -1;
            
            while (expoente > 1) {
                valor *= 1/valor;
                expoente--;
            }

            return valor;
            
        }

    }
    
    // Retorna o fatorial de um número inteiro não negativo
    public static long fatorial(long numero) {
        /*
            Realiza a multipicação n! = 1.2.3...(n-2)(n-1)n. Quando o número
            for igual a 3, o resultado só será multiplicado por 2 e o elemento
            1 será ignorado porque todo número multiplicado por 1 equivale a
            ele mesmo.
        */
        while (numero > 2) {
            numero *= numero - 1;
            numero--;
        }
        
        return numero;
    }
    
    public static double raiz2(long numero) {
        /*
            Verifica se o número inserido é menor que 0, ou seja, se é um
            número negativo.
        */
        if (numero < 0) {
            // Lançamento de exceção
            throw new IllegalArgumentException("Não digite um número negativo!");
        }

        double resultado = 0;
        short intervalo = 0; 
        
        while (!(exp(intervalo, 2) >= numero)) {
            intervalo++;   
        }   

        if (exp(intervalo, 2) == numero) {
            /* 
                Confere se o intervalo ao quadrado é igual à entrada, ou seja,
                se é um quadrado perfeito.
            */
            return intervalo;
        } else {
            /*
                Substrai-se o resultado por 1 caso a entrada não seja um 
                quadrado perfeito.
            */
            resultado = intervalo - 1;
        }
        
        // Loop do somatório que determina as casas decimais da raiz não exata
        for (var casaDecimal = 1; casaDecimal <= 5; casaDecimal++) {
            var algarismo = 1;
            
            // Testa uma casa decimal e soma-a até ultrapassar a entrada
            while (exp(resultado + (algarismo/exp(10, casaDecimal)), 2) < numero) {
                
                algarismo++;
            }
            
            /*
                Como o resultado ao quadrado supera pois sua casa decial está
                acrescida, logo se subtrai essa casa decimal por uma unidade.
            */
            resultado += (algarismo - 1)/exp(10, casaDecimal);
        }
        
        return resultado;
    }
    
    // Retorna o logarítmo na base 10 de um número inteiro
    //public static double log10(long numero) {
        /*
            Verifica se o número inserido é maior que 0, pois uma função
            logarítmica não assume valores menores que 1.
        */
        /*if (!(numero >= 1)) {
            // Lançamento de exceção
            throw new IllegalArgumentException("Digite um número maior ou "
                                             + "igual a 1!");
        }
        
        
    }*/
    
    // Retorna o logarítmo natural de um número com vírgula
    public static double ln(double numero) {
        /*
            Verifica se o número inserido é maior que -1, pois uma função
            logarítmica não assume valores menores a 1.
        */
        if (!(numero > -1)) {
            // Lançamento de exceção
            throw new IllegalArgumentException("Digite um número maior que -1"
                                             + " e menor ou igual a 1!");
        }
        
        /*
            Inicializa uma variável para tornar possível o uso do operado +=
        */
        double resultado = 0;
        
        /*  
            Corrige o valor que deve ser empregado na fórmula. Como a expressão
            é ln(x+1), logo se subtrairmos 1 da entrada, teremos:
            
                                [(x-1)+1] = [x-1+1]= x
            
            Então, a entrada qualquer x terá a saída ln(x).
        */
        numero--;
        
        /*
            Bloco de repetição que equivale ao somatório da expansão em série
            de Taylor da função ln(x+1) com 10 parcelas.
        */
        for (var n = 1; n <= 10; n++) {
            resultado += exp(-1, n+1)*(exp(numero, n)/n);
        }
        
        return resultado;
    }
    
    // Retorna o logarítmo natural de um número inteiro
    public static double ln(long numero) {
        /*
            Verifica se o número inserido é maior que -1, pois uma função
            logarítmica não assume valores menores a 1.
        */
        if (!(numero > -1)) {
            // Lançamento de exceção
            throw new IllegalArgumentException("Digite um número maior que -1"
                                             + " e menor ou igual a 1!");
        }
        
        /*
            Inicializa uma variável para tornar possível o uso do operado +=
        */
        double resultado = 0;
        
        /*  
            Corrige o valor que deve ser empregado na fórmula. Como a expressão
            é ln(x+1), logo se subtrairmos 1 da entrada, teremos:
            
                                [(x-1)+1] = [x-1+1]= x
            
            Então, a entrada qualquer x terá a saída ln(x).
        */
        numero--;
        
        /*
            Bloco de repetição que equivale ao somatório da expansão em série
            de Taylor da função ln(x+1) com 10 parcelas.
        */
        for (var n = 1; n <= 10; n++) {
            resultado += exp(-1, n+1)*(exp(numero, n)/n);
        }
        
        return resultado;
    }
    
    /*
        Retorna o valor aproximado da função seno. O valor de entrada é medido
        em radianos, pode ser qualquer número real e é um número com vírgula.
    */
    public static double sen(double numero) {
        /*
            Inicializa uma variável para tornar possível o uso do operado +=
        */
        double resultado = 0;
        
        /*
            Bloco de repetição que equivale ao somatório da expansão em série
            de Taylor da função sen(x) com 10 parcelas.
        */
        for (var n = 1; n <= 10; n++) {
            resultado += exp(-1, n)*(exp(numero, 2*n+1)/fatorial(2*n+1));
        }
        
        return resultado;
    }
    
    /*
        Retorna o valor aproximado da função seno. O valor de entrada é medido
        em radianos, pode ser qualquer número real e é um número inteiro.
    */
    public static double sen(long numero) {
        /*
            Inicializa uma variável para tornar possível o uso do operado +=
        */
        long resultado = 0;
        
        /*
            Bloco de repetição que equivale ao somatório da expansão em série
            de Taylor da função sen(x) com 10 parcelas.
        */
        for (var n = 1; n <= 10; n++) {
            resultado += exp(-1, n)*(exp(numero, 2*n+1)/fatorial(2*n+1));
        }
        
        return resultado;
    }
    
    /*
        Retorna o valor aproximado da função cosseno. O valor de entrada é
        medido em radianos, pode ser qualquer número real e é um número com
        vírgula.
    */
    public static double cos(double numero) {
        /*
            Inicializa uma variável para tornar possível o uso do operado +=
        */
        double resultado = 0;
        
        /*
            Bloco de repetição que equivale ao somatório da expansão em série
            de Taylor da função cos(x) com 10 parcelas.
        */
        for (var n = 1; n <= 10; n++) {
            resultado += exp(-1, n)*(exp(numero, 2*n)/fatorial(2*n));
        }
        
        return resultado;
    }
    
    /*
        Retorna o valor aproximado da função cosseno. O valor de entrada é
        medido em radianos, pode ser qualquer número real e é um número
        inteiro.
    */
    public static double cos(long numero) {
        /*
            Inicializa uma variável para tornar possível o uso do operado +=
        */
        double resultado = 0;
        
        /*
            Bloco de repetição que equivale ao somatório da expansão em série
            de Taylor da função cos(x) com 10 parcelas.
        */
        for (var n = 1; n <= 10; n++) {
            resultado += exp(-1, n)*(exp(numero, 2*n)/fatorial(2*n));
        }
        
        return resultado;
    }
    
    /*
        Método que retorna um número com a quantidade de casas decimais
        determinadas pelo próprio usuário, o restante das casas decimais das 15
        possíveis de um número double terão valor zero.
    */
    public static double arred(double numero, int casas) { //TODO: FAZER ISSO REALMENTE FUNCIONAR
        // Verifica se o número de casas inserido pelo usuário é válido
        if (casas < 0 || casas > 15) {
            // Lançamento de exceção
            throw new IllegalArgumentException("Um número do tipo 'double' não "
                                             + "pode ter um número positivo de "
                                             + "casas decimais não maior que 15!");
        }
        
        /*
            String que representa a parcela das casas decimais da entrada como
            um dado de tipo string.
        */
        String[] numeroStr = Double.toString(numero).split(".");
        
        /*
            numeroStr[1] é o conjunto de casas decimais da entrada. Criamos uma
            substring para deixar o número de casas decimais especificada pelo
            usuário.
        */
        numeroStr[1] = numeroStr[1].substring(0, casas);
        
        // Concatena os elementos do array 'numeroStr', separando-os com vírgula
        numero = Double.parseDouble(String.join(".", numeroStr[0], numeroStr[1]));
        
        return numero;
    }
    
    /*
        Retorna a parte fracionária de um número com vírgula, ou seja, todos os
        números à direita da vírgula.
    */
    public static double parteFracionaria(double numero) {
        double numeroAnterior = numero;
        
        while (!(numeroAnterior < numero)) {
            numeroAnterior--;
        }
        
        return numero - numeroAnterior;
    }
    
    // Retorna a parte fracionária de um número inteiro, ou seja, 0.
    public static double parteFracionaria(long numero) {return 0;}
    
    // Retorna o menor número inteiro maior ou igual à entrada.
    public static double teto(double numero) {
        if (parteFracionaria(numero) == 0) {
            return numero; // Retorna o próprio número se ele for inteiro
        } else {
            return (numero - parteFracionaria(numero)) + 1;
        }
    }
    
    // Retorna o menor número inteiro menor ou igual à entrada
    public static double chao(double numero) {
        if (parteFracionaria(numero) == 0) {
            return numero; // Retorna o próprio número se ele for inteiro
        } else {
            return (numero - parteFracionaria(numero)) - 1;
        }
    }
    
    // Comando que compila todos os métodos disponíveis nesta biblioteca
    public static void ajuda() {
        System.out.println("""
                           Esta biblioteca de matemática é destinada ao uso
                           do cálculo númerico, ou seja, na aproximação no
                           cálculo de raízes de um problema. Os métodos podem
                           ser usado por meio do prefixo 'Biblioteca_Matematica',
                           seguido do nome de um método disponível.
                           
                           Estão listados abaixo os seguintes métodos dela:
                           
                           (1) E: retorna o número de Euler;
                           (2) PI: retorna o valor de pi;
                           """);
    }
    
    /*public static void main(String[] args) {
        System.out.println(raiz2(2));
    }*/
}