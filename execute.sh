sudo docker stop front;
sudo docker rm front
sudo docker rmi $(sudo docker images  | grep projet_bibliotheque_burdy_simon_zelleg_massyl_front | awk '{print $3}');
sudo docker-compose -f apps-docker-compose.yml up -d front;
