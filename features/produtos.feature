# language: pt
# charset: UTF-8

@compra
Funcionalidade: Compra
  Como um cliente cadastrado no https://automationexercise.com/
  Eu quero fazer a compra de ao menos três produtos
  Para que eu possa estar bem vestida

  Contexto:
    Dado que eu efetue o login com o email "thiago448@gmail.com"


  @CT001
  Cenario: Cenario: CT001 - login
    Quando verifico se estou na pagina inicial
    E adiciono "Stylish Dress" "3" itens ao carrinho
    E adiciono "Beautiful Peacock Blue Cotton Linen Saree" "2" itens ao carrinho
    E adiciono "Men Tshirt" "1" itens ao carrinho
    E adiciono "Summer White Top" "4" itens ao carrinho
    E adiciono "Fancy Green Top" "2" itens ao carrinho




