# How to configure the SFTP Server

## Requirements
- Install [Docker](https://docs.docker.com/engine/install/).
- Install [Docker Compose](https://docs.docker.com/compose/install/).

## Open the sftp-config folder and run the following commands:
```sh
sudo docker-compose up
```
> Wait for the menssage: server_1  | sshd is running.

Get the name of the container:
```sh
sudo docker container ls
```
Get the ip of the container:
```sh
sudo docker container inspect sftp-config_server_1 | grep -i \"IPAddress\"
```
Connect to the SSH server:
```sh
ssh master@CONTAINER_IP -p 22
```
> Note: SSH password is 'master'

Run the following commands from the SSH terminal:
```sh
sudo mkdir /uploads/spring
```
```sh
sudo mkdir /uploads/spring/upload
```
```sh
sudo useradd -d /uploads/spring -G sftp spring -s /usr/sbin/nologin
```
```sh
echo "spring:spring" | sudo chpasswd
```
```sh
sudo chown spring:sftp -R /uploads/spring/upload
```
Then exit the terminal with 'exit'.
