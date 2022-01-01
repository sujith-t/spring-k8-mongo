# spring-k8-mongo demo
# rolling update https://www.youtube.com/watch?v=xRifmrap7S8
# versioning -> stamp=`date +"%s"` filename="sdt_$stamp" echo $filename
# docker_image="sujitht/crypto-k8-service:v-`date +"%s"`"

    
Clone code from github:
-------
    git clone git@github.com:sujith-t/spring-k8-mongo.git
    cd spring-k8-mongo
    
Build Maven Artifact:
-------
    mvn clean install -DskipTests=true
 
Build Docker image for Springboot Application
--------------
    docker build -t sujitht/crypto-k8-service:version .
  
Docker login
-------------
    docker login
    
Push docker image to dockerhub
-----------
    docker push sujitht/crypto-k8-service:version

Encode USERNAME and PASSWORD of Postgres using following commands:
--------
    echo -n "mongoadmin" | base64
    echo -n "admin123" | base64
Create the Secret using kubectl apply:
-------
    kubectl apply -f mongo-secret.yml

Create PV and PVC for Mongo using yaml file:
-----
    kubectl apply -f mongo-pv.yml
    kubectl apply -f mongo-pvc.yml
    
Deploying Mongo with kubectl apply:
-----------
    kubectl apply -f mongo-deployment.yml
    kubectl apply -f mongo-service.yml
    
Give permission for service which is running under same namespace by using rolebinding
----------------------
    kubectl create rolebinding default-view \
      --clusterrole=view \
      --serviceaccount=default:default \
      --namespace=default

Create configmaps for URL which we use in Springboot:
-------
    kubectl apply -f mongo-config.yml
Deploy Springboot Application:
-------------
    kubectl apply -f springboot-deployment.yml
    kubectl apply -f springboot-service.yml
Check secrets:
-------
    kubectl get secrets
    kubectl get configmaps
    kubectl get pv
    kubectl get pvc
    kubectl get deploy
    kubectl get pods
    kubectl get svc
 
Now we can cleanup by using below commands:
--------
    kubectl delete deploy mongo spring-mongo-service
    kubectl delete svc mongodb-service spring-mongo-service
    kubectl delete pvc mongo-pv-claim
    kubectl delete pv mongo-pv-volume
    kubectl delete configmaps mongo-conf
    kubectl delete secrets mongo-secret

Testing blue-green deployment undo rolling out
--------
    kubectl rollout undo deployment crypto-deployment