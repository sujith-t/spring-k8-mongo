# version="v-`date +"%s"`"

Clone code from github:
-------
    git clone git@github.com:sujith-t/spring-k8-mongo.git
    cd spring-k8-mongo
    
Build maven artifact:
-------
    mvn clean install -DskipTests=true
 
Build docker image for cryptocurrency service
--------------
    docker build -t sujitht/crypto-k8-service:version .
  
Docker login
-------------
    docker login
    
Push docker image to dockerhub
-----------
    docker push sujitht/crypto-k8-service:version

Encode USERNAME and PASSWORD using following commands:
--------
    echo -n "mongoadmin" | base64
    echo -n "admin123" | base64

Create the secret:
-------
    kubectl apply -f mongo-secret.yml

Create PV and PVC for Mongo using yaml file:
-----
    kubectl apply -f mongo-pv.yml
    kubectl apply -f mongo-pvc.yml

Give permission for service which is running under same namespace by using role binding
----------------------
    kubectl create rolebinding default-view \
      --clusterrole=view \
      --serviceaccount=default:default \
      --namespace=default

Create configmaps for URL which used in application:
-------
    kubectl apply -f mongo-config.yml

Deploy mongo:
-----------
    kubectl apply -f mongo-deployment.yml

Deploy cryptocurrency service:
-------------
    kubectl apply -f crypto-deployment.yml

Check secrets:
-------
    kubectl get secrets
    kubectl get configmaps
    kubectl get pv
    kubectl get pvc
    kubectl get deploy
    kubectl get pods
    kubectl get svc
 
Cleanup by using below commands:
--------
    kubectl delete -f crypto-deployment.yml
    kubectl delete -f mongo-deployment.yml
    kubectl delete -f mongo-config.yml
    kubectl delete -f mongo-pvc.yml
    kubectl delete -f mongo-pv.yml
    kubectl delete -f mongo-secret.yml

Testing blue-green deployment undo rolling out
--------
    kubectl rollout undo deployment crypto-deployment