cd /opt/jenkins/spring-k8-mongo
git pull origin dev
image_version="v-`date +"%s"`"
mvn clean install -DskipTests=true
echo "Building and pushing docker image version: $image_version"
docker build -t sujitht/crypto-k8-service:"$image_version" .
docker login
docker push sujitht/crypto-k8-service:"$image_version"
echo "Doing the rollout deployment with image version: $image_version"
sed -i --regexp-extended "s/v\-[0-9]{10}/$image_version/g" crypto-deployment.yml
kubectl apply -f crypto-deployment.yml