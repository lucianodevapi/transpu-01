
# Commands Linux
  
* ## abra o ubuntu ou o wsl depois digite o comando
* ## sudo su -
  comando para acessar como usuário root. digite a senha 

* ## salve o arquivo *.pem dentro do diretório raiz do projeto
* ## pelo ubunto acesse o diretório raiz do projeto
* ## cp *.pem /root
  copia o arquivo *.pem para o diretório /root

* ## cd /root
  retorna para o diretório /root

# Na aws selecione a instância e click em conectar
* ## dentro da máquina da aws digite o comando
*  ## sudo yum update -y
  para atualizar a máquina virtual

*  ## sudo amazon-linux-extras install docker 
  comando para instalar o docker

*  ## sudo service docker start
  levanta o serviço do docker

* ## sudo curl -SL https://github.com/docker/compose/releases/download/v2.24.5/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
  instala docker compose

* ## sudo chmod +x /usr/local/bin/docker-compose
  permissão para executar aplicativos dentro do diretório

* ## sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
  torna o docker compose global

* ## scp -i "transpu.pem" docker-compose.yaml ec2-user@ec2-54-198-124-108.compute-1.amazonaws.com:/home/ec2-user
  no ubuntu envia o arquivo docker-compose.yaml para dentro da ec2 no diretorio /home/ec2-user

* ## sudo docker-compose up -d
  executa o docker-compose




