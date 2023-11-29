app=adventure
tag=3.4

eval $(minikube docker-env)

mvn clean package -DskipTests -B
docker build -t ${app}:${tag} .