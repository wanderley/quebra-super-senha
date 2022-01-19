# Quebra Super Senha

Este é um solucionador para o desafio [Super Senha](https://vini.me/supersenha/) que funciona da seguinte forma:

- Entre um dos chutes possíveis no Super Senha.
- Para cada letra verde, atualize seu respectivo chute. Por exemplo, se você acertou o chute na primeira posição (um C), então substitua o campo "Chutes para letra 1" pelo valor "C".
- Entre as letras em cinza no campo "Letras que não estão na palavra".
- Entre as letras em amarelo no campo "Letras que estão em algum lugar da palavra".
- Note que a lista de possíveis chutes é atualizada após cada alteração.

## Desenvolvimento

Para iniciar o desenvolvimento interativo rode


    lein figwheel

e abra seu broswer na página [localhost:3449](http://localhost:3449/). O código será compilado automaticamente e todas as mudanças serão enviadas para o navegador sem a necessidade de reinciá-lo.


Para apagar todos os arquivos compilados:

    lein clean

Para criar uma build para produção:

    lein do clean, cljsbuild once min

## Colaboradores

- [Leonardo Coelho](https://github.com/leonardocna)
- [Cesar Valerio](https://github.com/Catulow)


## Licença

Copyright © 2022 Wanderley Guimarães da Silva

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
